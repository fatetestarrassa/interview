package com.icyfate.interview.test.core.reflect;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/6/16 17:30
 */
public class TargetObject {
    private String value;

    public TargetObject() {
        this.value = "xxx";
    }

    public void publicMethod(String s){
        System.out.println("exe public method :" + s);
    }

    private void privateMethd(){
        System.out.println("exe pirvate method:" + value);
    }
}
