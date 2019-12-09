package mars.utils.phone;

import java.util.Scanner;

public class MyClass {

    public static void main(String[] args) {
        ChineseTelocationConverter converter =ChineseTelocationConverter.getInstance();
        Scanner scanner = new Scanner(System.in);
        String num ;
        String loc;
        String code;
        while( !(num = scanner.nextLine().toString().trim()).equals("")) {
            loc = converter.getLocation(num,0,num.length(),true);
//            code = converter.getAreaCode(num,0,num.length());
            System.out.println(" result: location " + loc );
        }

    }
    
}
