package com.erstens.scaffold.builder.db;/**
 * Created by wang'ao on 2016/8/26 0026.
 */

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName DBProperty.class
 * @Description by wang'ao(数据库属性)
 * @Date 2016/8/26 0026 下午 6:41
 */
public class DBProperty {
    private String url;
    private String user;
    private String pwd;
    private String tableName;
    private String tableSchema;

    private static DBProperty instance;

    public static DBProperty getInstance() {
        return instance;
    }

    public static synchronized void init(String url, String user, String pwd, String tableName, String tableSchema) {
        instance = new DBProperty();
        instance.setUrl(url);
        instance.setUser(user);
        instance.setPwd(pwd);
        instance.setTableName(tableName);
        instance.setTableSchema(tableSchema);
    }


    public String getTableSchema() {
        return tableSchema;
    }

    private void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    private void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    private void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTableName() {
        return tableName;
    }

    private void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
