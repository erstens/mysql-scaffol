package com.erstens.scaffold.builder.core;

import com.erstens.scaffold.builder.utils.StringOptUtils;
import com.erstens.scaffold.builder.utils.ConverterDbToJavaUtils;

import java.io.Serializable;
import java.util.Map;

public class ColumnProp implements Serializable {
    private static final long serialVersionUID = 8168635250804817705L;
    private String dbName ;
    private String dbType ;
    private String dbComment ;
    private String javaName ;
    private String javaHumpName ;
    private String javaType ;
    private String dbDefault ;

    public ColumnProp(Map m) {
        this.dbName = String.valueOf(m.getOrDefault("name", ""));
        this.dbType = String.valueOf(m.getOrDefault("type", ""));
        this.dbComment = String.valueOf(m.getOrDefault("comment", ""));
        this.dbDefault = String.valueOf(m.getOrDefault("default", ""));
        this.javaName = StringOptUtils.toSplitHeadUpper(dbName) ;
        this.javaHumpName = StringOptUtils.toHump(dbName) ;
        this.javaType = ConverterDbToJavaUtils.getJavaType(dbType) ;
    }

    public String getDbDefault() {
        return dbDefault;
    }

    public void setDbDefault(String dbDefault) {
        this.dbDefault = dbDefault;
    }

    public String getJavaHumpName() {
        return javaHumpName;
    }

    public void setJavaHumpName(String javaHumpName) {
        this.javaHumpName = javaHumpName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbComment() {
        return dbComment;
    }

    public void setDbComment(String dbComment) {
        this.dbComment = dbComment;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    @Override
    public String toString() {
        return "ColumnProp{" +
                "dbName='" + dbName + '\'' +
                ", dbType='" + dbType + '\'' +
                ", dbComment='" + dbComment + '\'' +
                ", javaName='" + javaName + '\'' +
                ", javaType='" + javaType + '\'' +
                '}';
    }
}
