package com.erstens.scaffold.builder.utils;/**
 * Created by wang'ao on 2016/8/26 0026.
 */

import java.io.*;

/**
 * @author wang'ao
 * @version 1.0.0
 * @ClassName FileUtils.class
 * @Description by wang'ao(IO工具类)
 * @Date 2016/8/26 0026 下午 4:23
 */
public class FileUtils {
    public static void createFile(String context ,String path) {
        File f = new File(path) ;
        if(!f.exists()) {
            try {
                if(!f.getParentFile().exists()) {
                     f.getParentFile().mkdirs();
                }
                f.createNewFile() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fou = null ;
        try {
            fou = new FileOutputStream(f) ;
            byte[] b = context.getBytes();
            fou.write(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fou.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void writeObjectToFile(Object o,String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            else {
                f.delete() ;
                f.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(o);

            oos.close();                        //关闭文件流
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object getObjectFromFile(String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            return ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null ;
    }
}
