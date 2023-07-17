package com.github.zjz6b6.ideaplugindemo.demo.swing;


import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ListTest {
    JFrame jf = new JFrame("列表框测试");
    String[] books = {"java自学宝典", "轻量级javaEE企业应用实战", "Android基础教程", "jQuery实战教程", "SpringBoot企业级开发"};

    //定义 布局选择按钮 所在的面板
    JPanel layoutPanel = new JPanel();
    ButtonGroup layoutGroup = new ButtonGroup();

    //定义 选择模式按钮 所在面板
    JPanel selectModePanel = new JPanel();
    ButtonGroup selectModeGroup = new ButtonGroup();

    //用一个字符串数组来创建一个JList对象
    JList<String> bookList;
    JComboBox<String> bookSelector;

    public void init() {
        //组装视图

        //组装Jlist相关内容
        bookList = new JList<>(books);

        addBtn2LayoutPanel("纵向滚动", JList.VERTICAL);
        addBtn2LayoutPanel("纵向换行", JList.VERTICAL_WRAP);
        addBtn2LayoutPanel("横向换行", JList.HORIZONTAL_WRAP);

        addBtn2SelectModelPanel("无限制", ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        addBtn2SelectModelPanel("单选", ListSelectionModel.SINGLE_SELECTION);
        addBtn2SelectModelPanel("单范围", ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        //对JList做设置
        bookList.setVisibleRowCount(3);
        bookList.setSelectionInterval(2, 4);

        Box bookListVBox = Box.createVerticalBox();
        bookListVBox.add(new JScrollPane(bookList));
        bookListVBox.add(layoutPanel);
        bookListVBox.add(selectModePanel);

        //组装JComboBox
        Vector<String> vector = new Vector<>();
        for (int i = 0; i < books.length; i++) {
            vector.add(books[i]);
        }

        bookSelector = new JComboBox<>(vector);
        bookSelector.setEditable(true);
        bookSelector.setMaximumRowCount(3);

        //组装顶部的左右两部分
        Box topBox = Box.createHorizontalBox();
        topBox.add(bookListVBox);
        JPanel bookSelectPanel = new JPanel();
        bookSelectPanel.add(bookSelector);
        topBox.add(bookSelectPanel);

        //组装底部
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JTextArea jta = new JTextArea(6, 30);
        jta.setText("请选择...");
        bottomPanel.add(jta);

        JTextArea content = new JTextArea(6, 30);
        bottomPanel.add(content);

        //处理条目选中事件
        bookList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //获取当前选中的条目
                java.util.List<String> selectedValuesList = bookList.getSelectedValuesList();
                //把当前条目的内容设置到文本域中。
                content.setText(String.join(",", selectedValuesList));
            }
        });

        //组装整体
        Box holeBox = Box.createVerticalBox();
        holeBox.add(topBox);
        holeBox.add(bottomPanel);

        jf.add(holeBox);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    //封装方法，往layoutPanel中添加单选按钮
    public void addBtn2LayoutPanel(String name, int layoutType) {
        //设置标题边框
        layoutPanel.setBorder(new TitledBorder(new EtchedBorder(), "确定选项布局"));
        //创建单选按钮
        JRadioButton button = new JRadioButton(name);
        layoutPanel.add(button);

        //让第一个按钮默认选中
        if (layoutGroup.getButtonCount() == 0) {
            button.setSelected(true);
        }

        layoutGroup.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookList.setLayoutOrientation(layoutType);
            }
        });
    }

    //封装方法，给selectModelPanel添加按钮
    public void addBtn2SelectModelPanel(String name, int selectionModel) {
        //设置标题边框
        selectModePanel.setBorder(new TitledBorder(new EtchedBorder(), "确定选择模式"));
        //创建单选按钮
        JRadioButton button = new JRadioButton(name);
        selectModePanel.add(button);

        //让第一个按钮默认选中
        if (selectModeGroup.getButtonCount() == 0) {
            button.setSelected(true);
        }

        selectModeGroup.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookList.setSelectionMode(selectionModel);
            }
        });
    }

    public static void main(String[] args) {
        new ListTest().init();
    }
}