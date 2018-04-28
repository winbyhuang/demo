package com.example.demo.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class IntegerUtil {
//    private static Function<String, Integer> function = Integer::valueOf;

    private static BinaryOperator<String> bina = (x, y) -> {
        if (null != x && !"".equals(x) && null != y && !"".equals(y) && x.equals(y)) {
            return "1";
        } else {
            return "0";
        }
    };

    private static Predicate<String> pre = (x) -> {
        return null != x && !"".equals(x);
    };

    public static Boolean notNull(String s) {
        return pre.test(s);
    }
    public static String equls(String x,String y) {
        StringUtils.equals("1","2");
//        StringUtils.isNotEmpty("1","2");
//        StringUtils.isNotBlank("1","2");
        return bina.apply(x,y);
    }


}
