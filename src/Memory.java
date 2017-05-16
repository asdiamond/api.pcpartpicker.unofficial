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
        return null;
    }

    private static String getTypeFromMemData(String memData){
        return null;
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
