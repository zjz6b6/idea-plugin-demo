package com.github.zjz6b6.ideaplugindemo.demo

import com.github.zjz6b6.ideaplugindemo.ja.IconHelper
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

/**
 * @author Adam
 * @since 2023/7/17
 */
object SdkIcons {

    //默认图标文件存放在src/main/resources/icons/目录下
    @JvmField
    val Sdk_default_icon: Icon = IconLoader.getIcon("/icons/sdk_16.svg", SdkIcons::class.java)

    @JvmField
    val My_icon: Icon = IconLoader.getIcon("/icons/myIcon.svg", SdkIcons::class.java)

}

fun main(args: Array<String>) {
    var java = IconHelper::class.java
}