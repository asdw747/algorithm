package mars.LeetCode.Y2018M08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class huawei {

    public static void main(String [] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Enter your value:");
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("your value is :"+str);

        int maxIndex = 0;
        int max=0;
        int [] nums = new int[3];

        List<String> r = getStr(str);

        int maxRes = 0;
        for (String s:r) {
            String [] numStr = s.split(",");
            for (int i=0; i<3; i++) {
                nums[i] = Integer.parseInt(numStr[i]);
                if (Integer.parseInt(numStr[i])>max) {
                    max = Integer.parseInt(numStr[i]);
                    maxIndex = i;
                }
            }

            int count = 0;
            Set<Integer> res = getNumList(nums);
            int maxI = 0;
            for (int num: res) {
                count++;
                if (count == nums[maxIndex]) {
                    maxI = num;
                }
            }

            if (maxI > maxRes) {
                maxRes = maxI;
            }
        }

        System.out.println(maxRes);
    }

    private static List getStr(String numStr) {
        List<String> res = new ArrayList();
        res.add(numStr);

        if (numStr.contains("5")) {
            res.add(numStr.replace("5", "2"));
        }

        if (numStr.contains("2")) {
            res.add(numStr.replace("2", "5"));
        }

        if (numStr.contains("6")) {
            res.add(numStr.replace("6", "9"));
        }

        if (numStr.contains("9")) {
            res.add(numStr.replace("9", "6"));
        }

        return res;

    }

    private static Set getNumList(int[] number) {
        Set<Integer> set = new TreeSet();
        set.add(number[0]);
        set.add(number[1]);
        set.add(number[2]);

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (j==i) {
                    continue;
                }
                set.add(number[i]*10 + number[j]);
            }
        }

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                for (int k=0; k<3; k++) {
                    if (i==j || i==k || j==k) {
                        continue;
                    }
                    set.add(number[i]*100 + number[j]*10 + number[k]);
                }
            }
        }

        return set;
    }

}
