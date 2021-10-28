package com.icyfate.interview.test.arithmetic;

/**
 * 汉诺塔游戏
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/10/28 15:59
 */
public class HanoiDemo {

    private static int num = 0;

    public static void main(String[] args) {
        hanoi(4,"a","b","c");
    }

    /**
     * 汉诺塔游戏：共三个柱子a,b,c。a柱子从小至大放着n个盘子(1,2,3...n),要求把a柱子所有盘子按从小至大移动到c柱子。
     *            一次只能移动一个盘子，并且大盘子不能在小盘子上面。
     *
     * 思路：不管多少个盘子，都始终看做2个盘子:最下面的盘子和上面的所有盘子，递归的进行下面的步骤
     *      1.上面的所有盘子从a移动到b
     *      2.最下面的盘子从a移动到c
     *      3.上面所有盘子从b移动到c
     *
     *      回归条件是只有一个盘子，直接从a移动到c
     *
     * @param count 盘子数量
     * @param a     盘子开始存在的柱子
     * @param b     中转柱子
     * @param c     目标柱子，要移动到的柱子
     */
    public static void hanoi(int count,String a,String b,String c){
        if(count == 1){
            System.out.println(++num  + " 第" + count + "个盘子从" + a + "移动到" + c);
        } else {
            hanoi(count -1,a,c,b);
            System.out.println(++num  + " 第" + count  + "个盘子从" + a + "移动到" + c);
            hanoi(count - 1,b,a,c);
        }


    }
}
