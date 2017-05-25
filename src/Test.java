import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * CPU link: https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=
 * CPU Cooler link: https://pcpartpicker.com/products/cpu-cooler/fetch/?mode=list&xslug=&search=
 * Motherboard link: https://pcpartpicker.com/products/motherboard/fetch/?mode=list&xslug=&search=
 * Memory link: https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=
 * Storage link: https://pcpartpicker.com/products/internal-hard-drive/fetch/?mode=list&xslug=&search=
 * GPU link: https://pcpartpicker.com/products/video-card/fetch/?mode=list&xslug=&search=
 * Case link: https://pcpartpicker.com/products/case/fetch/?mode=list&xslug=&search=
 * Power Supply link: https://pcpartpicker.com/products/power-supply/fetch/?mode=list&xslug=&search=
 * Monitor link: https://pcpartpicker.com/products/monitor/fetch/?mode=list&xslug=&search=
 * */

public class Test {

    public static void main(String[] args) {
        try {
            String url = "https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=";
            System.setProperty("http.agent", "Chrome");
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());

        }catch (IOException e){
            System.out.println("Failed connection");
            e.printStackTrace();
        }
    }

    private static void serializeComputerParts(String filename, ArrayList cpuParts) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        for (Object curr : cpuParts) {
            out.writeObject(curr);
        }
        out.close();
        fileOut.close();
    }

    private static <T> ArrayList<T> deserializeComputerParts(String filename) throws IOException {
            FileInputStream in = new FileInputStream(filename);
            ObjectInputStream inputStream = new ObjectInputStream(in);
            ArrayList<T> parts = new ArrayList<>();
        try {
            for(Object curr; (curr = inputStream.readObject()) != null; ) parts.add((T)curr);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return parts;
    }

    private static String[][] getRawData(Document doc){
        String[][] rawData = new String[doc.getElementsByTag("tr").size()][];
        int j = 0;
        for (Element curr : doc.getElementsByTag("tr")) {
            int i = 0;
            rawData[j] = new String[curr.getElementsByTag("td").size() - 2];//one of the 2 base cases
            for (Element info : curr.getElementsByTag("td")) {
                if(info.text().equals("Add")) continue;
                if(info.text().matches("\\(\\d+\\)")) continue;//number between parenthesis, the ratings
                rawData[j][i] = info.text();
                System.out.println(rawData[j][i]);
                i++;
            }
            System.out.println();
            j++;
        }
        return rawData;
    }

    private static ArrayList<CPU> getCPUsFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<CPU> cpus = new ArrayList<>(rawData.length);

        for (int i = 0; i < rawData.length; i++) {
            cpus.add(i, new CPU(rawData[i]));
        }
        return cpus;
    }

    private static ArrayList<CPUCooler> getCPUCoolersFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<CPUCooler> coolers = new ArrayList<>(rawData.length);

        for (int i = 0; i < rawData.length; i++) {
            coolers.add(i, new CPUCooler(rawData[i]));
        }

        return coolers;
    }

    private static ArrayList<Motherboard> getMobosFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<Motherboard> motherboards = new ArrayList<>(rawData.length);

        for (int i = 0; i < rawData.length; i++) {
            motherboards.add(new Motherboard(rawData[i]));
            System.out.println(motherboards.get(i));
            System.out.println();
        }
        return motherboards;
    }

    private static ArrayList<Memory> getMemoryFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<Memory> rams = new ArrayList<>(rawData.length);
        for (int i = 0; i < rawData.length; i++) {
            rams.add(new Memory(rawData[i]));
        }
        return rams;
    }

    private static ArrayList<Storage> getStorageFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<Storage> drives = new ArrayList<>(rawData.length);
        for (int i = 0; i < rawData.length; i++) {
            drives.add(new Storage(rawData[i]));
        }
        return drives;
    }

    private static ArrayList<GPU> getGPUsFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<GPU> gpus = new ArrayList<>(rawData.length);
        for (int i = 0; i < rawData.length; i++){
            gpus.add(new GPU(rawData[i]));
        }
        return gpus;
    }

    private static ArrayList<Case> getCasesFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<Case> cases = new ArrayList<>(rawData.length);
        for (int i = 0; i < rawData.length; i++){
            cases.add(new Case(rawData[i]));
        }
        return cases;
    }

    private static ArrayList<PowerSupply> getPowerSuppliesFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<PowerSupply> powerSupplies = new ArrayList<>(rawData.length);
        for (int i = 0; i < rawData.length; i++){
            powerSupplies.add(new PowerSupply(rawData[i]));
        }
        return powerSupplies;
    }

    private static ArrayList<Monitor> getMonitorsFromDoc(Document doc){
        String[][] rawData = getRawData(doc);
        ArrayList<Monitor> monitors = new ArrayList<>(rawData.length);
        for (int i = 0; i < rawData.length; i++){
            monitors.add(new Monitor(rawData[i]));
        }
        return monitors;
    }
}
