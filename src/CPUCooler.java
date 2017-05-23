public class CPUCooler {
    private String name;
    private String fanRpm = "";
    private String noiseLevel = "";
    private double price;

    public CPUCooler(String[] coolerData){
        if(coolerData.length < 4) return;
        name = coolerData[0];
        fanRpm = coolerData[1];
        noiseLevel = coolerData[2];
        try {
            price = Double.parseDouble(coolerData[3].replace("$", ""));
        }catch (NullPointerException e){
            //no price
            price = 0.0;
        }
    }

    @Override
    public String toString(){
        return "name = " +  this.name + "\nprice = " +this.getPrice() + "\nfan rpm = " + fanRpm + "\nnoise level = " + this.noiseLevel;
    }

    public double getPrice() {
        return price;
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

}
