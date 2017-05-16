/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Monitor extends ComputerPart{
    private String resolution = "";
    private double size = 0.0;
    private int responseTime = 0;
    boolean ips = false;

    public Monitor(String monData){
        super(monData);
        name = getNameFromMemData(monData);
        size = getSizeFromMemData(monData);
        responseTime = getResponseTimeFromMemData(monData);
        ips = getIpsFromMemData(monData);
    }

    private static String getResolutionFromMemData(String monData){
        return null;
    }

    private static double getSizeFromMemData(String monData){
        return 0.0;
    }

    private static int getResponseTimeFromMemData(String monData){
        return 0;
    }

    private static boolean getIpsFromMemData(String monData){
        return false;
    }

    private static String getNameFromMemData(String monData){
        return null;
    }

    public String getResolution() {
        return resolution;
    }

    public double getSize() {
        return size;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public boolean isIps() {
        return ips;
    }
}
