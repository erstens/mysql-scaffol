package com.erstens.scaffold.builder.db;/**
 * Created by wang'ao on 2016/8/26 0026.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName Connector.class
 * @Description by wang'ao(连接器)
 * @Date 2016/8/26 0026 下午 6:43
 */
public class Connector {

    private static Connection conn ;

    private Connector() {

    }

    /**
     * 初始化
     */
    public static void init() {
        DBProperty property = DBProperty.getInstance();
        try {
            Class.forName("com.mysql.jdbc.Driver") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(property.getUrl()  , property.getUser() , property.getPwd() ) ;
            conn = con ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        return conn ;
    }

    /**
     * 关闭
     */
    public static void close() {
        try {
            if(null != conn && !conn.isClosed()) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
