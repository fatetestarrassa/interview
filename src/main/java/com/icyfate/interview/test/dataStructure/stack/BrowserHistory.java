package com.icyfate.interview.test.dataStructure.stack;

import java.util.Stack;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/9/15 14:50
 */
public class BrowserHistory {
    Stack<String> stack1 = new Stack<>();//前进(访问新地址)操作存入,顶部为当前地址
    Stack<String> stack2 = new Stack<>();//后退操作存入

    public Boolean browse(String url){
        stack1.push(url);
        return true;
    }

    /**
     * 前进
     *
     * @return
     */
    public String goForward(){
        if(stack2.isEmpty()){
            return "";
        }
        String url = stack2.pop();
        stack1.push(url);
        return url;
    }

    /**
     * 后退
     *
     * @return
     */
    public String goBack(){
        if(stack1.isEmpty()){
            return "";
        }
        String url = stack1.pop();
        stack2.push(url);
        return url;
    }


    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory();
        System.out.println("前进，当前网址是：" + history.goForward());
        history.browse("百度");
        history.browse("新浪");
        history.browse("bilibili");
        history.browse("acFun");

        System.out.println("前进，当前网址是：" + history.goForward());
        System.out.println("后退，当前网址是：" + history.goBack());
        System.out.println("后退，当前网址是：" + history.goBack());
        System.out.println("后退，当前网址是：" + history.goBack());
        System.out.println("后退，当前网址是：" + history.goBack());
        System.out.println("后退，当前网址是：" + history.goBack());
    }
}
