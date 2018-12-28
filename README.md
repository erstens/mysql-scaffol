mysql-scaffol

mysqltable to java code(dao and entity)

Features:

You can using these var:

- ${key} 
- ${className}
- ${entityName}
- ${tableName}  
- ${columnPropList}

columnPropList is List：

- ${dbName} 
- ${dbComment}
- ${dbType}
- ${javaType}
- ${javaName}
- ${javaHumpName}

list example 

    <%for(map in columnPropList){%>
    		${map.dbName}
    		${map.dbComment}
    <%}%>

about beetl

example

    import java.io.Serializable;
    import java.util.Date;
    
    public class ${className} implements Serializable {
    	<%for(map in columnPropList){%>
    	/**
    	 * ${map.dbComment}
    	 */
        private ${map.javaType} ${map.javaHumpName};
        <%}%>
    
        <%for(map in columnPropList){%>
        public ${map.javaType} get${map.javaName}() {
            return ${map.javaHumpName};
        }
    
        public void set${map.javaName}(${map.javaType} ${map.javaHumpName}) {
            this.${map.javaHumpName} = ${map.javaHumpName};
        }
        <%}%>
    }


