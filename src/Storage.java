/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Storage extends ComputerPart{
    private String series = "";
    private String type = "";
    private int cache = 0;
    private double pricePerGb = 0;

    public Storage(String storData){
        super(storData);
        name = getNameFromStorData(storData);
        series = getSeriesFromStorData(storData);
        type = getTypeFromStorData(storData);
        cache = getCacheFromStorData(storData);
        pricePerGb = getPricePerGbFromStorData(storData);

    }

    private String getNameFromStorData(String storData){
        return null;
    }

    private String getSeriesFromStorData(String storData){
        return null;
    }

    private String getTypeFromStorData(String storData){
        return null;
    }

    private int getCacheFromStorData(String storData){
        return 0;
    }

    private double getPricePerGbFromStorData(String storData){
        return 0.0;
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
