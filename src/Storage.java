/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Storage {
    private String name;
    private String series;
    private String form;
    private String type;
    private String capacity;
    private int cache;
    private double pricePerGb;
    private double price;

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

    public String getName() {
        return name;
    }

    public String getForm() {
        return form;
    }

    public String getCapacity() {
        return capacity;
    }

    public double getPrice() {
        return price;
    }
}
