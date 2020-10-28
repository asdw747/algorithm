package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.*;

public class GroupAnagrams {

    @Test
    public void test() {
        List<List<String>> result = groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.currentTimeMillis();
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> resultMap = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            resultMap.compute(key, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                    v.add(str);
                } else {
                    v.add(str);
                }

                return v;
            });
        }

        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry:resultMap.entrySet()) {
            result.add(entry.getValue());
        }

        return result;
    }

    private String getKey(String input) {
        char [] inputChars = input.toCharArray();
        for (int i=0; i<inputChars.length-1; i++) {
            for (int j=0; j<inputChars.length-1; j++) {
                if (inputChars[j] > inputChars[j+1]) {
                    Character temp = inputChars[j];
                    inputChars[j] = inputChars[j+1];
                    inputChars[j+1] = temp;
                }
            }
        }

        StringBuilder key = new StringBuilder();
        for (char character : inputChars) {
            key.append(character);
        }

        return key.toString();
    }

}
