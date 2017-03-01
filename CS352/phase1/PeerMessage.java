/**
 * Created by Abhishek Prajapati
 * Shridhar Oza
 */

import java.util.Arrays;
public class PeerMessage {

    private static byte[] PROTOCOL_ID = new byte[] {'B', 'i', 't', 'T', 'o', 'r', 'r', 'e', 'n', 't', ' ',
            'p', 'r', 'o', 't', 'o', 'c', 'o', 'l'};

    //declare variables used throughout the message objects

    public byte[] message = null;        //the message itself
    private final int message_length;    //length of the message for use in byte array
    private final byte id;               //id of the message type
    private byte[] hash_info = null;     //id the hash key, used for handshake
    private byte[] pieceOfFile = null;   //this is the peice of the file that is sent in piece messages
    private byte[] peerID = null;        //idetifies the peer used in handshake messages

    /**
     * peer message constructor
     * @param info_hash hash information
     * @param peerID peer id
     */
    PeerMessage(byte[] info_hash, byte[] peerID){

        this.hash_info = info_hash;
        this.peerID = peerID;
        this.message_length = 0;
        this.id = 9;
        this.message = new byte[68];   //initialize the message
        this.message[0] = (byte) 19;   //put the length of the protocol id in the first spot of the array

        //fill the message with the protocol id, the hash info, then the peerid
        System.arraycopy( PROTOCOL_ID, 0, this.message, 1, 19);
        System.arraycopy( info_hash, 0, this.message, 28, 20);
        System.arraycopy( peerID, 0, this.message, 48, 20);


    }

    /**
     * peer message constructor
     * @param lengthPrefix prefix length
     * @param messageID message id
     */
    PeerMessage(int lengthPrefix, byte messageID){
        this.message_length = lengthPrefix;
        this.id = messageID;
        this.message = new byte[this.message_length + 4];

        if( this.id == 0){
            System.arraycopy(byteArrayFromInt(1), 0, this.message, 0, 4);
            this.message[4] = 0;

        }else if( this.id == 1){
            System.arraycopy(byteArrayFromInt(1), 0, this.message, 0, 4);
            this.message[4] = 1;

        }else if( this.id == 2){
            System.arraycopy(byteArrayFromInt(1), 0, this.message, 0, 4);
            this.message[4] = 2;

        }else if( this.id == 3){
            System.arraycopy(byteArrayFromInt(1), 0, this.message, 0, 4);
            this.message[4] = 3;

        }else if( this.id == 4){
            System.arraycopy(byteArrayFromInt(5), 0, this.message, 0, 4);
            this.message[4] = 4;

        }else if( this.id == 6){
            System.arraycopy(byteArrayFromInt(13), 0, this.message, 0, 4);
            this.message[4] = 6;

        }else if( this.id == 7){
            System.arraycopy(byteArrayFromInt(this.message_length), 0, this.message, 0, 4);
            this.message[4] = 7;
        }

    }

    /**
     * start download
     * @param pieceBegin where piece begains
     * @param pieceIndex piece index
     * @param pieceBlock piece block
     * @param requestIndex requested index
     * @param requestBegin requested piece's starting index
     * @param requestLength requested piece's length
     * @param havePayload have the piece
     */
    void setLoad(int pieceBegin, int pieceIndex, byte[] pieceBlock,
                 int requestIndex, int requestBegin, int requestLength,
                 int havePayload){

        if( this.id == 4){
            System.arraycopy(byteArrayFromInt(havePayload), 0, this.message, 5, 4);

        }
        else if( this.id == 7){
            this.pieceOfFile = pieceBlock;
            System.arraycopy(byteArrayFromInt(pieceIndex), 0, this.message, 5, 4);
            System.arraycopy(byteArrayFromInt(pieceBegin), 0, this.message, 9, 4);
            System.arraycopy(pieceBlock, 0, this.message, 13, this.message_length - 9);
        }
        else if( this.id == 6){
            System.arraycopy(byteArrayFromInt(requestIndex), 0, this.message, 5, 4);
            System.arraycopy(byteArrayFromInt(requestBegin), 0, this.message, 9, 4);
            System.arraycopy(byteArrayFromInt(requestLength), 0, this.message, 13, 4);
        }
    }

    /**
     * convert byte array from integer
     * @param num number
     * @return
     */
    private static byte[] byteArrayFromInt(int num){

        byte[] array = new byte[4];     //integers are of size 4 bytes

        //we use bitwise shifts to get the corresponding bytes
        array[0] = (byte) (num >> 24);
        array[1] = (byte) (num >> 16);
        array[2] = (byte) (num >> 8);
        array[3] = (byte) (num);

        return array;
    }


    //we should create a method that does the opposite for use in printing / debuging
    private static int intFromByteArray(byte[] array){
        return java.nio.ByteBuffer.wrap(array).getInt();
    }


    public String toString(){

        switch (this.id){
            case -1 : return  "Keep alive message";
            case 0 : return  "Choke message";
            case 1 : return  "Unchoke message";
            case 2 : return "Interested message";
            case 3: return "Not interested message";
            case 4: return "Have message";
            case 6: return "Request Message";
            case 9:{
                for(int i = 0; i <20 ; i++)
                    System.out.print( this.message[i]);
                System.out.println("Info Hash:");
                for(int i = 0; i > this.hash_info.length; i++)
                    System.out.print( this.hash_info[i] + " ");
                System.out.println("Peer ID:");
                for(int i = 0; i > peerID.length; i++)
                    System.out.print( peerID[i]);
                return "Handshake Message\n";
            }
            case 7 : {
                System.out.println("Piece Message");
                System.out.println("Piece index: " + intFromByteArray(Arrays.copyOfRange(this.message, 5, 8)));
                System.out.println("Begin Index: " + intFromByteArray(Arrays.copyOfRange(this.message, 9, 12)));
                System.out.println("Piece block: ");
                for (byte aPieceOfFile : this.pieceOfFile)
                    System.out.print(aPieceOfFile);
                return "Piece Message";
            }
        }
        return "";
    }
}
