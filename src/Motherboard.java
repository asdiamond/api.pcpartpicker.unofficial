/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Motherboard extends ComputerPart {
    private String socket = "";
    private int ramSlots = 0;
    private int maxRam = 0;

    public Motherboard(String moboData){
        super(moboData);
        name = getNameFromMoboData(moboData);
        socket = getSocketFromMoboData(moboData);
        ramSlots = getRamSlotsFromMoboData(moboData);
        maxRam = getMaxRamFromMoboData(moboData);
    }

    public static String getSocketFromMoboData(String moboData){
        return null;
    }

    public static String getNameFromMoboData(String moboData){
        return null;
    }

    public static int getRamSlotsFromMoboData(String moboData){
        return 0;
    }

    public static int getMaxRamFromMoboData(String moboData){
        return 0;
    }

    public String getSocket() {
        return socket;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public int getMaxRam() {
        return maxRam;
    }
}
