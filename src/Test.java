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
            String cpuUrl = "https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=";
            System.setProperty("http.agent", "Chrome");
            Document doc = Jsoup.parse(new URL(cpuUrl).openStream(), "UTF-8", "", Parser.xmlParser());
            ArrayList<CPU> cpus = getCPUsFromDoc(doc);
        }catch (IOException e){
            System.out.println("Failed connection");
            e.printStackTrace();
        }
    }

    //returns names from cpu doc
    private static ArrayList<String> getNamesFromDoc(Document doc) {
        ArrayList<String> arr = new ArrayList<>();
        for (Element curr : doc.getElementsByTag("a")) {
            if(curr.text().equalsIgnoreCase("add") || curr.text().length() < 3){//should remove the page numbers, checks if its 1 or 2 digits
                continue;
            }
            arr.add(curr.text());
        }
        return arr;
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
}
