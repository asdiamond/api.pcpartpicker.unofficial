import java.io.IOException;

/**
 * CPU link: https://pcpartpicker.com/products/cpu/fetch/?mode=list&xslug=&search=
 * CPU Cooler link: https://pcpartpicker.com/products/cpu-cooler/fetch/?mode=list&xslug=&search=
 * Motherboard link: https://pcpartpicker.com/products/motherboard/fetch/?mode=list&xslug=&search=
 * Memory link: https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=
 * Storage link: https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=
 * GPU link: https://pcpartpicker.com/products/video-card/fetch/?mode=list&xslug=&search=
 * Case link: https://pcpartpicker.com/products/case/fetch/?mode=list&xslug=&search=
 * Power Supply link: https://pcpartpicker.com/products/power-supply/fetch/?mode=list&xslug=&search=
 * Monitor link: https://pcpartpicker.com/products/monitor/fetch/?mode=list&xslug=&search=
 * */

public class Test {

    public static void main(String[] args) {
        try{
//            String[] rawData = ComputerPart.getRawData("https://pcpartpicker.com/products/memory/fetch/?mode=list&xslug=&search=");
            String[] rawData = ComputerPart.getRawData("https://pcpartpicker.com/products/memory/fetch/?sort=a2&page=1&mode=list&xslug=&search=");
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
                System.out.println(Memory.getTypeFromMemData(curr));
                System.out.println();
            }
        } catch (IOException e){
            System.out.println("Failed connection");
            e.printStackTrace();
        }
    }

}
