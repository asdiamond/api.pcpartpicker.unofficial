/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Motherboard {
    private String name;
    private String socket;
    private String formFactor;
    private int ramSlots;
    private String maxRam;
    private double price;

    public Motherboard(String[] moboData){
        if(moboData.length < 5) return;
        name = moboData[0];
        socket = moboData[1];
        formFactor = moboData[2];
        ramSlots = Integer.parseInt(moboData[3]);
        maxRam = moboData[4];
        try {
            price = Double.parseDouble(moboData[5].replace("$", ""));
        }catch (NullPointerException e){
            //no price
            price = 0.0;
        }
    }

    @Override
    public String toString() {
        return name + "\n" + socket + "\n" + formFactor + "\n" + ramSlots + "\n" + maxRam + "\n" + price;
    }

    public String getSocket() {
        return socket;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public String getMaxRam() {
        return maxRam;
    }

    public String getName() {
        return name;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public double getPrice() {
        return price;
    }
}
