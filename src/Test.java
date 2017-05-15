import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        try{
/*            ArrayList<CPUCooler> coolers = CPUCooler.parseCPUCoolerData("https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=");
            for (CPUCooler curr : coolers) {
                System.out.println(curr);
                System.out.println();
            }*/
//            System.setOut(new PrintStream("info.txt"));

            String[] rawData = ComputerPart.getRawData("https://pcpartpicker.com/products/cpu-cooler/fetch/?mode=list&xslug=&search=");
            for (int i = 0; i < rawData.length - 1; i++) {
                String curr = rawData[i];
                if (curr.contains("(") && curr.contains(")")) {
                    String removee = curr.substring(curr.indexOf("("), curr.indexOf(")") + 1);
                    curr = curr.replace(removee, "");
                }
                if(curr.contains("$")){
                    curr = curr.substring(0, curr.indexOf("$"));
                }
                System.out.println(curr);
//                System.out.println(CPUCooler.getNoiseLevel(curr));
//                System.out.println();
            }
        } catch (IOException e){
            System.out.println("Failed connection");
            e.printStackTrace();
        }
    }

}
