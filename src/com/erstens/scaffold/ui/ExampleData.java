package com.erstens.scaffold.ui;

public class ExampleData {
    public static final String DAO = "DAO可用变量\n" +
            "\n" +
            "key:${key}\n" +
            "className:${className}\n" +
            "entityName:${entityName}\n" +
            "tableName:${tableName}\n" +
            "\n" +
            "<%\n" +
            "for(map in columnPropList){\n" +
            "%>\n" +
            "    dbName,${map.dbName}\n" +
            "    dbComment,${map.dbComment}\n" +
            "    dbType,${map.dbType}\n" +
            "    javaType,${map.javaType}\n" +
            "    javaName,${map.javaName}\n" +
            "    javaHumpName,${map.javaHumpName}\n" +
            "--------------------------------------\n" +
            "<%\n" +
            "}\n" +
            "%>" ;
    public static final String ENTITY = "import java.io.Serializable;\n" +
            "import java.util.Date;\n" +
            "\n" +
            "public class ${className} implements Serializable {\n" +
            "\t<%for(map in columnPropList){%>\n" +
            "\t/**\n" +
            "\t * ${map.dbComment}\n" +
            "\t */\n" +
            "    private ${map.javaType} ${map.javaHumpName};\n" +
            "    <%}%>\n" +
            "\n" +
            "    <%for(map in columnPropList){%>\n" +
            "\n" +
            "    public ${map.javaType} get${map.javaName}() {\n" +
            "        return ${map.javaHumpName};\n" +
            "    }\n" +
            "\n" +
            "    public void set${map.javaName}(${map.javaType} ${map.javaHumpName}) {\n" +
            "        this.${map.javaHumpName} = ${map.javaHumpName};\n" +
            "    }\n" +
            "    <%}%>\n" +
            "}";

    public static final String USER_DIR = System.getProperty("user.home") ;

    public static final String PORT = "3306" ;
}
