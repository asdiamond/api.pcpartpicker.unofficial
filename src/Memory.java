/**
 * Created by Aleksandr on 5/15/2017.
 */
public class Memory {
    private String name;
    private String speed;
    private String type;
    private int cas;
    private String modules;
    private int size;
    private double pricePerGB;
    private double price;

    public Memory(String[] memData){
        name = memData[0];
        speed = memData[1];
        type = memData[2];
        cas = Integer.parseInt(memData[3]);
        modules = memData[4];
        size = Integer.parseInt(memData[5].replace("GB", ""));
        try {
            pricePerGB = Double.parseDouble(memData[6].replace("$", ""));
            price = Double.parseDouble(memData[7].replace("$", ""));
        }catch (NullPointerException e){
            //there can be no price, and therefore sometimes no price per gb
            pricePerGB = 0.0;
            price = 0.0;
        }
    }

    @Override
    public String toString() {
        return name + "\n" + speed + "\n" + type + "\n" + cas + "\n" + modules + "\n" + size + "\n" + pricePerGB + "\n" + price;
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
