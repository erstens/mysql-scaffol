package com.erstens.scaffold.ui;

import com.erstens.scaffold.Constant;
import com.erstens.scaffold.builder.utils.FileUtils;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class DataMap implements Serializable {
    private static final long serialVersionUID = -7292775147495756703L;
    private String host ;
    private String port ;
    private String user ;
    private String pwd ;
    private String db ;
    private String table ;
    private String output ;
    private List<String> checkedList ;
    private Map<String,Object> modelType ;

    private static DataMap instance = null ;

    private DataMap() {
    }

    public static synchronized DataMap getInstance() {
        //db info to local cache .
        File f = new File(Constant.DATA_MAP_FILENAME);
        if(null == instance) {
            if(f.exists()) {
                instance = ((DataMap) FileUtils.getObjectFromFile(Constant.DATA_MAP_FILENAME));
                return instance;
            }
            else {
                instance = new DataMap();
                return instance;
            }
        }
        return instance;
    }
    public static boolean alreadeyInited(){
        if(null == instance) {
            //db info to local cache .
            File f = new File(Constant.DATA_MAP_FILENAME);
            if(f.exists()) {
                return true;
            }
            return false ;
        }
        return true ;
    }

    public List<String> getCheckedList() {
        return checkedList;
    }

    public void setCheckedList(List<String> checkedList) {
        this.checkedList = checkedList;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public DataMap setHost(String host) {
        this.host = host;
        return this ;
    }

    public String getUser() {
        return user;
    }

    public DataMap setUser(String user) {
        this.user = user;
        return this ;

    }

    public String getPwd() {
        return pwd;
    }

    public DataMap setPwd(String pwd) {
        this.pwd = pwd;
        return this ;

    }

    public String getDb() {
        return db;
    }

    public DataMap setDb(String db) {
        this.db = db;
        return this ;

    }

    public String getTable() {
        return table;
    }

    public DataMap setTable(String table) {
        this.table = table;
        return this ;

    }

    public String getOutput() {
        return output;
    }

    public DataMap setOutput(String output) {
        this.output = output;
        return this ;

    }

    public Map getModelType() {
        return modelType;
    }

    public DataMap setModelType(Map modelType) {
        this.modelType = modelType;
        return this ;
    }

    @Override
    public String toString() {
        return "DataMap{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                ", db='" + db + '\'' +
                ", table='" + table + '\'' +
                ", output='" + output + '\'' +
                ", modelType=" + modelType +
                '}';
    }
}
