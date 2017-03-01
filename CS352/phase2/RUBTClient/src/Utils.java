import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by Abhi on 10/17/16.
 */
public class Utils {

    //hex array
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * converts hex string to encoded url
     * @param hexString string
     * @return encoded url as string
     * @throws Exception
     */
    public static String hexToEncodeURL(String hexString) throws Exception {
        int hexLength = hexString.length();
        if(hexString.isEmpty() || hexLength % 2 != 0){
            throw new Exception("Error while converting hex to URL: "+hexString);
        }
        char[] encodeURL = new char[hexLength+hexLength/2];
        int i=0;
        int j=0;
        while(i<hexLength){
            encodeURL[j++]='%';
            encodeURL[j++]=hexString.charAt(i++);
            encodeURL[j++]=hexString.charAt(i++);
        }
        return new String(encodeURL);
    }

    /**
     * converts a byte array to hex string
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Converts an integer to a byte to be stored in a byte array
     * @param number the number to be converted
     * @return bye the byte array that contains the number
     */
    @SuppressWarnings("unused")
    private static byte[] intToByte(int number) {
        ByteBuffer bye = ByteBuffer.allocate(4);
        bye.putInt(number);

        return bye.array();
    }
    
    /**
     * Generates a random peer id for use in the URL
     * @return byte[] the generated peer ID for us
     */
    public static byte[] generatePeerID() {
        Random blando = new Random();

        byte[] peer_id = new byte[20];

        // Go through the list to randomly generate the peer id
        for (int i = 0; i < 6; i++) {
            // Generates some numbers
            peer_id[i] = (byte) (blando.nextInt((57 - 48) + 1) + 48);
        }
        for (int i = 6; i < 20; i++) {
            // Generates some letters
            peer_id[i] = (byte) (blando.nextInt((122 - 97) + 1) + 97);
        }

        return peer_id;

    }


}
