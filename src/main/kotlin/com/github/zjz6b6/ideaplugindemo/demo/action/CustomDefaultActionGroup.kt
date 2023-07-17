package com.github.zjz6b6.ideaplugindemo.demo.action

import com.github.zjz6b6.ideaplugindemo.demo.MyTools
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
        e.presentation.icon = MyTools.SdkIcons.Sdk_default_icon

    }

}