package mars.LeetCode.Y2018M09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String [] args) {
//        char []ss={'a','b','c'};
//        permutation(ss,0);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Enter your value:");
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("your value is :"+str);

        String [] input = str.split(" ");
        int sum = getSum(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        System.out.println(sum);
    }

    public static int getSum(int a1, int an, int b) {
        if ((an -a1)%b!=0) {
            return -1;
        }
        int count = (an - a1)/b + 1;
        return (a1+an)*count/2;
    }






































//    public static void print(int [] num, int index) {
//        if (index>=num.length) {
//            System.out.println();
//            return;
//        }
//
//        for (int i=0; i<num.length; i++) {
//            System.out.print(num[i]);
//            print(num, index+1);
//        }
//
//    }
//
//    public static void permutation(char[]ss,int i){
//        if(ss==null||i<0 ||i>ss.length){
//            return;
//        }
//        if(i==ss.length){
//            System.out.println(new String(ss));
//        }else{
//            for(int j=i;j<ss.length;j++){
//                char temp=ss[j];//交换前缀,使之产生下一个前缀
//                ss[j]=ss[i];
//                ss[i]=temp;
//                permutation(ss,i+1);
//                temp=ss[j]; //将前缀换回来,继续做上一个的前缀排列.
//                ss[j]=ss[i];
//                ss[i]=temp;
//            }
//        }
//    }

}
