package com.icyfate.interview.test.dataStructure.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Stack其他应用：字符串反转  维护函数调用  （LIFO 后进先出的可以考虑Stack是否适用）
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/9/17 15:58
 */
public class StackOther {
    public static void main(String[] args) {
        String str = "abcefg";
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<str.length();i++){
            stack.push(str.charAt(i));
        }
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }
}
