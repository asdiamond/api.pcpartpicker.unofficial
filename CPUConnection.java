import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class CPUConnection{
    public static ArrayList<CPU> parseCPUData(String url) throws IOException {
        Document doc = null;
//            doc = Jsoup.connect("https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=")
//            doc = Jsoup.connect("https://pcpartpicker.com/products/cpu/fetch/?sort=d3&page=1&mode=list&xslug=&search=")
            doc = Jsoup.connect(url)
                    .header("application/json", "text/javascript")
                    .header("gzip", "deflate")
                    .header("en-US", "en")
                    .header("Connection", "keep-alive").get();

//        System.setOut(new PrintStream("info.txt"));

        String[] theHtml = doc.text().split("html");
        String[] stuff = theHtml[1].split("Add");

        //should fix first index so its usable
        if (stuff[0].contains("\"") && stuff[0].contains("\"")){
             String removee = stuff[0].substring(stuff[0].indexOf("\""), stuff[0].indexOf(" ") + 2);
             stuff[0] = stuff[0].replace(removee, "");
        }

        ArrayList<CPU> cpuList = new ArrayList<>(stuff.length - 1);
        for (int i = 0; i < stuff.length - 1; i++) {//remember to neglect last element
            //removes ratings
            if (stuff[i].contains("(") && stuff[i].contains(")")) {
                String removee = stuff[i].substring(stuff[i].indexOf("("), stuff[i].indexOf(")") + 1);
                stuff[i] = stuff[i].replace(removee, "");
            }
            CPU curr = new CPU();
            curr.setTdp(getTdpFromCPUData(stuff[i]));
            curr.setCores(getCoresFromCPUData(stuff[i]));
            curr.setName(getNameFromCPUData(stuff[i]));
            curr.setClockSpeed(getClockSpeedFromCPUData(stuff[i]));
            curr.setPrice(getPriceFromCPUData(stuff[i]));
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


