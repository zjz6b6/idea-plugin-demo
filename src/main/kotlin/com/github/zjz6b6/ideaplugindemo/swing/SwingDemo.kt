package com.github.zjz6b6.ideaplugindemo.swing

import com.openhtmltopdf.layout.BoxBuilder
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.event.ChangeListener
import kotlin.concurrent.thread


/**
 * 边界布局
 *
 * 其中边上的4个方向可以不设置，这样就可以实现列和行布局模式了 ？？
 * @doc https://blog.csdn.net/liudonglovehemin/article/details/130740949
 */
fun borderLayoutTest() {

    var jFrame = JFrame("演示边界布局管理器")

    jFrame.location = Point(100, 100)
    jFrame.size = Dimension(500, 300)

    //1. 创建组件
    val jButton = JButton("中间")
    val jButton2 = JButton("北间")
    val jButton3 = JButton("西间")
    val jButton4 = JButton("东间")
    val jButton5 = JButton("南间")

    //2. 添加各个组件
    jFrame.add(jButton, BorderLayout.CENTER)  //布局的中间
    jFrame.add(jButton2, BorderLayout.NORTH)  //布局的北边
    jFrame.add(jButton3, BorderLayout.WEST)   //布局的西边
    jFrame.add(jButton4, BorderLayout.EAST)   //布局的东边
    jFrame.add(jButton5, BorderLayout.SOUTH)  //布局的南边

    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}

/**
 * 流布局
 *
 * 一种流布局模式，控件会按添加顺序依次摆放，如果超过容器大小会自动换行
 */
fun flowLayoutTest() {

    val jFrame = JFrame("演示流布局管理器")

    jFrame.location = Point(300, 200)
    jFrame.size = Dimension(200, 200)


    //1. 创建组件
    val jButton1 = JButton("A")
    val jButton2 = JButton("B")
    val jButton3 = JButton("C")
    val jButton4 = JButton("D")
    val jButton5 = JButton("E")

    //2. 添加各个组件
    jFrame.add(jButton1)
    jFrame.add(jButton2)
    jFrame.add(jButton3)
    jFrame.add(jButton4)
    jFrame.add(jButton5)
    //设置流布局
    jFrame.layout = FlowLayout(FlowLayout.LEFT)


    //4. 设置窗体属性
    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE //设置窗体关闭的同时关闭jvm
    jFrame.isResizable = false  //Resizable:可调整大小的，设置窗体大小不可变

}

/**
 * 网格模式，适合比较复杂的布局方式
 */
fun gridLayoutTest() {
    val jFrame = JFrame("演示网格布局管理器")

    jFrame.location = Point(400, 400)
    jFrame.size = Dimension(200, 200)

    //定义组件
    var size = 9;
    //定义按钮数组
    for (i in 0 until size) {
        jFrame.add(JButton("${i + 1}"))
    }

    // 网格布局
    jFrame.layout = GridLayout(3, 3, 10, 30)

    //4. 设置窗体属性
    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE //设置窗体关闭的同时关闭jvm
    jFrame.isResizable = false  //Resizable:可调整大小的，设置窗体大小不可变
}

fun boxLayoutTest() {
    val jFrame = JFrame("演示BoxLayout管理器")

    // 水平排列
    var jPanel = JPanel()
    jPanel.layout = BoxLayout(jPanel, BoxLayout.X_AXIS)
    jFrame.add(jPanel)

    jPanel.add(JButton("Btn1"))
    jPanel.add(JButton("Btn2"))

    jFrame.pack()
    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}

fun jProgressTest() {
    val jFrame = JFrame("演示进度条")

    jFrame.layout = FlowLayout()

    val jProgressBar = JProgressBar(JProgressBar.HORIZONTAL, 0, 100)
    jProgressBar.isStringPainted = true
    jProgressBar.isBorderPainted = true

    // 组装视图
    var verticalBox = Box.createVerticalBox()

    jFrame.add(verticalBox)
    jFrame.add(jProgressBar)
    jFrame.pack()
    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    // 启动子线程 模拟耗时操作
    val simulaterActivity = SimulaterActivity(jProgressBar.maximum)
    Thread(simulaterActivity).start()
//    thread(start = true) {
//        SimulaterActivity(jProgressBar.maximum)
//    }

    //设置一个定时任务
    var timer = Timer(200, ActionListener {
        //读取线程任务对象的当前完成量，设置给进度条
        jProgressBar.value = simulaterActivity.current
    })
    timer.start()

    jProgressBar.addChangeListener(ChangeListener { if (jProgressBar.value == simulaterActivity.amount)  timer.stop() })

}

fun main() {
//    borderLayoutTest()
//    flowLayoutTest()
//    gridLayoutTest()
//    boxLayoutTest()

//    Swing进度条
//    主要使用JProgressBar、ProgressMonitor、BoundedRangeModel实现进度条。
//    jProgressTest() // 跑不起来  NoClassDefFoundError: kotlin/jvm/internal/Ref$ObjectRef

    // 如何才能调用无参构造函数呢？其实很简单，给每个参数添加一个默认值就可以了
    // 只要参数都有默认值，就会默认生成 无参构造函数
    // 重载函数 @JvmOverloads


}


class SimulaterActivity (var amount: Int = 0) : Runnable {

    @Volatile
    var current = 0

    override fun run() {
        while (current < amount) {
            Thread.sleep(50)
            current++
        }
    }

}


