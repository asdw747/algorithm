package mars.utils;

import org.junit.Test;

import java.util.Random;

public class MyStringUtil {

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyz0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            int number=random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(MyStringUtil.getRandomString(24));
    }

}
