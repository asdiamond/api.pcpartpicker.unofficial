import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * Created by Aleksandr on 5/14/2017.
 */
public class ComputerPart<T extends ComputerPart> {

    public ArrayList<T> parseResponse(String url) throws IOException {
        String[] rawData = getRawData(url);

        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class type = (Class)superClass.getActualTypeArguments()[0];

        ArrayList<T> list = new ArrayList<>(rawData.length - 1);
        for (int i = 0; i < rawData.length - 1; i++) {//remember to neglect last element
//            list.add(i, type.newInstance(rawData[i]));
        }
        return list;
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
}
