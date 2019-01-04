# mysql-scaffol
mysql-table to java code(dao and entity)

# starter:

1. click `memu`, last one.
2. fill  `db-info` and `output dir` .
3. click `beetl template settings` , expan panel .
4. click `one` left , edit or paste the template for beetl .
5. click `general` button.

# features:

Editing `template` .You can using these `var`:
* ${key} 
* ${className}
* ${entityName}
* ${tableName}  
* ${columnPropList}

`columnPropList` is List：

* ${dbName} 
* ${dbComment}
* ${dbType}
* ${javaType}
* ${javaName}
* ${javaHumpName}

> list example 
>
> ``` java
> <%for(map in columnPropList){%>
> 		${map.dbName}
> 		${map.dbComment}
> <%}%>
> ```

##### about [beetl](http://ibeetl.com)

# example
``` java
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
```



# template

In template directory .(dao,entity)



# cache

Your data is saved in `{userdir}/.ideaPlugins_mysql-scaffold/DataMap.obj`

> If there is a problem with the data, resetting the data requires deleting it.