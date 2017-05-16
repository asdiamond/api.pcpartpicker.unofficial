/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Case extends ComputerPart{
    private String type = "";
    private int fiveInchSlots = 0;
    private int threeInchSlots = 0;
    private boolean powerSupply = false;

    public Case(String caseData){
        super(caseData);
        this.name = getNameFromCaseData(caseData);
        this.fiveInchSlots = get5inchSlotsFromCaseData(caseData);
        this.threeInchSlots = get3inchSlotsFromCaseData(caseData);
        this.powerSupply = getPowerSupplyFromCaseData(caseData);
    }

    public static String getTypeFromCaseDataFromCaseData(String caseData){
        return null;
    }

    public static int get5inchSlotsFromCaseData(String caseData){
        return 0;
    }

    public static int get3inchSlotsFromCaseData(String caseData){
        return 0;
    }

    private static boolean getPowerSupplyFromCaseData(String caseData){
        return false;
    }

    private static String getNameFromCaseData(String caseData){
        return null;
    }

    public String getType() {
        return type;
    }

    public int getFiveInchSlots() {
        return fiveInchSlots;
    }

    public int getThreeInchSlots() {
        return threeInchSlots;
    }

    public boolean isPowerSupply() {
        return powerSupply;
    }
}
