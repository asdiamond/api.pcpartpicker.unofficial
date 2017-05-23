import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * CPU link: https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=
 * CPU Cooler link: https://pcpartpicker.com/products/cpu-cooler/fetch/?mode=list&xslug=&search=
 * Motherboard link: https://pcpartpicker.com/products/motherboard/fetch/?mode=list&xslug=&search=
 * Memory link: https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=
 * Storage link: https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=
 * GPU link: https://pcpartpicker.com/products/video-card/fetch/?mode=list&xslug=&search=
 * Case link: https://pcpartpicker.com/products/case/fetch/?mode=list&xslug=&search=
 * Power Supply link: https://pcpartpicker.com/products/power-supply/fetch/?mode=list&xslug=&search=
 * Monitor link: https://pcpartpicker.com/products/monitor/fetch/?mode=list&xslug=&search=
 * */

public class Test {

    public static void main(String[] args) {
        try {
            String url = "https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=";
            System.setProperty("http.agent", "Chrome");
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
            getMemoryFromDoc(doc);
        }catch (IOException e){
            System.out.println("Failed connection");
            e.printStackTrace();
        }
    }

    private static ArrayList<CPU> getCPUsFromDoc(Document doc){
        Elements trElements = doc.getElementsByTag("tr");
        ArrayList<CPU> cpus = new ArrayList<>(trElements.size());
        for (int i = 0; i < trElements.size(); i++) {
            cpus.add(i, new CPU(trElements.get(i).text()));
            System.out.println(trElements.get(i).text());
            System.out.println(cpus.get(i).getPrice());
            System.out.println();
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

    private static String[][] getRawData(Document doc){
        String[][] rawData = new String[doc.getElementsByTag("tr").size()][];
        int j = 0;
        for (Element curr : doc.getElementsByTag("tr")) {
            int i = 0;
            rawData[j] = new String[curr.getElementsByTag("td").size() - 3];//one of the 3 base cases
            for (Element info : curr.getElementsByTag("td")) {
                if(info.text().length() < 1) continue;
                if(info.text().equals("Add")) continue;
                if(info.text().matches("\\(\\d+\\)")) continue;//number between parenthesis, the ratings
                rawData[j][i] = info.text();
//                System.out.println(rawData[j][i]);
                i++;
            }
//            System.out.println();
            j++;
        }
        return rawData;
    }
}
