/**
 * Created by Aleksandr on 5/15/2017.
 */
public class PowerSupply{
    private String series = "";
    private String form = "";
    private String efficiency = "";
    private int watts = 0;
    private String modular = "";

    public PowerSupply(String powData){
    }

    public String getSeries() {
        return series;
    }

    public String getForm() {
        return form;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public int getWatts() {
        return watts;
    }

    public String getModular() {
        return modular;
    }
}
