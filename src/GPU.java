/**
 * Created by Aleksandr on 5/15/2017.
 */
public class GPU  {
    private String series = "";
    private String chipSet = "";
    private int memory = 0;
    private double clock = 0.0;

    public GPU(String gpuData){
    }

    public String getSeries() {
        return series;
    }

    public String getChipSet() {
        return chipSet;
    }

    public int getMemory() {
        return memory;
    }

    public double getClock() {
        return clock;
    }
}
