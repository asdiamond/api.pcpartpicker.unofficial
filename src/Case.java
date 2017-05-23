/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Case {
    private String type = "";
    private int fiveInchSlots = 0;
    private int threeInchSlots = 0;
    private boolean powerSupply = false;

    public Case(String caseData){
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
