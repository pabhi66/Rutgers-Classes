/**
 * Created by Abhi on 10/17/16.
 */
import GivenTools.*;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RUBTClient {
    public static String file_save_name;
    public static byte[] peer_id = "abhishekprajapati123".getBytes();
    public static TorrentInfo torrentInfo = null;

    /**
     * Main function
     * @param args command line arguments (file_name.torrent, safe_file_name)
     * @throws Exception throws IO Exception
     */
    public static void main(String[] args) throws Exception {

        /**
         * Check if the user enters right number of argument else end and print message
         */
        if (args.length != 2) {
            System.out.println("Usage: java RUBTClient <torrent_file_name.torrent> <name to save the file as>\n");
            return;
        }

        /**
         *validate the file name extension to see if .torrent file is selected and also check for valid save file name
         */
        else if (!validate(args[0], args[1])) {
            System.out.println("Please Enter valid name for the file");
            System.out.println("Usage: java RUBTClient <torrent_file_name.torrent> <name to save the file as>\n");
        }

        /**
         * store the torrent name received from command line into torrentName
         * store the name of the file you want to save as in fileSaveName
         */
        String torrentName = args[0];
        String fileSaveName = args[1];
        file_save_name = fileSaveName;


        /**
         * read bytes from the torrent
         */
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(torrentName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found please try again");
        }

        //create torrent info from the bytes
        torrentInfo = new TorrentInfo(bytes);
        //get the tracker response from the torrent info
        byte[] tracker_response = Tracker.createURL(torrentInfo);
        //decode the tracker
        Tracker trackerDecoded = Tracker.decodeTracker(tracker_response);

        //create arraylist to hold all he peers
        assert trackerDecoded != null;
        ArrayList<Peer> peers = trackerDecoded.peers;

        for (Peer peer : peers) {
            new Peer(peer.port_number, peer.ip_address, torrentInfo, peer_id);

        }
    }

    /**
     * /**
     * The validate method returns true if the input arguments are valid, and false otherwise.
     *
     * @param torrentFile - the name of the torrent file
     * @param saveFile - name of the file to be downloaded
     */
    public static boolean validate(String torrentFile, String saveFile){
        if(saveFile.isEmpty() || torrentFile.isEmpty() || !torrentFile.endsWith(".torrent"))
            return false;
        return true;
    }
}
