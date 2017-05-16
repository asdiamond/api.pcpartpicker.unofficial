import java.io.IOException;
import java.util.ArrayList;

public class CPUCooler extends ComputerPart {
    private String fanRpm = "";
    private String noiseLevel = "";

    public CPUCooler(String coolerData){
        super(coolerData);
        super.name       = getNameFromCoolerData(coolerData);
        this.fanRpm     = getFanRpmFromCoolerData(coolerData);
        this.noiseLevel = getNoiseLevel(coolerData);
    }

    public static ArrayList<CPUCooler> parseCPUCoolerData(String url) throws IOException {
        String [] rawData = getRawData(url);

        ArrayList<CPUCooler> coolers = new ArrayList<>(rawData.length - 1);
        for (int i = 0; i < rawData.length - 1; i++) {
            coolers.add(i, new CPUCooler(rawData[i]));
        }
        return coolers;
    }

    private static String getNameFromCoolerData(String coolerData){
//        return coolerData.substring(0, coolerData.indexOf("RPM") - 3);
        return null;
    }

    private static String getFanRpmFromCoolerData(String coolerData){
//        return coolerData.substring(coolerData.indexOf("RPM") + 3, coolerData.indexOf(" dbA") - 4);
        return null;
    }

    private static String getNoiseLevel(String coolerData){
        String noiseLevel = null;
        try {
            noiseLevel = coolerData.substring(coolerData.indexOf("RPM") + 3, coolerData.indexOf(" dbA"));
        }catch (StringIndexOutOfBoundsException e){
            //there are some with no noise level
            noiseLevel = "";
       }
       return noiseLevel;
    }

    @Override
    public String toString(){
        return "name = " +  this.name + "\nprice = " + super.getPrice() + "\nfan rpm = " + fanRpm + "\nnoise level = " + this.noiseLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFanRpm() {
        return fanRpm;
    }

    public void setFanRpm(String fanRpm) {
        this.fanRpm = fanRpm;
    }

    public String getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(String noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

}
