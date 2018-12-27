package com.erstens.scaffold.builder.core;

import com.erstens.scaffold.builder.utils.BeanUtils;
import com.erstens.scaffold.builder.utils.FileUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.List;

/**
 * 建造类,用于将数据列绑定在模版中
 * Created by wang'ao on 2016/11/24 0024.
 */
public class CodeBuilder {
    private CodeBuilder() {
    }

    public static void toFile(List<ModelFullProp> modelPropList) throws RuntimeException{
        for (ModelFullProp model : modelPropList) {
            String text = getCodeString(model.getTemplateContent(),model.getBeetlProp());
            FileUtils.createFile(text,model.getOutputFile());
        }
    }

    private static String getCodeString(String content, Object bean) {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate(content);
            t.binding(BeanUtils.transBean2Map(bean));
            return t.render();
        } catch (RuntimeException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
