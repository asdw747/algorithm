package mars.leetcode.y2019;

import org.apache.commons.lang.math.NumberUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过递归实现的一个排列组合问题
 **/
public class Recursive {

    private static List<Integer> initial = new ArrayList<>();
    private static int counter = 0;

    private static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter your value:");

        try {
            str = br.readLine();
            String [] inputArray = str.split(" ");
            for (String input : inputArray) {
                initial.add(NumberUtils.toInt(input));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        input();
        recursiveWithoutRepeat(initial, new ArrayList<>());

        System.out.println("counter : " + counter);

    }

    private static void recursiveWithRepeat(List<Integer> input, List<Integer> current) {
        if (current.size() >= input.size()) {
            for (int i : current) {
                System.out.print(i);
            }
            System.out.println();
            counter ++;
            return;
        }

        for (int i : input) {
            List<Integer> target = new ArrayList<>();
            copy(current, target);

            target.add(i);
            recursiveWithRepeat(input, target);
        }

        current.clear();
    }

    private static void recursiveWithoutRepeat(List<Integer> input, List<Integer> current) {
        if (input.isEmpty()) {
            for (int i : current) {
                System.out.print(i);
            }
            System.out.println();
            counter ++;
            return;
        }

        for (int i : input) {
            List<Integer> currentTarget = new ArrayList<>();
            copy(current, currentTarget);

            List<Integer> inputTarget = new ArrayList<>();
            copyWithOutElement(input, inputTarget, i);

            currentTarget.add(i);
            recursiveWithoutRepeat(inputTarget, currentTarget);
        }

        current.clear();
        input.clear();
    }

    private static void copy(List<Integer> source, List<Integer> target) {
        target.addAll(source);
    }

    private static void copyWithOutElement(List<Integer> source, List<Integer> target, int excludeItem) {
        for (int item : source) {
            if (item != excludeItem) {
                target.add(item);
            }
        }
    }

}
