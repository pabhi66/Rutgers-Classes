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


}
