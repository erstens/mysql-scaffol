/*
 * Created by JFormDesigner on Mon Dec 24 16:57:25 CST 2018
 */

package com.erstens.scaffold.ui;

import com.erstens.scaffold.Constant;
import com.erstens.scaffold.CoreHandler;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

/**
 * @author wang'ao
 */
public class MainJPanel extends JPanel  {

    public static final int WIDTH = 550 ;
    public static final int HEIGHT = 350 ;

    public static final int PANEL_CONTEXT_WIDTH = 500 ;
    public static final int PANEL_CONTEXT_HEIGHT = 280 ;

    public static final int PANEL_INNER_WIDTH = 450 ;
    public static final int PANEL_INNER_HEIGHT = 240 ;

    public static final int TEMPLATE_LEFT_WIDTH = 110 ;
    public static final int TEMPLATE_RIGHT_WIDTH = 380 ;
    public static final int TEMPLATE_HEIGHT = 120 ;

    public static final int LINKLABEL_HEIGHT = 40 ;
    public static final int LINKLABEL_WIDTH = 240 ;

    private JLabel expandLink;
    private JList modelTypeList;

    private JTextArea jTextArea;
    private JPanel templatePanel;
    private JTextField hostField;
    private JTextField portField;
    private JTextField userField;
    private JTextField pwdField;
    private JTextField dbField;
    private JTextField tableField;
    private JCheckBox entityCheckBox;
    private JCheckBox daoCheckBox;

    private Project project = null;
    private MyDialog myDialog = null ;
    private MainJPanel _this;


    private TextFieldWithBrowseButton textFieldWithBrowseButton;

    public MainJPanel(MyDialog myDialog, Project project) {
        this.project = project ;
        this.myDialog = myDialog ;
        this._this = this ;

        JPanel baseArea = getBaseArea();
        JPanel templateLinkArea = getTemplateLinkArea();
        JPanel templateArea = getTemplateArea();

        //init property .
        this.add(getBox(baseArea, templateLinkArea, templateArea),BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBorder(BorderFactory.createEtchedBorder());

        //init listeners .
        this.expandLink.addMouseListener(expandLinkClick);
        this.modelTypeList.addListSelectionListener(listSelectionListener);
        this.jTextArea.addKeyListener(textAreaKeyListener);

        //init by data .
        this.initFromStorage() ;
    }

    @NotNull
    private Box getBox(JPanel baseArea, JPanel templateLinkArea, JPanel templateArea) {
        Box hbox1=Box.createHorizontalBox();
        Box hbox2=Box.createHorizontalBox();
        Box hbox3=Box.createHorizontalBox();

        hbox1.add(baseArea);
        hbox2.add(templateLinkArea);
        hbox3.add(templateArea);

        Box vbox = Box.createVerticalBox();
        vbox.add(hbox1);
        vbox.add(hbox2);
        vbox.add(hbox3);

        return vbox;
    }

    public void initFromStorage() {
        if(DataMap.alreadeyInited()) {
            DataMap dataMap = DataMap.getInstance();
            hostField.setText(dataMap.getHost());
            portField.setText(dataMap.getPort());
            userField.setText(dataMap.getUser());
            pwdField.setText(dataMap.getPwd());
            dbField.setText(dataMap.getDb());
            tableField.setText(dataMap.getTable());
            textFieldWithBrowseButton.setText(dataMap.getOutput());
            daoCheckBox.setSelected(false);
            entityCheckBox.setSelected(false);
            Optional.ofNullable(dataMap.getModelType()).map(Map::keySet).orElseGet(HashSet::new).stream().forEach(s -> {
                String key = String.valueOf(s);
                if (Constant.DAO_FLAG.equals(key)) {
                    if (dataMap.getCheckedList().contains(key)) {
                        daoCheckBox.setSelected(true);
                    }
                    DataMapTemplate.dao = String.valueOf(dataMap.getModelType().get(Constant.DAO_FLAG));
                }
                if (Constant.ENTITY_FLAG.equals(key)) {
                    if (dataMap.getCheckedList().contains(key)) {
                        entityCheckBox.setSelected(true);
                    }
                    DataMapTemplate.entity = String.valueOf(dataMap.getModelType().get(Constant.ENTITY_FLAG));
                }
            });
        }
        if (!DataMap.alreadeyInited()) {
            daoCheckBox.setSelected(true);
            entityCheckBox.setSelected(true);
            portField.setText(ExampleData.PORT);
            modelTypeList.setSelectedIndex(0);
            jTextArea.setText(ExampleData.ENTITY);
            textFieldWithBrowseButton.setText(ExampleData.USER_DIR);

            DataMapTemplate.entity = ExampleData.ENTITY;
            DataMapTemplate.dao = ExampleData.DAO;
        }
        //close expand
        templatePanel.setVisible(false);
    }
    KeyListener textAreaKeyListener = new KeyAdapter(){
        @Override
        public void keyReleased(KeyEvent e) {
            if (Constant.CHECKED_DAO_TXT.equals(String.valueOf(modelTypeList.getSelectedValue()))) {
                DataMapTemplate.dao = jTextArea.getText();
            }
            if (Constant.CHECKED_ENTITY_TXT.equals(String.valueOf(modelTypeList.getSelectedValue()))) {
                DataMapTemplate.entity = jTextArea.getText();
            }
        }
    };
    MouseListener expandLinkClick = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e) {
            if (templatePanel.isVisible()) {
                templatePanel.setVisible(false);
                _this.myDialog.setSize(MyDialog.WIDTH,-1);
                return ;
            }
            if (!templatePanel.isVisible()) {
                templatePanel.setVisible(true);
                _this.myDialog.setSize(MyDialog.WIDTH,MyDialog.HEIGHT);
                return ;
            }
        }
    };
    ListSelectionListener listSelectionListener = e -> {
        if (Constant.CHECKED_DAO_TXT.equals(String.valueOf(modelTypeList.getSelectedValue()))) {
            jTextArea.setText(DataMapTemplate.dao);
        }
        if (Constant.CHECKED_ENTITY_TXT.equals(String.valueOf(modelTypeList.getSelectedValue()))) {
            jTextArea.setText(DataMapTemplate.entity);
        }
    };

    private JPanel getBaseArea() {
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(9,2));
        panel.setPreferredSize(new Dimension(50, 50));

        JLabel userLabel0 = new JLabel("driver:");
        panel.add(userLabel0);
        JRadioButton jRadioButton = new JRadioButton("mysql 5.6+");
        jRadioButton.setBounds(0,0,1,1);
        jRadioButton.setSelected(true);
        jRadioButton.setEnabled(false);
        panel.add(jRadioButton);

        JLabel userLabel1 = new JLabel("host:");
        panel.add(userLabel1);
        this.hostField = new JTextField();
        panel.add(hostField);

        JLabel labelPort = new JLabel("port:");
        panel.add(labelPort);
        this.portField = new JTextField();
        panel.add(portField);

        JLabel userLabel2 = new JLabel("user:");
        panel.add(userLabel2);
        this.userField = new JTextField();
        panel.add(userField);

        JLabel userLabel3 = new JLabel("pwd:");
        panel.add(userLabel3);
        this.pwdField = new JTextField();
        panel.add(pwdField);

        JLabel userLabel4 = new JLabel("db:");
        panel.add(userLabel4);
        this.dbField = new JTextField();
        panel.add(dbField);

        JLabel userLabel5 = new JLabel("table:");
        panel.add(userLabel5);

        this.tableField = new JTextField();
        panel.add(tableField);

        JLabel userLabel6 = new JLabel("model:");
        panel.add(userLabel6);

        panel.add(getCheckboxArea());

        JLabel userLabel7 = new JLabel("output:");
        panel.add(userLabel7);

        this.textFieldWithBrowseButton = new TextFieldWithBrowseButton();
        textFieldWithBrowseButton.addBrowseFolderListener("choose output directory", null, project,
                new FileChooserDescriptor(false, true, false, false, false, false));

        panel.add(textFieldWithBrowseButton);

        panel.setPreferredSize(new Dimension(PANEL_INNER_WIDTH, PANEL_INNER_HEIGHT));

        return getMarginPanel(panel,PANEL_CONTEXT_WIDTH,PANEL_CONTEXT_HEIGHT);
    }

    public JPanel getCheckboxArea() {
        JPanel panel = new JPanel() ;

        panel.setLayout(new GridLayout(1,2));

        this.entityCheckBox = new JCheckBox(Constant.CHECKED_ENTITY_TXT);
        entityCheckBox.setSelected(true);
        panel.add(entityCheckBox);

        this.daoCheckBox = new JCheckBox(Constant.CHECKED_DAO_TXT);
        daoCheckBox.setSelected(true);
        panel.add(daoCheckBox);

        return panel;
    }

    public JPanel getTemplateArea() {
        this.templatePanel = new JPanel();

        templatePanel.setLayout(new FlowLayout());

        ListModel jListModel =  new DefaultComboBoxModel(new String[] { Constant.CHECKED_ENTITY_TXT,Constant.CHECKED_DAO_TXT  });
        this.modelTypeList = new JList();
        modelTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelTypeList.setModel(jListModel);
        modelTypeList.setBorder(BorderFactory.createEtchedBorder());
        modelTypeList.setPreferredSize(new Dimension(TEMPLATE_LEFT_WIDTH, TEMPLATE_HEIGHT));


        templatePanel.add(modelTypeList);

        this.jTextArea = new JTextArea();
        jTextArea.setCaretPosition(0);
        JScrollPane scroll = new JScrollPane(jTextArea);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setAutoscrolls(true);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEtchedBorder());
        scroll.setPreferredSize(new Dimension(TEMPLATE_RIGHT_WIDTH, TEMPLATE_HEIGHT));

        templatePanel.add(scroll);

        return templatePanel;

    }

    public JPanel getTemplateLinkArea() {
        JPanel panel = new JPanel();
        this.expandLink = new JLabel("--- beetl template settings ---");
        expandLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        expandLink.setForeground(Color.BLUE);

        panel.add(expandLink);
        panel.setPreferredSize(new Dimension(LINKLABEL_WIDTH, LINKLABEL_HEIGHT));

        return panel;

    }

    private static boolean blank(String str) {
        if (null == str) {
            return true;
        }
        if ("".equals(str.trim())) {
            return true;
        }
        return false ;
    }

    public static JPanel getMarginPanel(JPanel panel, int width, int height) {
        JPanel panelMargin = new JPanel();

        JPanel panelPadding = new JPanel();
        panelPadding.setBorder(BorderFactory.createEtchedBorder());
        panelPadding.setPreferredSize(new Dimension(width, height));

        panelPadding.add(panel);

        panelMargin.add(panelPadding);

        return panelMargin;
    }

    public void general() {
        if (blank(hostField.getText())) {
            Messages.showMessageDialog(project, "host is null .", "error", Messages.getErrorIcon());
            return ;
        }
        if (blank(userField.getText())) {
            Messages.showMessageDialog(project, "user is null .", "error", Messages.getErrorIcon());
            return ;
        }
        if (blank(pwdField.getText())) {
            Messages.showMessageDialog(project, "pwd is null .", "error", Messages.getErrorIcon());
            return ;
        }
        if (blank(dbField.getText())) {
            Messages.showMessageDialog(project, "db is null .", "error", Messages.getErrorIcon());
            return ;
        }
        if (blank(tableField.getText())) {
            Messages.showMessageDialog(project, "table is null .", "error", Messages.getErrorIcon());
            return ;
        }
        if (!entityCheckBox.isSelected() && !daoCheckBox.isSelected()) {
            Messages.showMessageDialog(project, "must be select model .", "error", Messages.getErrorIcon());
            return ;
        }
        DataMap dataMap = DataMap.getInstance();

        dataMap.setHost(hostField.getText());
        dataMap.setPort(portField.getText());
        dataMap.setUser(userField.getText());
        dataMap.setPwd(pwdField.getText());
        dataMap.setDb(dbField.getText());
        dataMap.setTable(tableField.getText());
        dataMap.setOutput(textFieldWithBrowseButton.getText());



        Map m = Optional.ofNullable(dataMap.getModelType()).orElseGet(HashMap::new);
        m.put(Constant.ENTITY_FLAG,DataMapTemplate.entity );
        m.put(Constant.DAO_FLAG,DataMapTemplate.dao );

        dataMap.setModelType(m);

        if(entityCheckBox.isSelected()) {
            List list = Optional.ofNullable(dataMap.getCheckedList()).orElseGet(LinkedList::new);
            if (!list.contains(Constant.ENTITY_FLAG)) {
                list.add(Constant.ENTITY_FLAG);
            }
            dataMap.setCheckedList(list);
        }

        if(daoCheckBox.isSelected()) {
            List list = Optional.ofNullable(dataMap.getCheckedList()).orElseGet(LinkedList::new);
            if (!list.contains(Constant.DAO_FLAG)) {
                list.add(Constant.DAO_FLAG);
            }
            dataMap.setCheckedList(list);
        }

        try {
            CoreHandler.toFile();
            Messages.showMessageDialog(project, "success ." , "info", Messages.getInformationIcon());
        } catch (RuntimeException e) {
            Messages.showMessageDialog(project, "error .", "error", Messages.getErrorIcon());
        }
    }
}
