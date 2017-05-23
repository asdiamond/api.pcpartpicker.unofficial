/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Monitor {
    private String resolution = "";
    private double size = 0.0;
    private int responseTime = 0;
    boolean ips = false;

    public Monitor(String monData){
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
