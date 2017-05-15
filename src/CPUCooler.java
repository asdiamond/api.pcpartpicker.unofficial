public class CPUCooler {
    private String name = "";
    private String fanRpm = "";
    private String noiseLevel = "";
    private double price;

    public String toString(){
        return "name = " +  this.name + "\nprice = " + this.price + "\nfan rpm = " + fanRpm + "\nnoise level = " + this.noiseLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFanRpm() {
        return fanRpm;
    }

    public void setFanRpm(String fanRpm) {
        this.fanRpm = fanRpm;
    }

    public String getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(String noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
