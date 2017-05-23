public class CPU {
    private String name;
    private double clockSpeed;
    private int cores;
    private int tdp;
    private double price;

    public CPU(String cpuData){
        name = cpuData.substring(0, cpuData.indexOf("GHz") - 4);
        clockSpeed = Double.parseDouble(cpuData.substring(cpuData.indexOf("GHz") - 4, cpuData.indexOf("GHz")));
        cores = Integer.parseInt(cpuData.substring(cpuData.indexOf("GHz") + 3, cpuData.indexOf("GHz") + 6).trim());
        tdp = Integer.parseInt(cpuData.substring(cpuData.indexOf("GHz") + 6, cpuData.indexOf("W")).trim());
        try {
            price = Double.parseDouble(cpuData.substring(cpuData.indexOf("$") + 1).replace("Add", "").trim());
        }catch (StringIndexOutOfBoundsException | NumberFormatException e){
            price = 0;//no price, dont worry about it
        }
    }

    @Override
    public String toString() {
        return "name= " + this.name + "\nprice= " + this.getPrice() + "$\ncores= " + this.cores + "\ntdp= " + this.tdp
                + "W\nclockSpeed= " + this.clockSpeed + "GHz";
    }

    public double getPrice() {
        return price;
    }

    public int getCores(){
        return this.cores;
    }

    public double getClockSpeed(){
        return this.clockSpeed;
    }

    public double getTdp(){
        return this.tdp;
    }

    public String getName() {
        return name;
    }
}