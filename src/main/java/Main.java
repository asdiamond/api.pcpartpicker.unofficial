import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    static String CPUlink= "https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=";
    static String CPUCoolerlink= "https://pcpartpicker.com/products/cpu-cooler/fetch/?mode=list&xslug=&search=";
    static String Motherboardlink= "https://pcpartpicker.com/products/motherboard/fetch/?mode=list&xslug=&search=";
    static String Memorylink="https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=";
    static String Storagelink= "https://pcpartpicker.com/products/internal-hard-drive/fetch/?mode=list&xslug=&search=";
    static String GPUlink= "https://pcpartpicker.com/products/video-card/fetch/?mode=list&xslug=&search=";
    static String Caselink = "https://pcpartpicker.com/products/case/fetch/?mode=list&xslug=&search=";
    static String PowerSupply = "https://pcpartpicker.com/products/power-supply/fetch/?mode=list&xslug=&search=";
    static String Monitorlink = "https://pcpartpicker.com/products/monitor/fetch/?mode=list&xslug=&search=";

    public static void main(String[] args) throws IOException {
//        System.setOut(new PrintStream("info.txt"));
        System.setProperty("http.agent", "Chrome");

        Document doc = Jsoup.parse(new URL(PowerSupply).openStream(), "UTF-8", "", Parser.xmlParser());
//        System.out.println(doc);
//        Document mobileDoc = Jsoup.parse(new URL("https://pcpartpicker.com/products/cpu/fetch/?mode=mobile&xslug=&search=").openStream(), "UTF-8", "", Parser.xmlParser());
//        System.out.println(mobileDoc);
//        getRawData(mobileDoc);

        getUrlsFromDoc(doc);
//        System.setOut(System.err);
//        getRawData(doc);
    }

    private static void getUrlsFromDoc(Document doc) {
        Elements links = doc.getElementsByTag("a");
//        Pattern pattern = Pattern.compile("&quot;#(.*?)\\&quot;");
        Pattern pattern = Pattern.compile("&quot;#(.*?)\\\\");//should grab it without the annoying "\" at the end
        //the original is still there just in case ;)
        for (Element curr : links) {
            if(curr.text().equals("Add")){
                Matcher matcher = pattern.matcher(curr.toString());
                if(matcher.find()){
                    System.out.println(matcher.group(1));
                }
            }
        }
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

    public ArrayList<CPU> getCPUs() throws IOException {
        String url = "https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getCPUsFromDoc(doc);
    }

    public ArrayList<CPUCooler> getCPUCoolers() throws IOException {
        String url = "https://pcpartpicker.com/products/cpu-cooler/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getCPUCoolersFromDoc(doc);
    }

    public ArrayList<Motherboard> getMotherboards() throws IOException {
        String url = "https://pcpartpicker.com/products/motherboard/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getMobosFromDoc(doc);
    }

    public ArrayList<Memory> getMemory() throws IOException {
        String url = "https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getMemoryFromDoc(doc);
    }

    public ArrayList<Storage> getStorage() throws IOException {
        String url = "https://pcpartpicker.com/products/internal-hard-drive/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getStorageFromDoc(doc);
    }

    public ArrayList<GPU> getGPUs() throws IOException {
        String url = "https://pcpartpicker.com/products/video-card/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getGPUsFromDoc(doc);
    }

    public ArrayList<Case> getCases() throws IOException {
        String url = "https://pcpartpicker.com/products/case/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getCasesFromDoc(doc);
    }

    public ArrayList<PowerSupply> getPowerSupplies() throws IOException {
        String url = "https://pcpartpicker.com/products/power-supply/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getPowerSuppliesFromDoc(doc);
    }

    public ArrayList<Monitor> getMonitors() throws IOException {
        String url = "https://pcpartpicker.com/products/monitor/fetch/?mode=list&xslug=&search=";
        System.setProperty("http.agent", "Chrome");
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
        return getMonitorsFromDoc(doc);
    }

}
