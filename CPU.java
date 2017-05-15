public class CPU {
    private double price = -1;
    private String name = "NONAME";
    private int cores = -1;
    private double clockSpeed = -1.0;
    private int tdp = -1;

    @Override
    public String toString() {
        return "name= " + this.name + "\nprice= " + this.price + "$\ncores= " + this.cores + "\ntdp= " + this.tdp
                + "W\nclockSpeed= " + this.clockSpeed + "GHz";
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCores(int cores){
        this.cores = cores;
    }

    public void setClockSpeed(double clockSpeed){
        this.clockSpeed = clockSpeed;
    }

    public void setTdp(int tdp){
        this.tdp = tdp;
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
}