package com.erstens.scaffold;

import com.erstens.scaffold.builder.core.CodeBuilder;
import com.erstens.scaffold.builder.core.ColumnProp;
import com.erstens.scaffold.builder.core.ModelFullProp;
import com.erstens.scaffold.builder.db.Connector;
import com.erstens.scaffold.builder.db.DBProperty;
import com.erstens.scaffold.builder.db.StatementExe;
import com.erstens.scaffold.ui.DataMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoreHandler {

    public static void toFile() throws RuntimeException {
        //init db
        DataMap instance = DataMap.getInstance();
        String url = String.format("jdbc:mysql://%s:%s", instance.getHost(),instance.getPort());
        String user = instance.getUser();
        String pwd = instance.getPwd();
        String tableName = instance.getTable();
        String tableSchema = instance.getDb();
        String output = instance.getOutput();

        DBProperty.init(url,user,pwd,tableName,tableSchema);
        Connector.init();

        //general by mysql table
        List<ColumnProp> columnList = StatementExe.queryColumns()
                .stream()
                .map(ColumnProp::new)
                .collect(Collectors.toList());


        List<ModelFullProp> list = new LinkedList<>();
        Map<String,Object> modelType = instance.getModelType();
        for (Map.Entry<String, Object> current : modelType.entrySet()) {
            String k = current.getKey();
            String v = String.valueOf(current.getValue());

            ModelFullProp model = new ModelFullProp(k.toUpperCase(), tableName, v, output, columnList, "java");
            list.add(model);
        }
        CodeBuilder.toFile(list);
    }
}
