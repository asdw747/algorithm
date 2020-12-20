package mars.leetcode.y2020q4;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

public class DecodeWays {
    /**
     * https://leetcode-cn.com/problems/decode-ways/
     */
    @Test
    public void test() {
        String input = "2102";
        int res = numDecodings(input);
        System.currentTimeMillis();
    }

    public int numDecodings(String s) {
        char [] inputChars = s.toCharArray();
        int [] resArray = new int[inputChars.length];
        for (int i=0; i<inputChars.length; i++) {
            int num = Integer.parseInt(String.valueOf(inputChars[i]));
            if (i==0) {
                if (num==0) {
                    return 0;
                } else {
                    resArray[i] = 1;
                }
            } else {
                int value = Integer.parseInt(String.valueOf(inputChars[i-1]) + String.valueOf(inputChars[i]));
                if (value>=10 && value <= 26) {
                    if (num == 0) {
                        resArray[i] = (i-2<0?1:resArray[i-2]);
                    } else {
                        resArray[i] = resArray[i-1] + (i-2<0?1:resArray[i-2]);
                    }
                } else {
                    if (num == 0) {
                        return 0;
                    } else {
                        resArray[i] = resArray[i-1];
                    }
                }
            }
        }

        return resArray[resArray.length-1];
    }

}
