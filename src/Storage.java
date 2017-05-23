/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Storage {
    private String series = "";
    private String type = "";
    private int cache = 0;
    private double pricePerGb = 0;

    public Storage(String storData){

    }

    public String getSeries() {
        return series;
    }

    public String getType() {
        return type;
    }

    public int getCache() {
        return cache;
    }

    public double getPricePerGb() {
        return pricePerGb;
    }
}
