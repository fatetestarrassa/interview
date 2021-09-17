package com.icyfate.interview.test.dataStructure.stack;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Stack;

/**
 * 验证字符串有效性：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断该字符串是否有效。
 *      有效性条件：
 *          1.左括号必须有相同类型的右括号闭合      比如{]就是左右括号类型不对
 *          2.括号顺序必须一致                    比如([)]就是顺序不一致
 *
 *      实现方法：利用stack实现
 *          1.map中存入所有括号的  key（右括号）-value（左括号）
 *          2.遍历字符串，遇到左括号压入栈中，遇到右括号从map取出响应左括号和栈顶元素比较，不一致返回false，一致则继续，循环完毕
 *              后，如果栈为空，返回true，否则返回false
 *
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/9/17 13:56
 */
public class StringEffectiveness {

    public static boolean checkEffectiveness(String str){
        Map<Character,Character> map = Maps.newTreeMap();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++){
            char temp = str.charAt(i);
            if(map.containsKey(temp)){
                //右括号
                if(stack.isEmpty()){
                    return false;
                }
                if(map.get(temp) != stack.pop()){
                    return false;
                }

            } else {
                //左括号
                stack.push(temp);
            }
        }


        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(checkEffectiveness("(){}[]"));
        System.out.println(checkEffectiveness("(]"));
        System.out.println(checkEffectiveness("([)]"));
    }
}
