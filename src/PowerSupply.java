/**
 * Created by Aleksandr on 5/15/2017.
 */
public class PowerSupply extends ComputerPart{
    private String series = "";
    private String form = "";
    private String efficiency = "";
    private int watts = 0;
    private String modular = "";

    public PowerSupply(String powData){
        super(powData);
        name = getNameFromPowdata(powData);
        series = getSeriesFromPowdata(powData);
        form = getFormFromPowdata(powData);
        efficiency = getEfficiencyFromPowdata(powData);
        watts = getWattsFromPowdata(powData);
        modular = getModularFromPowdata(powData);
    }

    public String getNameFromPowdata(String powData){
        return null;
    }

    public String getSeriesFromPowdata(String powData){
        return null;
    }

    public String getFormFromPowdata(String powData){
        return null;
    }

    public String getEfficiencyFromPowdata(String powData){
        return null;
    }

    public int getWattsFromPowdata(String powData){
        return 0;
    }

    public String getModularFromPowdata(String powData){
        return null;
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
