package com.erstens.scaffold.builder.utils;

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName StringOptUtils.class
 * @Description by wang'ao(自定字符串操作工具类)
 * @Date 2016/8/26 0026 下午 1:22
 */
public class StringOptUtils {
    /**
     * 首字符小写
     * @param s
     * @return
     */
    public static String firstCharToLower(String s) {
        return s.toLowerCase().charAt(0) + s.substring(1) ;
    }
    /**
     * 首字符大写
     * @param s
     * @return
     */
    public static String firstCharToUpper(String s) {
        return s.toUpperCase().charAt(0) + s.substring(1) ;
    }
    /**
     * 分隔字符'_'转换成首字母大写
     * @param s
     * @return
     */
    public static String toSplitHeadUpper(String s) {
        String[] split = s.split("_");
        StringBuffer buf = new StringBuffer() ;
        for(int i = 0 ;i < split.length ;i ++) {
            buf.append(firstCharToUpper(split[i])) ;
        }
        return buf.toString() ;
    }
    /**
     * 指定分隔字符''转换成首字母全部大写
     * @param s
     * @return
     */
    public static String toSplitHeadUpper(String s, String c) {
        String[] split = s.split(c);
        StringBuffer buf = new StringBuffer() ;
        for(int i = 0 ;i < split.length ;i ++) {
            buf.append(firstCharToUpper(split[i])) ;
        }
        return buf.toString() ;
    }
    /**
     * 分隔字符'_'第一个转换成首字母小写（此段第一个）
     * @param s
     * @return
     */
    public static String toHump(String s) {
        String res = toSplitHeadUpper(s) ;
        return res.toLowerCase().charAt(0) + res.substring(1) ;
    }
    /**
     * 分隔字符''第一个转换成首字母小写（此段第一个）
     * @param s
     * @return
     */
    public static String toHump(String s, String c) {
        String res = toSplitHeadUpper(s, c) ;
        return res.toLowerCase().charAt(0) + res.substring(1) ;
    }

}
