/**
 * Created by Abhi on 10/21/14.
 */
public class tvGuide {
    public static void main(String[] args){
        int[] chanNum = {2,4,5,7,9,11};
        String[] chanName = {"cbs","nbc","fox","abc","my9","cw"};
        //System.out.println("Enter channel number");
        //System.out.println("Network " + channelLookUp(chanNum,chanName,IO.readInt()));
        System.out.println("Enter channel networkr");
        System.out.println("Channel number " + reverseLookup(chanNum,chanName,IO.readString()));
    }
    public static String channelLookUp(int[] chans, String[] nets, int chan){
        for(int i=0; i< chans.length; i++){
                if (chan == chans[i]) {
                    return nets[i];
                }
            }
        return "channel not found";
    }
    public static int reverseLookup(int[] chans, String[] nets, String net){
        for(int i=0; i<nets.length; i++) {
            if(net.equalsIgnoreCase(nets[i])) {
                return chans[i];
            }
        }
        return -1;
    }
}
