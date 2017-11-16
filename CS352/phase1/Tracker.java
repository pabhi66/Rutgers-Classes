/**
 * Created by Abhishek Prajapati
 * Shridhar Oza
 */

import GivenTools.BencodingException;
import GivenTools.TorrentInfo;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {

    public int interval; //interval
    public int minInterval; //minimum interval
    public int complete; //pieces completed
    public int incomplete; //pieces in-completed
    public ArrayList<Peer> peers; //array list to hold all the peers

    /**
     * default tracker constructor
     */
    public Tracker(){
    }

    /**
     * tracker constructor
     * @param tracker_response tracker response
     * @throws Exception io exception
     */
    private Tracker(HashMap<ByteBuffer, Object> tracker_response)throws Exception{
        readTrackerResponse(tracker_response);
        addPeer(tracker_response);
    }

    /**
     * read the tracker response and print out the message
     * @param tracker_response tracker response
     * @throws Exception io exception
     */
    private void readTrackerResponse(HashMap<ByteBuffer, Object> tracker_response) throws Exception{
        if(tracker_response.containsKey(ByteBuffer.wrap(new byte[]{'f', 'a', 'i','l','u', 'r', 'e', ' ', 'r', 'e', 'a', 's', 'o', 'n',})))
            throw new Exception("Tracker failed to response");
        if (tracker_response.containsKey(ByteBuffer.wrap(new byte[] {'c', 'o', 'm', 'p', 'l', 'e', 't', 'e' })))
            this.complete = (Integer) tracker_response.get(ByteBuffer.wrap(new byte[] {'c', 'o', 'm', 'p', 'l', 'e', 't', 'e' }));
        else
            this.complete = 0;
        if(tracker_response.containsKey(ByteBuffer.wrap(new byte[] {'i', 'n', 't', 'e', 'r', 'v', 'a','l' })))
            this.interval = (Integer) tracker_response.get(ByteBuffer.wrap(new byte[] {'i', 'n', 't', 'e', 'r', 'v', 'a','l' }));
        else
            this.interval = 0;
        if (tracker_response.containsKey(ByteBuffer.wrap(new byte[] { 'm', 'i', 'n', ' ', 'i', 'n', 't', 'e', 'r','v', 'a', 'l' })))
            this.minInterval = (Integer) tracker_response.get(ByteBuffer.wrap(new byte[] { 'm', 'i', 'n', ' ', 'i', 'n', 't', 'e', 'r','v', 'a', 'l' }));
        else
            this.minInterval = 0;
        if (tracker_response.containsKey(ByteBuffer.wrap(new byte[] {'i', 'n', 'c', 'o', 'm', 'p', 'l', 'e', 't', 'e' })))
            this.incomplete = (Integer) tracker_response.get(ByteBuffer.wrap(new byte[] {'i', 'n', 'c', 'o', 'm', 'p', 'l', 'e', 't', 'e' }));
        else
            this.incomplete = 0;
    }

    /**
     * add peer with RU prefix to the peer array list
     * @param tracker_response tracker response
     */
    private void addPeer(HashMap<ByteBuffer, Object> tracker_response){
        this.peers = new ArrayList<Peer>(); //create new array list

        //iterate through the man and get right peers
        for (Object element : (ArrayList<?>) tracker_response.get(ByteBuffer.wrap(new byte[] {'p', 'e', 'e', 'r', 's' })) ){
            //@SuppressWarning("unchecked");
            Map<ByteBuffer, Object> peerMap= (Map<ByteBuffer, Object>)element;

            //if the peer is missing information then do not add them to array list
            if(!peerMap.containsKey(ByteBuffer.wrap(new byte[] {'p', 'o', 'r', 't'})) || !peerMap.containsKey(ByteBuffer.wrap(new byte[] { 'i','p' })) || !peerMap.containsKey(ByteBuffer.wrap(new byte[] {'p', 'e', 'e', 'r', ' ', 'i', 'd' })))
                continue;

            //get the port number of peer
            int peerPort = (Integer) peerMap.get(ByteBuffer.wrap(new byte[] {'p', 'o', 'r', 't'}));
            //get the peer ip address
            String peerIP = objectToStr(peerMap.get(ByteBuffer.wrap(new byte[] { 'i','p' })));
            //get peer id
            String peerID = objectToStr(peerMap.get(ByteBuffer.wrap(new byte[] {'p', 'e', 'e', 'r', ' ', 'i', 'd' })));
            //byte[] pid = ((ByteBuffer) peerMap.get(KEY_PEER_ID)).array();

            if(objectToStr(peerMap.get(ByteBuffer.wrap(new byte[] {'p', 'e', 'e', 'r', ' ', 'i', 'd' }))).contains("-RU")){
                System.out.println("Peer with RU prefix found: " + peerID);
                this.peers.add(new Peer(peerPort, peerIP));
            }
        }
    }

    /**
     * create URL to download the file from and connect tracker
     * @param torrentInfo torrent info
     * @return tracker in bytes
     */
    public static byte[] createURL(TorrentInfo torrentInfo){
        String infoHash = null, peerID = null;
        try {
            infoHash = "?info_hash="+ Utils.hexToEncodeURL(Utils.bytesToHex(torrentInfo.info_hash.array()));
            peerID = "&peer_id="+Utils.hexToEncodeURL(Utils.bytesToHex("abhishekprajapati123".getBytes()));
        } catch (Exception e) {
            System.out.println("Error: while converting hex to Encoded URL");
        }
        String Port = "&port="+torrentInfo.announce_url.getPort();
        String uploaded = "&uploaded="+0;
        String downloaded = "&downloaded="+0;
        String left = "&left="+torrentInfo.file_length;

        String url = torrentInfo.announce_url.toString()+infoHash+peerID+Port+uploaded+downloaded+left;

        System.out.println("The Current URL is : " + url);

        return connectTracker(url);
    }

    /**
     * connect to tracker
     * @param url url
     * @return tracker in bytes
     */
    private static byte[] connectTracker(String url){
        HttpURLConnection connection = null;
        DataInputStream dataInputStream = null;
        try {
            connection = (HttpURLConnection)new URL(url).openConnection();
        } catch (IOException e) {
            System.out.println("Error: while connecting trackers");
        }

        try {
            assert connection != null;
            dataInputStream= new DataInputStream(connection.getInputStream());
        } catch (IOException e) {
            System.out.println("Error: unable to read from tracker");
        }

        byte[] returnBytes = new byte[connection.getContentLength()];
        try {
            assert dataInputStream != null;
            dataInputStream.readFully(returnBytes);
            dataInputStream.close();
        } catch (IOException e) {
            System.out.println("Error: unable to read the bytes from tracker");
        }
        return returnBytes;
    }

    /**
     * decode the tracker
     * @param tracker tracker
     * @return tracker
     */
    public static Tracker decodeTracker(byte[] tracker){
        Object object = null;
        try {
            object = GivenTools.Bencoder2.decode(tracker);
        } catch (BencodingException e) {
            e.printStackTrace();
        }
        HashMap<ByteBuffer, Object> response = (HashMap<ByteBuffer, Object>) object;
        try {
            return new Tracker(response);
        } catch (Exception e) {
            System.out.println("Error: unable to read tracker response");
        }
        return null;
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
}
