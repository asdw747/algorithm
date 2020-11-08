package mars.leetcode.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PermuteUtil {


    @Test
    public void test() {
        List<int[]> res = PermuteUtil.allPermuteUseA(new int[]{1,2,3,4,5,6}, 3);
        System.currentTimeMillis();
    }

    public static List<int[]> allPermuteUseA(int [] input, int target) {
        if (target > input.length) {
            return Collections.emptyList();
        }

        List<int[]> result = new ArrayList<>();
        generatePermuteUseA(result, new int[target], 0, target, input);
        return result;

    }

    private static void generatePermuteUseA(List<int[]> result, int[] inputRow, int curLen, int target, int[] inputSource) {
        if (curLen >= target) {
            result.add(inputRow);
            return;
        }

        for (int i=0; i<inputSource.length; i++) {
            int [] newInputRow = Arrays.copyOf(inputRow, inputRow.length);
            newInputRow[curLen] = inputSource[i];
            int [] newInput = deleteByIndex(inputSource, i);
            generatePermuteUseA(result, newInputRow, curLen+1, target, newInput);
        }
    }

    private static int[] deleteByIndex(int [] input, int index) {
        if (input.length <= index) {
            return new int[0];
        }

        int[] output = new int[input.length - 1];
        int j=0;
        for (int i=0; i<input.length; i++) {
            if (i!=index) {
                output[j] = input[i];
                j++;
            }
        }

        return output;
    }

    public static List<int[]> allPermuteUseC(int [] input, int target) {
        if (target > input.length) {
            return Collections.emptyList();
        }

        List<int[]> result = new ArrayList<>();
        generatePermuteUseC(result, new int[target], 0, target, input);
        return result;
    }

    private static void generatePermuteUseC(List<int[]> result, int[] inputRow, int curLen, int target, int[] inputSource) {
        if (curLen >= target) {
            result.add(inputRow);
            return;
        }

        int restTarget = target-curLen;
        for (int i=0; i<inputSource.length; i++) {
            if (inputSource.length-i < restTarget) {
                break;
            }

            int [] newInputRow = Arrays.copyOf(inputRow, inputRow.length);
            newInputRow[curLen] = inputSource[i];
            int [] newInput = Arrays.copyOfRange(inputSource, i+1, inputSource.length);
            generatePermuteUseC(result, newInputRow, curLen+1, target, newInput);
        }
    }

}
