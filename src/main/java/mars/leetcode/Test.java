package mars.leetcode;


import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Test {

    public static void main(String [] args) {


        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        double extRate = Double.parseDouble(decimalFormat.format(500- 40000/360.0) );
        System.out.println(extRate);

    }


}
