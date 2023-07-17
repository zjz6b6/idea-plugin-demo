package com.github.zjz6b6.ideaplugindemo.demo

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

/**
 * @author Adam
 * @since 2023/7/17
 */
class MyTools {

    object SdkIcons {
        //默认图标文件存放在src/main/resources/icons/目录下
        val Sdk_default_icon: Icon = IconLoader.getIcon("./icons/myIcon.svg", SdkIcons::class.java)
    }

}