package mars.LeetCode;

import org.apache.commons.httpclient.methods.PostMethod;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
//        List<>
        List a = new ArrayList();

        a.add(1);
        a.add(2);
        a.add(3);

        System.out.println(a.get(a.size() - 1));

        try {

            PostMethod method = new PostMethod("www.baidu.com");
            method.addParameter("",null);

            System.out.println("ok");
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }

}
