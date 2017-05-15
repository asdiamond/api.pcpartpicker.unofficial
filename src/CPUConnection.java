import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class CPUConnection{
    public static ArrayList<CPU> parseCPUData(String url) throws IOException {

        String[] rawData = Test.getRawData(url);

        ArrayList<CPU> cpuList = new ArrayList<>(rawData.length - 1);
        for (int i = 0; i < rawData.length - 1; i++) {//remember to neglect last element
            //removes ratings
            if (rawData[i].contains("(") && rawData[i].contains(")")) {
                String removee = rawData[i].substring(rawData[i].indexOf("("), rawData[i].indexOf(")") + 1);
                rawData[i] = rawData[i].replace(removee, "");
            }
            CPU curr = new CPU();
            curr.setTdp(getTdpFromCPUData(rawData[i]));
            curr.setCores(getCoresFromCPUData(rawData[i]));
            curr.setName(getNameFromCPUData(rawData[i]));
            curr.setClockSpeed(getClockSpeedFromCPUData(rawData[i]));
            curr.setPrice(getPriceFromCPUData(rawData[i]));
            System.out.println(curr);
            System.out.println();
            cpuList.add(i, curr);
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

}


