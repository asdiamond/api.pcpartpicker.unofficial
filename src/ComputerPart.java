import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ComputerPart<T> {
    private double price = 0.0;
    protected String name = "";

    public ComputerPart(){}

    public ComputerPart(String rawData){
        if (rawData.contains("(") && rawData.contains(")")) {
            String removee = rawData.substring(rawData.indexOf("("), rawData.indexOf(")") + 1);
            rawData = rawData.replace(removee, "");
        }
        this.price = getPriceFromRawData(rawData);
    }

    /** The last index will always be fucked up just ignore it, it has no usable data anyways */
    protected static String[] getRawData(String url) throws IOException {
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

    public static double getPriceFromRawData(String rawData){
        double price = 0.0;
        try {
            price = Double.parseDouble(rawData.substring(rawData.indexOf("$") + 1));
        } catch(StringIndexOutOfBoundsException | NumberFormatException e ){
            //this means the price is blank on their website, just dont worry about it.
            price = 0.0;
        }
        return price;
    }

    public double getPrice() {
        return price;
    }

    public String getName(){
        return name;
    }

}
