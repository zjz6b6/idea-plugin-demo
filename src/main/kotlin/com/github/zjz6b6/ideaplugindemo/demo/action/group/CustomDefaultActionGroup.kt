package com.github.zjz6b6.ideaplugindemo.demo.action.group

import com.github.zjz6b6.ideaplugindemo.demo.SdkIcons
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DefaultActionGroup

/**
 * @author Adam
 * @since 2023/7/17
 */
class CustomDefaultActionGroup : DefaultActionGroup() {

    override fun update(e: AnActionEvent) {
        var editor = e.getData(CommonDataKeys.EDITOR)
        e.presentation.isEnabled = (editor != null)
//        e.presentation.icon = SdkIcons.Sdk_default_icon
        e.presentation.icon = SdkIcons.My_icon
//        e.presentation.icon = IconHelpr.MY_ICON
    }

}