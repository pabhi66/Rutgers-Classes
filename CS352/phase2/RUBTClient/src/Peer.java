/**
 * Abhishek Prajapati
 * Shridhar Oza
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import GivenTools.Bencoder2;
import GivenTools.BencodingException;
import GivenTools.TorrentInfo;

public class Peer {

    // The peer ID we must check for in the list of retrieved peers
    public static final byte[] PEER_ID_TO_MATCH = new byte[] { 'R', 'U' };

    // The protocol that is used in the handshake message
    public static final byte[] BT_PROTOCOL = new byte[] { 'B','i','t','T','o','r','r','e','n','t',' ','p','r','o','t','o','c','o','l' };

    // ArrayList to hold the piece IDs
    private static final ArrayList<Integer> pieces = new ArrayList<>();
    
    // ArrayList to hold the peerIDs from the tracker
    private static final ArrayList<String> peerIDList = new ArrayList<>();

    // ArrayList to hold all of the peer IPs and ports
    private static ArrayList<String> peers = new ArrayList<>();

    // Inits the IDs of the different kinds of messages
//    private static final byte CHOKE_ID = 0;
//    private static final byte INTERESTED_ID = 2;
//    private static final byte UNCHOKE_ID = 1;
//    private static final byte N_INTERESTED_ID = 3;
//    private static final byte BITFIELD_ID = 5;
//    private static final byte HAVE_ID = 4;
//    private static final byte REQUEST_ID = 6;
//    private static final byte CANCEL_ID = 8;
//    private static final byte PIECE_ID = 7;
//    private static final byte PORT_ID = 9;

    // Our own peer ID.
    private static final byte[] pID = Utils.generatePeerID();

    /**
     * The "main" function for this peer class
     * All the peers functions are run through here
     * @param info Metainfo for the torrent file
     * @param response A list of peers returned from the tracker.
     */
    @SuppressWarnings("rawtypes")
    public static byte[] peerMain(TorrentInfo info, byte[] response) throws Exception {

        Map oResponse = decodeResponse(response);
        peers = getPeersFromReponse(oResponse);

        final String[] peerIP = findFastestPeer();

        byte[] t = downloadFromPeer(peerIP, info);
        return t;
    }
    
    /**
     * convert object to string
     * @param o object
     * @return string object
     */
    private static String objectToStr(Object o){

        if(o instanceof Integer)
            return String.valueOf(o);
        else if(o instanceof ByteBuffer){
            try {
                return new String(((ByteBuffer) o).array(),"ASCII");
            } catch (UnsupportedEncodingException e) {
                return o.toString();
            }
        }else if(o instanceof Map<?,?>){
            String retStr = "";
            for (Object name: ((Map<?, ?>) o).keySet()){
                String value = objectToStr(((Map<?, ?>) o).get(name));
                retStr += objectToStr(name) + ": " + value + "\n";
            }

            return retStr;
        }else if(o instanceof List){
            String retStr = "";
            for(Object elem: (List<?>)o){
                retStr += objectToStr(elem) + "\n";
            }
            return retStr;
        }
        return o.toString();
    }

    /**
     * Loops through every peer, pings each of them several times, and returns the one with the
     * smallest RTT.
     * @return An array of size two where the first element is the peer's IP address
     * and the second element is the peer's port number.
     * @throws IOException if a network error occurs.
     * @throws UnknownHostException if InetAddress.getByName could not find the IP.
     */
    private static String[] findFastestPeer() throws UnknownHostException, IOException {

        String[] fastestPeer = null;
        long shortestTime = Long.MAX_VALUE;

        System.out.println("------------------------------");
        System.out.println("\nList of Peers founded with RU prefix and their average RTT are following:");
        for (int i = 0; i < peers.size(); ++i) {

            // Only ping peers whose ID begins with RU
            if (!verifyID(i)) {
                continue;
            }

            String[] peerIP = peers.get(i).split(":");

            // Start timing the pings
            final String ip = peerIP[0];
            final int maxPings = 10; // 10 is small but recommended by assignment description
            long totalTime = 0;

            // Ping the IP ten times and add all of the times together.
            for (int numberOfPings = 0; numberOfPings < maxPings; ++numberOfPings) {

                final long startTime = System.nanoTime();
                InetAddress.getByName(ip).isReachable(1000);
                final long finishedTime = System.nanoTime();

                totalTime += finishedTime - startTime;
            }

            final long averageTime = totalTime / maxPings;
            System.out.println("Peer with RU prefix: " + peerIP[0] + ", average time to connect to this peer is: " + averageTime + "ns");

            if (averageTime < shortestTime) {
                shortestTime = averageTime;
                fastestPeer = peerIP;
            }
        }
        System.out.println("------------------------------");
        System.out.println("\nFastest peer to connect is: " + Arrays.toString(fastestPeer) + "\n");
        System.out.println("------------------------------");
        return fastestPeer;
    }

    /**
     * Decodes the response from the tracker
     * @param response A list of peers returned from the tracker.
     * @return oResponse, the decoded response
     */
    @SuppressWarnings("rawtypes")
    public static Map decodeResponse(byte[] response) throws BencodingException {
 
    	return (Map)Bencoder2.decode(response);
    }

    /**
     * Gets the list of peerIDs, peerIPs, and peer ports from the parsed response
     * @param map the deocded response
     * @return peerURLs ArrayList of type String that holds the peer information
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ArrayList<String> getPeersFromReponse(Map map) throws UnsupportedEncodingException {

        // Init the byte fields for the peer information
        // Using 50 for the byte size so there is less of a chance of an overflow
        byte[] id = new byte[50];
        byte[] ip = new byte[50];
        int port = 0;

        ArrayList<ByteBuffer> peerKey = (ArrayList) map.get(ByteBuffer.wrap("peers".getBytes()));
        ArrayList<String> peerInfo = new ArrayList<>();

        Iterator i = peerKey.iterator();
        Object o;

        // Iterate through the list of dictionaries that hold the peer information
        while (i.hasNext() && (o = i.next()) != null) {
            Map p = (Map) o;

            // Gets the port information
            port = (int) p.get(ByteBuffer.wrap("port".getBytes()));

            // Gets the peer ip information
            Object peersipO = p.get(ByteBuffer.wrap("ip".getBytes()));
            ByteBuffer ipo = (ByteBuffer) peersipO;
            ip = ipo.array();

            // Gets the peer id information
            Object peersidO = p.get(ByteBuffer.wrap("peer id".getBytes("utf8")));
            ByteBuffer ido = (ByteBuffer) peersidO;
            id = ido.array();

            // Add the peer information to the peerInfo ArrayList separated by a ':'
            // Add the peer id to peerID ArrayList
            peerInfo.add(new String(ip) + ":" + port);
            peerIDList.add(new String(id));
        }

        return peerInfo;
    }

    /**
     * Downloads the piece from the peer
     * @param peerIP An array of size two where the first element is the peer's IP address
     * and the second element is the peer's port number.
     * @param torrent Metadata for the torrent we are downloading.
     * @return A byte array containing all of the byte sof the downloaded file in correct order.
     * @throws Exception
     */
    public static byte[] downloadFromPeer(String[] peerIP, TorrentInfo torrent) throws Exception {

        // The byte array that will hold the bytes of the put-together file
        byte[] fileBytes = new byte[torrent.file_length];

        // Init the socket
        Socket peerSock = new Socket(peerIP[0], Integer.parseInt(peerIP[1]));
        // This is data to be sent to the peer
        OutputStream toPeer = peerSock.getOutputStream();
        // This is data to be received from the peer
        InputStream fromPeer = peerSock.getInputStream();

        // Create and verify the handshake message
        byte[] message = handshake(torrent);

        // If the handshake is verified, move onto creating messages
        if (verifyHandshake(message, toPeer, fromPeer)) {
        	System.out.println("------------------------------");
            System.out.println("Handshake with peer accepted!");
            System.out.println("------------------------------");

            // Create and send the interested message
            byte[] interestedMsg = makeMessage(1, 2, 5, -1, -1, -1);
            if (interestedMsg == null) {
            	System.out.println("------------------------------");
                System.err.println("There was a problem in creating the interested message.");
                System.out.println("------------------------------");
                closeConnections(peerSock, fromPeer, toPeer);
                return null;
            }
            System.out.println("------------------------------");
            System.out.println("Sending message to the peer...");
            System.out.println("------------------------------");
            toPeer.write(interestedMsg);

            // Checks for the unchoke message
            while (!decodeMessage(message).equals("unchoke")) {

                message = getMessage(fromPeer, interestedMsg.length);
            }
            System.out.println("------------------------------");
            System.out.println("Peer responded with unchoke message!");
            System.out.println("------------------------------");
            // Set block size to be 16KB
            int block_size = torrent.piece_length / 2;
            // Gets number of pieces to be downloaded
            int numPieces = torrent.piece_length;
            // Number of blocks
            int numBlocksPerPiece = numPieces / block_size;
            // Length of the piece
            int pLength;

            // Loops through the number of pieces and gets them
            for (int i = 0; i < torrent.piece_hashes.length; i++) {
                pieces.add(i);
            }

            // Connect to the tracker to tell it that the download has started
            System.out.println("------------------------------");
            Tracker.updateTracker(torrent, pID, "started");
            System.out.println("Starting download ....\n");
            System.out.println("------------------------------");

            for (int i = 0; i < torrent.piece_hashes.length; i++) {
                System.out.println("Downloading Piece: " + (i + 1) + " out of " 
                		+ torrent.piece_hashes.length + " =====>  " + (float) (i+1)/torrent.piece_hashes.length + "%");

                // Check if this is the last piece, and if it is, calculate the new piece length
                if (i == torrent.piece_hashes.length - 1) {
                    pLength = torrent.file_length - torrent.piece_length * (torrent.piece_hashes.length - 1);
                }
                else {
                    pLength = torrent.piece_length;
                }

                // Send the request message
                byte[] requestMsg = makeMessage(13, 6, 17, i, 0, pLength);
                toPeer.write(requestMsg);

                // Get the piece from the peer
                byte[] newPiece = getPiece(fromPeer, numBlocksPerPiece, pLength);
                if (newPiece == null) {
                	System.out.println("------------------------------");
                    System.err.println("There was a problem downloading piece: " + (i + 1));
                    System.out.println("------------------------------");
                    break;
                }

                // Verify that the piece hash matches up with the hash from the torrent file
                if (verifyPieceHash(newPiece, i, torrent)) {
                    // The have message, telling the peer we now have the piece of the file
                    toPeer.write(makeMessage(5, 4, 9, i, -1, -1));
                    // Write the piece to the file bytes
                    System.arraycopy(newPiece, 0, fileBytes, torrent.piece_length * i, newPiece.length);
                }
                else {
                	System.out.println("------------------------------");
                    System.err.println("Piece hash did not match.");
                    System.out.println("------------------------------");
                    break;
                }
            }

        }
        else {
        	System.out.println("------------------------------");
            System.err.println("There was a problem contacting the peer.\nHandshake did not match.");
            System.out.println("------------------------------");
        }

        closeConnections(peerSock, fromPeer, toPeer);
        return fileBytes;
    }

    /**
     * Gets the piece from the peer following the request message
     * @param fromPeer input stream
     * @param blocksPerPiece number of blocks per piece of the file
     * @param length length of the piece
     * @return pieceData the byte array of all the bytes of that peer
     * @throws Exception
     */
    public static byte[] getPiece(InputStream fromPeer, int blocksPerPiece, int length) throws Exception {
        byte[] newPiece = null;
        byte[] pieceData = null;

        // Using 13+length because we are interested in the piece data, not the actual message preceding it
        newPiece = getMessage(fromPeer, 13 + length);

        if (decodeMessage(newPiece).equals("piece")) {
            pieceData = getActualPieceInfo(newPiece, 13);
        }

        return pieceData;
    }

    /**
     * Separates the message component from the actual piece bytes
     * @param oldPiece, the full piece
     * @param headerLength, the length of the header (message length, ID, etc.))
     * @return piece, the bytes of the piece that actually contain the information
     */
    private static byte[] getActualPieceInfo(byte[] oldPiece, int headerLength) {
        int pieceLength = oldPiece.length - headerLength;
        byte[] newPiece = new byte[pieceLength];

        System.arraycopy(oldPiece, headerLength, newPiece, 0, pieceLength);
        // System.arraycopy(src, srcPos, dest, destPos, length);
        return newPiece;
    }

    /**
     * Takes in the message received from the peer and figures out which message the peer sent
     * @param message the message to be decoded
     * @return decoded message the type of message that was passed in
     */
    private static String decodeMessage(byte[] message) {

        if (message.length < 4) {
            return null;
        }
        else if (message.length == 4) {
            return "keep-alive";
        }
        else {
            switch (message[4]) {
                case 0:
                    return "choke";
                case 1:
                    return "unchoke";
                case 2:
                    return "interested";
                case 3:
                    return "not_interested";
                case 4:
                    return "have";
                case 5:
                    return "bitfield";
                case 6:
                    return "request";
                case 7:
                    return "piece";
                case 8:
                    return "cancel";
                case 9:
                    return "port_id";
                default:
                    return "invalid";
            }
        }
    }

    /**
     * Closes the connections to the peer
     * @param peerSock
     * @param fromPeer
     * @param toPeer
     * @throws IOException
     */
    private static void closeConnections(Socket peerSock, InputStream fromPeer, OutputStream toPeer) throws IOException {
        fromPeer.close();
        toPeer.close();
        peerSock.close();
    }

    /**
     * Compares the SHA-1 encoded piece from the peer, to the actual piece hash fromt he torrent file
     * @param index the place at which the hash is in the torrent file hash list
     * @param torrent the torrent with all the metadata
     * @return boolean if the piece has is the same as the torrent file
     */
    private static boolean verifyPieceHash(byte[] piece, int index, TorrentInfo torrent) {
        byte[] hash = encodeToSHA1(piece);
        byte[] b = torrent.piece_hashes[index].array();

        if (Arrays.equals(hash, b)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Encodes the sent in piece to SHA-1 hash in order to compare it to the hash that is in the torrent file
     * @param toEncode, the piece that is encoded
     * @return sha1Encoded piece hash
     */
    private static byte[] encodeToSHA1(byte[] toEncode) {

        byte[] sha1Encoded = null;

        try {
            MessageDigest encoder = MessageDigest.getInstance("SHA-1");
            sha1Encoded = encoder.digest(toEncode);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-1 failed to encode the piece.");
        }

        return sha1Encoded;

    }

    /**
     * Returns the peer ID from the list of peers
     * @param peerIP, the ID of the peer
     * @return peerIP, the IP address that matches the peerID
     */
    @SuppressWarnings("unused")
    private static String getPeerID(String peerIP) {
        while (true) {
            for (int i = 0; i < peers.size(); i++) {
                if (peers.get(i).equals(peerIP)) {
                    return peerIDList.get(i);
                }
            }
            break;
        }
        return null;
    }

    /**
     * Checks to see if the returned handshake is viable
     *
     * @param message the created handshake
     * @param toPeer
     * @param fromPeer
     * @return boolean if the handshake is valid
     * @throws IOException
     */
    private static boolean verifyHandshake(byte[] message, OutputStream toPeer, InputStream fromPeer) throws IOException {

        byte[] response = new byte[49 + BT_PROTOCOL.length];

        try {
            toPeer.write(message);
        } catch (IOException e) {
        	System.out.println("------------------------------");
            System.err.println("Could not send message to the peer.");
            System.out.println("------------------------------");
        }

        fromPeer.read(response);

        // Check if the two byte arrays are the same
        // If they are the same, then the info hash, and the peer IDs will be the same
        if (checkHash(message, response)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks to see if the two handshake info hashes from the peer and from the user client are the same
     *
     * @param message the created handshake
     * @param response the incoming handshake
     * @return boolean if the hashes match
     */
    private static boolean checkHash(byte[] message, byte[] response) {

        for (int i = 0; i < 20; i++) {
            if (message[i + 28] != response[i + 28]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verify that the peer ID is correct by comparing it to the peer prefix.
     * @param index The index of peer ID to verify in peerIDList.
     * @return true if the peer ID matches the prefix, false otherwise.
     */
    private static boolean verifyID(int index) {
        final String peerID = peerIDList.get(index);
        final byte[] prefixBytes = peerID.substring(1, 3).getBytes();
        return Arrays.equals(prefixBytes, PEER_ID_TO_MATCH);
    }

    /**
     * Creates the handshake message
     *
     * @param info the torrent metadata
     * @return handshakeMessage the created message
     * @throws IOException
     */
    private static byte[] handshake(TorrentInfo info) throws IOException {
        int i = 0;
        int count;
        byte[] handshakeMessage = new byte[49 + BT_PROTOCOL.length];
        byte[] infoHash = info.info_hash.array();

        // The pstrlen
        handshakeMessage[0] = (byte) 19;

        // Combine the pstr with the pstrlen
        for (i = 1; i <= BT_PROTOCOL.length; i++) {
            handshakeMessage[i] = BT_PROTOCOL[i - 1];
        }
        for (count = 0; count < 8; count++) {
            handshakeMessage[i + count] = (byte) 0;
        }
        i += count;

        // Add info_hash to the end of the array
        for (count = 0; count < infoHash.length; count++) {
            handshakeMessage[i + count] = infoHash[count];
        }
        i += count;

        // Add peer ID to the end of the array
        for (count = 0; count < pID.length; count++) {
            handshakeMessage[i + count] = pID[count];
        }
        i += count;

        return handshakeMessage;
    }

    /**
     * Makes the messages to be sent to the peer
     *
     * @param lengthPrefix the prefix length, first part of message
     * @param messageID the message ID, second part of message
     * @param expectedMessageSize the expected response message size
     * @param index the piece index
     * @param begin the zero-based byte offset
     * @param length variable length of bytes
     * @return the created message
     * @throws Exception
     */
    public static byte[] makeMessage(int lengthPrefix, int messageID, int expectedMessageSize, int index, int begin, int length) throws Exception {
        // The message to send
        byte[] message = new byte[expectedMessageSize];
        int i = 0;

        // Set first three 0s for the len
        for (i = 0; i < 3; i++) {
            message[i] = (byte) 0;
        }
        // Set the last byte of the len
        message[i] = (byte) lengthPrefix;
        i++;
        // Set the ID
        switch (messageID) {
            case 0:
                message[i] = 0;
                break;
            case 1:
                message[i] = 1;
                break;
            case 2:
                message[i] = 2;
                break;
            case 3:
                message[i] = 3;
                break;
            case 4:
                message[i] = 4;
                break;
            case 5:
                message[i] = 5;
                break;
            case 6:
                message[i] = 6;
                break;
            case 7:
                message[i] = 7;
                break;
            case 8:
                message[i] = 8;
                break;
            case 9:
                message[i] = 9;
                break;
            default:
                throw new Exception("Not a valid message ID.");
        }
        i++;

        if (index >= 0) {
            byte[] temp = ByteBuffer.allocate(4).putInt(index).array();
            System.arraycopy(temp, 0, message, i, temp.length);
            i += 4;
        }

        if (begin >= 0) {
            byte[] temp = ByteBuffer.allocate(4).putInt(begin).array();
            System.arraycopy(temp, 0, message, i, temp.length);
            i += 4;
        }

        if (length >= 0) {
            byte[] temp = ByteBuffer.allocate(4).putInt(length).array();
            System.arraycopy(temp, 0, message, i, temp.length);
        }

        return message;

    }

    /**
     * Returns the message the peer sent to us
     *
     * @param fromPeer
     * @param size the size of the expected message
     * @return message the message sent by the peer that we are receiving
     * @throws IOException
     */
    private static byte[] getMessage(InputStream fromPeer, int size) throws IOException {

        int messageLength;

        // While there are still bytes to read, and they don't exceed the size of the needed response,
        // we change the message length
        while ((messageLength = fromPeer.available()) < size) {
            // do nothing
        }

        byte[] message = new byte[messageLength];
        fromPeer.read(message);

        return message;
    }

    public static byte[] getPID() {
        return pID;
    }
}

