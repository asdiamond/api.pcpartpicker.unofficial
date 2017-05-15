import java.io.IOException;
import java.util.ArrayList;

public class CPU extends ComputerPart {
    private double price;
    private String name = "";
    private int cores;
    private int tdp;
    private double clockSpeed;

    public static ArrayList<CPU> parseCPUData(String url) throws IOException {
        String[] rawData = getRawData(url);

        ArrayList<CPU> cpuList = new ArrayList<>(rawData.length - 1);
        for (int i = 0; i < rawData.length - 1; i++) {//remember to neglect last element
            cpuList.add(i, new CPU(rawData[i]));
        }
        return cpuList;
    }

    private static double getPriceFromCPUData(String cpuData){
        double price = 0.0;
        try {
            price = Double.parseDouble(cpuData.substring(cpuData.indexOf("$") + 1));
        } catch(StringIndexOutOfBoundsException | NumberFormatException e ){
            //this means the price is blank on their website, just dont worry about it.
            price = 0.0;
        }
        return price;
    }

    private static int getTdpFromCPUData(String cpuData){
        String coresAndTDP;
        try {
            coresAndTDP = cpuData.substring(cpuData.indexOf("GHz") + 3, cpuData.lastIndexOf("W "));
        }catch (StringIndexOutOfBoundsException e){
            return -1;
        }
        if(coresAndTDP.charAt(1) == '0'){//if the second character is a 0, everything after that is TDP
            return Integer.parseInt(coresAndTDP.substring(1));
        }
        if(coresAndTDP.length() > 4){//if its more than 4 characters everything after the second character is TDP
            return Integer.parseInt(coresAndTDP.substring(2));
        }
        return Integer.parseInt(coresAndTDP.substring(1));//otherwise everything after the first character is TDP
    }

    private static int getCoresFromCPUData(String cpuData){
        String coresAndTDP;
        try {
            coresAndTDP = cpuData.substring(cpuData.indexOf("GHz") + 3, cpuData.lastIndexOf("W "));
        }catch (StringIndexOutOfBoundsException e){
            return -1;
        }
        if(coresAndTDP.charAt(1) == '0'){//if the second digit is a zero then you have a multiple of 10 cores
            //AND its impossible to have TDP starting with 0, so its a for sure case.
            return Integer.parseInt(coresAndTDP.substring(0, 2));
        }
        if(coresAndTDP.length() > 4){//we can pretty safely assume that none have over 1000 watts of TDP
            return Integer.parseInt(coresAndTDP.substring(0, 2));
        }
        if(cpuData.toLowerCase().contains("xeon")){//if we are dealing with a xeon
            if(Integer.parseInt("" + coresAndTDP.charAt(0)) > 4){
                return Integer.parseInt("" + coresAndTDP.charAt(0));
            }
            return Integer.parseInt(coresAndTDP.substring(0, 2));
        }

        return Integer.parseInt("" + coresAndTDP.charAt(0));
    }

    private static double getClockSpeedFromCPUData(String cpuData){
        double clockSpeed = 0.0;
        try{
            clockSpeed = Double.parseDouble(cpuData.substring(cpuData.indexOf("GHz") - 3, cpuData.indexOf("GHz")));
        } catch (StringIndexOutOfBoundsException | NumberFormatException e){
            clockSpeed = 0.0;
        }
        return clockSpeed;
    }

    private static String getNameFromCPUData(String cpuData){
        String name = "";
        try{
            name = cpuData.substring(0, cpuData.indexOf("GHz") - 3);
        } catch (StringIndexOutOfBoundsException e){
            name = "NONAME";
        }
        return name;
    }

    public CPU(String cpuData){
        //removes ratings
        if (cpuData.contains("(") && cpuData.contains(")")) {
            String removee = cpuData.substring(cpuData.indexOf("("), cpuData.indexOf(")") + 1);
            cpuData = cpuData.replace(removee, "");
        }
        this.price      = getPriceFromCPUData(cpuData);
        this.name       = getNameFromCPUData(cpuData);
        this.cores      = getCoresFromCPUData(cpuData);
        this.clockSpeed = getClockSpeedFromCPUData(cpuData);
        this.tdp        = getTdpFromCPUData(cpuData);
    }

    @Override
    public String toString() {
        return "name= " + this.name + "\nprice= " + this.price + "$\ncores= " + this.cores + "\ntdp= " + this.tdp
                + "W\nclockSpeed= " + this.clockSpeed + "GHz";
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getCores(){
        return this.cores;
    }

    public double getClockSpeed(){
        return this.clockSpeed;
    }

    public double getTdp(){
        return this.tdp;
    }
}