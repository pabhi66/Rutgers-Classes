import GivenTools.BencodingException;
import GivenTools.TorrentInfo;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Abhishek Prajapati
 * Shridhar Oza
 */
public class RUBTClient {
    public static TorrentInfo torrentInfo;

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


        /**
         * read bytes from the torrent
         */
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(torrentName));
        } catch (FileNotFoundException e) {
            System.out.println("File not found please try again");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error while reading file");
            System.exit(0);
        }

        //parse torrent file using torrent info
        try {
            torrentInfo = new TorrentInfo(bytes);
        } catch (BencodingException e) {
            System.out.println("Bee Encoding error");
            System.exit(0);
        }

        //get the response from the peer
        byte[] peerID = Peer.getPID();
        byte[] response = null;
        try {
            response = Tracker.getURLRequest(torrentInfo,peerID);
        } catch (Exception e) {
            System.out.println("Error while getting tracker request");
            System.exit(0);
        }
        
        if(response == null){
        	System.out.println("Trecker did not connect sucessfully");
        }else{
        	System.out.println("Tracker connected sucessfully");
        }

        byte[] file = null;
        try {
            file = Peer.peerMain(torrentInfo,response);
        } catch (Exception e) {
            System.out.println("Error while getting peer response");
            System.exit(0);
        }

        if (file == null) {
            System.err.println("There was an error downloading the file.");
            System.exit(0);
        }
        
        System.out.println("Trying to download file " + torrentName + " and save it as " + fileSaveName);

        // Create and write the downloaded file
        FileOutputStream writer = new FileOutputStream(fileSaveName);
        writer.write(file);
        writer.close();

        // Contact the tracker and tell it that the download has completed
        Tracker.updateTracker(torrentInfo, peerID, "completed");

        // Notify the user the file has been downloaded and created
        System.out.println("\nFile: " + torrentInfo.file_name + " finished downloaded!");
        System.exit(0);

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
