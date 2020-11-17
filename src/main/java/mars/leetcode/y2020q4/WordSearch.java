package mars.leetcode.y2020q4;

import org.junit.Test;

import java.util.Arrays;

public class WordSearch {

    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     *  
     *
     * 示例:
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *  
     *
     * 提示：
     *
     * board 和 word 中只包含大写和小写英文字母。
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     * 1 <= word.length <= 10^3
     *
     * https://leetcode-cn.com/problems/word-search/solution/dan-ci-sou-suo-by-leetcode-solution/
     */
    @Test
    public void test() {
        char [][] input = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        boolean res = exist(input, "ABCB");
        System.currentTimeMillis();
    }

    public boolean exist(char[][] board, String word) {
        boolean [][] search = new boolean[board.length][board[0].length];
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                boolean res = search(board, search, word.toCharArray(), 0, i, j);
                if (res) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean search(char [][] input, boolean[][] searched, char [] word, int index, int x, int y) {
        if (input[x][y] != word[index]) {
            //已经搜索过或者不匹配
            return false;
        } else if (index >= word.length-1) {
            return true;
        }

        //命中了同样的字母
        searched[x][y] = true;
        int [][] around = new int[][]{{x+1,y},{x-1,y},{x,y+1},{x,y-1}};
        for (int[] item:around) {
            if (item[0]>=0 && item[0]<input.length && item[1]>=0 && item[1]<input[0].length) {
                if(searched[item[0]][item[1]]) {
                    continue;
                }

                boolean res = search(input, searched, word, index+1, item[0], item[1]);
                if (res) {
                    return true;
                }
            }
        }

        //本轮遍历无结果，把当前节点置为未搜索过的状态
        searched[x][y] = false;
        return false;
    }
}
