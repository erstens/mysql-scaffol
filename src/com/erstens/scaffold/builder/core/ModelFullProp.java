package com.erstens.scaffold.builder.core;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class ModelFullProp extends ModelBeetlProp implements Serializable {
    private static final long serialVersionUID = -198103558954569571L;
    private String templateContent ;
    private String outputFile;
    private String outputDir;
    private String fileType ;

    public ModelFullProp(String key, String tableName, String templateContent, String output, List<ColumnProp> columnList, String fileType) {
        super(key,tableName,columnList);
        this.templateContent = templateContent ;
        this.outputDir = output ;
        this.fileType = fileType;

        this.outputFile = this.outputDir + File.separator + super.getClassName() + "." + this.fileType;
    }

    public ModelBeetlProp getBeetlProp() {
        return new ModelBeetlProp(getKey(),getTableName(),getColumnPropList()) ;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
