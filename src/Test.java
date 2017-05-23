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
            String url = "https://pcpartpicker.com/products/cpu-cooler/fetch/?mode=list&xslug=&search=";
            System.setProperty("http.agent", "Chrome");
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
            getCPUCoolersFromDoc(doc);
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
        Elements coolerElements = doc.getElementsByTag("tr");
        ArrayList<CPUCooler> coolers = new ArrayList<>(doc.getElementsByTag("tr").size());
        for (Element curr : coolerElements) {
            String[] coolerArr = new String[5];
            int i = 0;
            for (Element innerTd : curr.getElementsByTag("td")) {
                if(innerTd.text().equals("Add")) continue;
                if(innerTd.text().contains("(") && innerTd.text().contains(")")) continue;
                if(innerTd.text().length() < 1) continue;
                coolerArr[i] = innerTd.text();
                i++;
            }
            coolers.add(new CPUCooler(coolerArr));
        }
        return coolers;
    }
}
