package mars.leetcode;


import com.google.gson.Gson;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.BitSet;

public class Test {

    public static void main(String [] args) {


//        DecimalFormat decimalFormat = new DecimalFormat("#");
//        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
//        double extRate = Double.parseDouble(decimalFormat.format(500- 40000/360.0) );
//        System.out.println(extRate);

        BitSet bitSet = new BitSet(61);
        bitSet.set(61);
        System.out.println(new Gson().toJson(bitSet));

    }


}
