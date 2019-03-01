package mars.finance;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class checkTool {

    public static void main(String [] args) {
        System.out.println("-------------------------begin-------------------------");

        try {
            List<String> jars1 = new ArrayList<String>();
            List<String> jars2 = new ArrayList<String>();

            String [] jarArray1 = (
                    "" +
                            "").split(":");
            String [] jarArray2 = (
                    "" +
                            "").split(":");

            for (String jar : jarArray1) {
                if (StringUtils.isBlank(jar)) {
                    continue;
                }

                String [] array = jar.split("/");
                jars1.add(array[array.length-1]);
            }

            for (String jar : jarArray2) {
                if (StringUtils.isBlank(jar)) {
                    continue;
                }

                String [] array = jar.split("/");
                jars2.add(array[array.length-1]);
            }

            List<String> res = new ArrayList<String>();
            for (int i=0; i<jars1.size(); i++) {
                String jar1 = jars1.get(i);
                if (!jars2.contains(jar1)) {
                    res.add(jar1);
                }
            }

            List<String> resB = new ArrayList<String>();
            for (int i=0; i<jars2.size(); i++) {
                String jar2 = jars2.get(i);
                if (!jars1.contains(jar2)) {
                    resB.add(jar2);
                }
            }

            System.currentTimeMillis();
        } catch (Exception e) {
            System.currentTimeMillis();
        }

    }

}
