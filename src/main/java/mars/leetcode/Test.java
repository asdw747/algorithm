package mars.leetcode;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.Data;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {

    public static void main(String [] args) {
        List<Model> list = new ArrayList<>();

        Model m1 = new Model();
        m1.setA(100);
        list.add(m1);

        Model m2 = new Model();
        m2.setA(200);
        list.add(m2);

        Model m3 = new Model();
        m3.setA(300);
        list.add(m3);

        list.sort((o1, o2) -> o2.getA().compareTo(o1.getA()));

        System.out.println(list.get(0).getA());
    }



}

@Data
class Model {
    Integer a;
    Integer b;
}