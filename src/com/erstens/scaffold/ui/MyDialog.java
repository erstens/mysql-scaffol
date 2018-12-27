package com.erstens.scaffold.ui;

import com.erstens.scaffold.Constant;
import com.erstens.scaffold.builder.db.Connector;
import com.erstens.scaffold.builder.utils.FileUtils;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MyDialog extends DialogWrapper {
    private final MainJPanel panel;
    public final static int WIDTH = 574;
    public final static int HEIGHT = 573;


    public MyDialog(@Nullable Project project) {
        super(project);
        this.panel = new MainJPanel(this,project);

        init();
        this.setModal(true);
        this.setResizable(false);
        this.setOKButtonText("general");
        this.setCancelButtonText("cancel");

    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel;
    }

    @Override
    protected void doOKAction() {
        panel.general();

        //db info to local cache .
        FileUtils.writeObjectToFile(DataMap.getInstance(), Constant.DATA_MAP_FILENAME);
    }

    @Override
    public void doCancelAction() {
        super.doCancelAction();
        Connector.close();
    }
}
