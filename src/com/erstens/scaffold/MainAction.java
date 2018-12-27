package com.erstens.scaffold;

import com.erstens.scaffold.ui.MyDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;

public class MainAction extends AnAction {
    public void actionPerformed(AnActionEvent event) {
        MyDialog demoDialog = new MyDialog(event.getData(PlatformDataKeys.PROJECT_CONTEXT));
        demoDialog.show();
    }
}