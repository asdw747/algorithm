package mars.leetcode.y2019m05;

import org.apache.commons.lang.math.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
    德州扑克

    一副扑克有52张牌，每张派由一个花色和一个数字组成

    花色为以下4者之一
    方片 D
    黑桃 S
    红桃 H
    梅花 C

    数字为以下13者之一
    2,3,4,5,6,7,8,9,T,J,Q,K,A

    花色无大小，数字2最小，A最大
    一手牌有5张，根据花色和数字不同，其大小按照以下规则决定
    同花顺>铁支>葫芦>同花>顺子>三条>两对>对子>散牌

    同花顺：相同花色的顺子
    铁支：四张一样的牌
    葫芦：三条加对子
    同花：五张牌花色相同
    顺子：五张相连的牌
    三条：三张一样的牌
    两对：两个对子
    对子：一个对子
    散牌：散牌比最大的一张牌

    输入：Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH
    输出： White wins - high card: Ace

    输入：Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S
    输出： Black wins - full house

    输入：Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH
    输出： Black wins - high card: 9

    输入：Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH
    输出： Tie
 **/
public class Poker {

    private static List<Integer> Black = new ArrayList<>();
    private static List<Integer> White = new ArrayList<>();

    private static int []v = new int[128];

    private static void init() {
        char [] suit = "CDHS".toCharArray();
        char [] rank = "23456789TJQKA".toCharArray();
        for(int i=0; i<suit.length; i++)
            v[suit[i]] = i;
        for(int i=0; i<rank.length; i++)
            v[rank[i]] = i+2;
    }

    private static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("Enter your value:");
        try {
            str = br.readLine();
            String [] blackArray = str.split(" ");
            for (int i=0; i<5; i++) {
                char a = blackArray[i].toCharArray()[0];
                char b = blackArray[i].toCharArray()[1];
                Black.add(v[a]*4 + v[b]);
            }

            str = br.readLine();
            String [] whiteArray = str.split(" ");
            for (int i=0; i<5; i++) {
                char a = whiteArray[i].toCharArray()[0];
                char b = whiteArray[i].toCharArray()[1];
                White.add(v[a]*4 + v[b]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(Black);
        Collections.sort(White);

        System.out.println(Black);
        System.out.println(White);
    }

    //同花顺
    private static int straightFlush(List<Integer> D) {
        if(flush(D) == -1) return -1;
        return straight(D);
    }

    //铁支
    private static int four(List<Integer> D) {
        if(D.get(1) / 4 == D.get(2) / 4 &&
                D.get(2) / 4 == D.get(3) / 4) {
            if(D.get(0) / 4 == D.get(1) / 4)
                return D.get(0) / 4;
            if(D.get(4) / 4 == D.get(3) / 4)
                return D.get(4) / 4;
        }
        return -1;
    }

    //葫芦
    private static int fullHouse(List<Integer> D) {
        if((D.get(0) / 4 == D.get(1) / 4 &&
                D.get(4) / 4 == D.get(3) / 4)
                &&
                (D.get(2) / 4 == D.get(1) / 4 ||
                        D.get(2) / 4 == D.get(3) / 4)
                &&
                (D.get(0) / 4 != D.get(4) / 4)) {
            return D.get(2) / 4;
        }
        return -1;
    }

    //同花
    private static int flush(List<Integer> D) {
        for(int i = 0; i < 4; ++i)
            if(D.get(i) % 4 != D.get(i+1) % 4)
                return -1;
        return D.get(4) / 4;
    }

    //顺子
    private static int straight(List<Integer> D) {
        for(int i = 0; i < 4; ++i)
            if(D.get(i) / 4 + 1 != D.get(i+1) / 4)
                return -1;
        return D.get(4) / 4;
    }

    //三条
    private static int three(List<Integer> D) {
        if(D.get(0) / 4 == D.get(1) / 4 &&
                D.get(1) / 4 == D.get(2) / 4)
            return D.get(0) / 4;
        if(D.get(1) / 4 == D.get(2) / 4 &&
                D.get(2) / 4 == D.get(3) / 4)
            return D.get(1) / 4;
        if(D.get(2) / 4 == D.get(3) / 4 &&
                D.get(3) / 4 == D.get(4) / 4)
            return D.get(2) / 4;
        return -1;
    }

    //两对
    private static int twoPairs(List<Integer> D) {
        if(D.get(0) / 4 == D.get(1) / 4 &&
                D.get(2) / 4 == D.get(3) / 4)
            return D.get(2)/4*169+D.get(0)/4*13+D.get(4);
        if(D.get(0) / 4 == D.get(1) / 4 &&
                D.get(3) / 4 == D.get(4) / 4)
            return D.get(4)/4*169+D.get(0)/4*13+D.get(2);
        if(D.get(1) / 4 == D.get(2) / 4 &&
                D.get(4) / 4 == D.get(3) / 4)
            return D.get(2)/4*169+D.get(3)/4*13+D.get(0);
        return -1;
    }

    //一对
    private static int onePair(List<Integer> D) {
        int base;
        int counter = 0;
        int x = 0;
        int y = 0;
        boolean found = false;

        for(int i = 0; i < 4;) {
            if(D.get(i) / 4 == D.get(i+1) / 4) {
                found = true;
                y = D.get(i) / 4;
                i += 2;
            }
            else {
                base = NumberUtils.toInt(String.valueOf(Math.pow(13, counter)));
                counter ++;
                x += base * D.get(i) / 4;
                i += 1;
            }
        }
        if(!found) return -1;
        return y*13*13*13 + x;
    }

    //散牌
    private static int highCard(List<Integer> D) {
        int ans = 0;
        for(int i = 4; i >= 0; --i) {
            ans *= 13;
            ans += D.get(i) / 4;
        }
        return ans;
    }

    private static int judge() {
        String className = "mars.LeetCode.Y2019M05.Poker";
        String [] methodNames = {"straightFlush", "four", "fullHouse", "flush",
                "straight", "three", "twoPairs", "onePair", "highCard"};
        try {
            Class clz = Class.forName(className);
            Object obj = clz.newInstance();
            //获取方法
            for (String methodName : methodNames) {
                Method m = obj.getClass().getDeclaredMethod(methodName, List.class);
                int blackRes = (Integer) m.invoke(obj, Black);
                int whiteRes = (Integer) m.invoke(obj, White);

                if (blackRes > whiteRes) {
                    System.out.println(methodName);
                    return 1;
                } else if (blackRes < whiteRes) {
                    System.out.println(methodName);
                    return -1;
                } else if (blackRes != -1) {
                    System.out.println(methodName);
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    public static void main(String[] args){

        init();
        input();

        int res = judge();
        if (res == 1) {
            System.out.println("Black wins");
        } else if (res == -1) {
            System.out.println("White wins");
        } else {
            System.out.println("Tie");
        }

    }

}