package com.erstens.scaffold;

import java.io.File;

public interface Constant {
    String ENTITY_FLAG = "ENTITY";
    String DAO_FLAG = "DAO";
    String CHECKED_ENTITY_TXT = "(ENTITY)";
    String CHECKED_DAO_TXT = "*DAO";

    String DATA_MAP_FILENAME = System.getProperty("user.home") + File.separator + ".ideaPlugins_mysql-scaffold" + File.separator + "DataMap.obj" ;
}
