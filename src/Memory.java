/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Memory extends ComputerPart{
    private String speed = "";
    private String type = "";
    private int cas = 0;
    private String modules = "";
    private int size = 0;

    public Memory(String memData){
        super(memData);
        name = getNameFromMemData(memData);
        speed = getSpeedFromMemData(memData);
        type = getTypeFromMemData(memData);
        cas = getCasFromMemData(memData);
        modules = getModulesFromMemData(memData);
        size = getSizeFromMemData(memData);
    }

    private static String getSpeedFromMemData(String memData){
        if(memData.contains("DDR-333") || memData.contains("DDR-400")){//the only ones without 4 digit speed
            return memData.substring(memData.indexOf("DDR"), memData.indexOf("DDR") + 7);
        }
        return memData.substring(memData.indexOf("DDR"), memData.indexOf("DDR") + 9);//4 digits of DDR + 1, and 4 extra digits of speed.
    }

    public static String getTypeFromMemData(String memData){
        if(memData.contains("DDR-333") || memData.contains("DDR-400")){
            return memData.substring(memData.indexOf("DDR") + 7, memData.indexOf("DDR") + 18);
        }
        return memData.substring(memData.indexOf("DDR") + 9, memData.indexOf("DDR") + 21);
    }

    private static int getCasFromMemData(String memData){
        return 0;
    }

    private static String getModulesFromMemData(String memData){
        return null;
    }

    private static int getSizeFromMemData(String memData){
        return 0;
    }

    private static String getNameFromMemData(String memData){
        return null;
    }

    public String getSpeed() {
        return speed;
    }

    public String getType() {
        return type;
    }

    public int getCas() {
        return cas;
    }

    public String getModules() {
        return modules;
    }

    public int getSize() {
        return size;
    }
}
