/**
 * Created by Aleksandr on 5/15/2017.
 */
public class GPU extends ComputerPart{
    private String series = "";
    private String chipSet = "";
    private int memory = 0;
    private double clock = 0.0;

    public GPU(String gpuData){
        super(gpuData);
        this.name = getNameFromgpuData(gpuData);
        this.series = getSeriesFromgpuData(gpuData);
        this.chipSet = getChipsetFromgpuData(gpuData);
        this.memory = getMemoryFromgpuData(gpuData);
        this.clock = getClockFromgpuData(gpuData);
    }

    private static String getSeriesFromgpuData(String gpuData){
        return null;
    }

    private static String getChipsetFromgpuData(String gpuData){
        return null;
    }

    private static int getMemoryFromgpuData(String gpuData){
        return 0;
    }

    private static double getClockFromgpuData(String gpuData){
        return 0.0;
    }

    private static String getNameFromgpuData(String gpuData){
        return null;
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
