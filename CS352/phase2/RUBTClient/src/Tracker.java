/**
 * Abhishek Prajapati
 * Shridhar Oza
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import GivenTools.TorrentInfo;

public class Tracker {

    // Start with port number 6881
    private static int portNum = 6881;

    /**
     * HTTP GET Request
     *
     * @param infoFile metadata for the torrent we are downloading.
     * @param peerID the peer ID that we created for our peer.
     * @return response a byte array of all the peers who responded to our GET request.
     */
    public static byte[] getURLRequest(TorrentInfo torrentInfo, byte[] peerID) throws Exception {

        /* If port 6881 fails, keep trying new port numbers Once all ports from 6881 to 6889 have
         * been tried, the tracker connection will fail */
        while (portNum < 6890) {
        	
        	String url = createURL(torrentInfo, peerID, -500, null);
        	System.out.println("------------------------------");
        	System.out.println("URL Connected: " + url);
        	System.out.println("------------------------------");
            System.out.println("Attempting on port: " + portNum);
            System.out.println("------------------------------");

            try {
                // Establishes the URL connection
                HttpURLConnection connection = decodeTracker(new URL(url.toString()));//(HttpURLConnection) get.openConnection();

                connection.setRequestMethod("GET");

                // Gets the data from the tracker
                DataInputStream i = new DataInputStream(connection.getInputStream());
                int len = (int) connection.getContentLengthLong();
                byte[] response = new byte[len];
                i.readFully(response);
                i.close();

                return response;

            } catch (MalformedURLException e) {
                System.err.println("There was a Malformed Exception");
                portNum++;
                continue;
            } catch (IOException e) {
                System.err.println("There was an IO Exception");
                portNum++;
                continue;
            }
        }

        System.out.println("All legal ports have been attempted.\nCould not connect to the tracker.");

        return null;
    }

    /**
     * Connects to the tracker and tells it the download has started
     *
     * @param torrent the metadata for our torrent
     * @param pID the generated peerID for us
     * @param event either 'started', 'completed', or 'stopped' to communicate the state of the download to the tracker
     */
    public static void updateTracker(TorrentInfo torrentInfo, byte[] pID, String event) throws Exception {

        int i = 6881;
        while (i < 6890) {

        	String url = createURL(torrentInfo, null, i, event);

            try {
                HttpURLConnection connection = decodeTracker(new URL(url.toString()));//(HttpURLConnection) con.openConnection();
                connection.connect();
                return;

            } catch (MalformedURLException e) {
                System.err.println("There was a Malformed Exception");
                i++;
            } catch (IOException e) {
                System.err.println("There was an IO Exception");
                i++;
            }
        }
    }
    
    private static HttpURLConnection decodeTracker(URL url) throws IOException{
    	return (HttpURLConnection)url.openConnection();
    }
    
    /**
     * create the url based on the given parameters
     * @param torrentInfo torrent info
     * @param peerID peer id
     * @param i port number
     * @param event event
     * @return url string 
     * @throws Exception IO Exception
     */
    private static String createURL(TorrentInfo torrentInfo,byte[] peerID, int i,String event) throws Exception{
    	StringBuilder sb = new StringBuilder();
    	if(event == null && i == -500){
    	sb.append(torrentInfo.announce_url+
        		"?info_hash="+
        		Utils.hexToEncodeURL(Utils.bytesToHex(torrentInfo.info_hash.array()))
        		+ "&peer_id=" + new String(peerID)+ "&port=" + portNum 
        		+"&left=" + torrentInfo.file_length + "&uploaded=" + 0 +"&downloaded=" + 0);
    	return sb.toString();
    	
    	}
        sb.append(torrentInfo.announce_url + "?info_hash="
        		+ Utils.hexToEncodeURL(Utils.bytesToHex(torrentInfo.info_hash.array())) 
        		+"&peer_id=" + "" + "&port=" + i + "&left=" + torrentInfo.file_length 
        		+"&uploaded=" + 0 + "&downloaded=" + 0+ "&event=" + event);
        return sb.toString();
    }
}

