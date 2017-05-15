import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        try{
            CPUCoolerConnection.parseCPUCoolerData("https://pcpartpicker.com/products/cpu-cooler/fetch/?mode=list&xslug=&search=");
        } catch (IOException e){
            System.out.println("Failed connection");
            e.printStackTrace();
        }
    }

    /** The last index will always be fucked up just ignore it, it has no usable data anyways */
    public static String[] getRawData(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .header("application/json", "text/javascript")
                .header("gzip", "deflate")
                .header("en-US", "en")
                .header("Connection", "keep-alive").get();

        String[] theHtml = doc.text().split("html");
        String[] rawData =  theHtml[1].split("Add");

        if (rawData[0].contains("\"") && rawData[0].contains("\"")){
            String removee = rawData[0].substring(rawData[0].indexOf("\""), rawData[0].indexOf(" ") + 2);
            rawData[0] = rawData[0].replace(removee, "");
        }

        return rawData;
    }
}
