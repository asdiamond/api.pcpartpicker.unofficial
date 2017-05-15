import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        try{
            ArrayList<CPU> cpus = CPU.parseCPUData("https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=");
            for (CPU curr : cpus) {
                System.out.println(curr);
                System.out.println();
            }
        } catch (IOException e){
            System.out.println("Failed connection");
            e.printStackTrace();
        }
    }

}
