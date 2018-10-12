package mars.LeetCode.Y2018M09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1 {

    public static void main(String [] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Enter your value:");
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String [] input = str.split(" ");
        long input1;
        if (input[0].length()>14) {
            input1 = Long.MAX_VALUE;
        } else {
            input1 = Long.parseLong(input[0]);
        }
        long input2 = Long.parseLong(input[1]);

        if (input2<=2) {
            System.out.println(0);
            return;
        }

        long avg = input2/2;
        if (avg>=input1) {
            System.out.println(0);
            return;
        }

        if (avg<input1 && input2>input1) {
            System.out.println(input1-avg);
            return;
        }

        if (input2%2==0) {
            System.out.println(avg-1);
        } else {
            System.out.println(avg);
        }

    }

}
