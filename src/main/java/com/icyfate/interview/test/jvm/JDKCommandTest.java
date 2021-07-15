package com.icyfate.interview.test.jvm;

import com.sun.management.VMOption;
import sun.management.HotSpotDiagnostic;
import sun.plugin.javascript.navig.LinkArray;

import java.util.List;

/**
 * @author sunbing
 * @version 1.0
 * @since 2021/7/14 17:18
 */
public class JDKCommandTest {
    public static void main(String[] args) {
        HotSpotDiagnostic mxBean = new HotSpotDiagnostic();
        List<VMOption> list = mxBean.getDiagnosticOptions();
        for(VMOption temp : list){
            System.out.println(temp.getName() + " = " + temp.getValue());
        }
    }
}
