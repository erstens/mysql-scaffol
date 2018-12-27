package com.erstens.scaffold.builder.utils;

public class ConverterDbToJavaUtils {
    public static String getJavaType(String dbType) {
        if(dbType.contains("varchar")) {
            return "String";
        }
        if(dbType.contains("int")) {
            return "Integer";
        }
        if(dbType.contains("Integer")) {
            return "Integer";
        }
        if(dbType.contains("datetime")) {
            return "Date";
        }
        return "String" ;
    }
}
