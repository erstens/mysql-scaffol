package com.erstens.scaffold.builder.core;

import com.erstens.scaffold.Constant;
import com.erstens.scaffold.builder.utils.StringOptUtils;

import java.io.Serializable;
import java.util.List;

public class ModelBeetlProp implements Serializable {
    private static final long serialVersionUID = -198103558954569571L;
    private String key ;
    private String className ;
    private String tableName ;
    private String entityName ;

    private List<ColumnProp> columnPropList;

    public ModelBeetlProp() {}

    public ModelBeetlProp(String key, String tableName, List<ColumnProp> columns) {
        this.key = key;
        this.tableName = tableName;
        this.columnPropList = columns;
        this.className = StringOptUtils.toSplitHeadUpper(tableName) + (Constant.ENTITY_FLAG.equals(key) ? "" : key);
        this.entityName = StringOptUtils.toSplitHeadUpper(tableName) ;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnProp> getColumnPropList() {
        return columnPropList;
    }

    public void setColumnPropList(List<ColumnProp> columnPropList) {
        this.columnPropList = columnPropList;
    }

    @Override
    public String toString() {
        return "ModelBeetlProp{" +
                "key='" + key + '\'' +
                ", className='" + className + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columnPropList=" + columnPropList +
                '}';
    }
}
