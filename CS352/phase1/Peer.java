/**
 * Created by Abhishek Prajapati
 * Shridhar Oza
 */

import GivenTools.TorrentInfo;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * peer class
 */
public class Peer implements Runnable{

    int port_number;
    String ip_address;
    private TorrentInfo torrentInfo;
    private Socket socket = null;
    private DataOutputStream dOutStream = null;
    private DataInputStream dInStream = null;
    private FileOutputStream fOutStream = null;
    private final int timeoutTime = 130000;
    private static final int KBLIM = 16384;
    private ArrayList<byte[]> pieces = new ArrayList<>();


    /**
     * peer constructor
     * @param port_number port number
     * @param ip_address ip address
     */
    public Peer(int port_number, String ip_address){
        this.ip_address = ip_address;
        this.port_number = port_number;
    }

    /**
     * peer constructor
     * @param port_number port number
     * @param ip_address ip address
     * @param torrentInfo torrent info
     * @param peerID peer id
     * @throws Exception IO exception
     */
    Peer(int port_number, String ip_address, TorrentInfo torrentInfo, byte[] peerID) throws Exception{
        this.ip_address = ip_address;
        this.port_number = port_number;
        this.torrentInfo = torrentInfo;
        socket = new Socket(ip_address, port_number);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        dInStream = new DataInputStream(input);
        dOutStream = new DataOutputStream(output);

        //set up a handshake
        if(!(sendHandshake(torrentInfo.info_hash.array(), peerID))){
            System.out.println("Handshake Failed");
            return;
        }

        boolean success = download();
        if(!success)
            System.out.println("download Failed!");
        else
            System.out.println("download Suceeeded!");

        socket.close();
        dInStream.close();
        dOutStream.close();
        fOutStream.close();

    }

    /**
     * set up peer hand shale
     * @param info_hash hash information
     * @param peerid peer id
     * @return true if hand shack successful
     */
    private boolean sendHandshake(byte[] info_hash, byte[] peerid){
        PeerMessage handshake = new PeerMessage(info_hash, peerid);
        boolean ret;
        try{
            dOutStream.write(handshake.message);
            dOutStream.flush();
            socket.setSoTimeout(timeoutTime);
            byte[] receiveShake = new byte[68];
            dInStream.readFully(receiveShake);
            byte[] peerInfoHash = Arrays.copyOfRange(receiveShake, 28, 48);
            ret = Arrays.equals(peerInfoHash, info_hash);
            return ret;
        }catch(Exception e){
            System.out.println("Exception thrown for handshake");
        }
        return true;
    }

    /**
     * download the file
     * @return true if downloaded file successfully
     * @throws Exception io Exception
     */
    private boolean download() throws Exception{
        PeerMessage mainMessage = new PeerMessage(1, (byte) 2);
        PeerMessage request = null;
        byte[] buff = null;
        byte[] pieceSub = null;
        int lastPiece;
        int numPieces = 0;
        int begin = 0;
        int count;

        socket.setSoTimeout(timeoutTime);

        for(int i = 0; i < 6; i++){
            //System.out.println("got to i : " + i);
            dInStream.readByte();
        }
        dOutStream.write(mainMessage.message);
        dOutStream.flush();
        socket.setSoTimeout(timeoutTime);

        for(int i = 0; i < 5; i++){
            if(i == 4 && dInStream.readByte() == 1){
                break;
            }
            dInStream.readByte();
        }
        lastPiece = torrentInfo.file_length - (torrentInfo.piece_length * (torrentInfo.piece_hashes.length - 1)) - ( (torrentInfo.piece_length / KBLIM) - 1) * KBLIM;
        fOutStream = new FileOutputStream(new File(RUBTClient.file_save_name));
        boolean gotPiece = false;

        //set up while loop to see if we have all the pieces
        while(numPieces != torrentInfo.piece_hashes.length){
            //set up loop for each piece to download
            while(!gotPiece){
                if(numPieces+1 == torrentInfo.piece_hashes.length){
                    request = new PeerMessage(13, (byte) 6);
                    count = (lastPiece < KBLIM) ? lastPiece : KBLIM;
                    lastPiece -= KBLIM;
                    request.setLoad(-1, -1, null, numPieces, begin, count, -1);
                    dOutStream.write(request.message);
                    dOutStream.flush();
                    socket.setSoTimeout(timeoutTime);
                    buff = new byte[4];
                    int debug = 1;

                    for(int i = 0; i < 4; i++){
                        buff[i] = dInStream.readByte();
                    }

                    pieceSub = new byte[count];
                    debug++;

                    for(int i = 0; i < 9; i++){
                        dInStream.readByte();
                    }
                    debug++;

                    for(int i =0; i < count; i++)
                        pieceSub[i] = dInStream.readByte();

                    this.pieces.add(pieceSub);
                    fOutStream.write(pieceSub);
                    if(lastPiece < 0){
                        numPieces++;
                        gotPiece=true;
                        continue;
                    }
                    begin += count;
                } else{
                    request = new PeerMessage(13, (byte) 6);
                    request.setLoad(-1, -1, null, numPieces, begin, KBLIM, -1);
                    dOutStream.write(request.message);
                    dOutStream.flush();
                    socket.setSoTimeout(timeoutTime);
                    buff = new byte[4];
                    for(int i = 0; i < 4; i++)
                        buff[i] = dInStream.readByte();

                    for(int i = 0; i < 9; i++)
                        dInStream.readByte();

                    pieceSub = new byte[KBLIM];

                    for(int i = 0; i < KBLIM; i++)
                        pieceSub[i] = dInStream.readByte();

                    this.pieces.add(pieceSub);
                    fOutStream.write(pieceSub);

                    if(begin + KBLIM == torrentInfo.piece_length){
                        numPieces++;
                        begin = 0;
                        gotPiece=true;
                        float percentage = (float) numPieces / (float) torrentInfo.piece_hashes.length;
                        System.out.println("=============> "+percentage * 100 + "%");
                    }	else{
                        begin += KBLIM;
                    }
                }

            }
            gotPiece = false;
        }
        return true;

    }
    public void run(){
    }

}
