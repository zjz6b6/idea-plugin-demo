package com.github.zjz6b6.ideaplugindemo.demo.action

import com.github.zjz6b6.ideaplugindemo.MyBundle
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages

/**
 * 直接将Action添加到 toolWindow
 *
 * @author Adam
 * @since 2023/7/17
 */
class PopupDialogAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {

        var messageBuilder = StringBuilder(e.presentation.text + " Selected")
        var nav = e.getData(CommonDataKeys.NAVIGATABLE)
        if (nav != null) {
            messageBuilder.append("\nSelected Element: $nav")
        }
        messageBuilder.append("\nDesc: ${e.presentation.description}")
        Messages.showMessageDialog(e.project, messageBuilder.toString(), MyBundle.message("MyPopupDialogTitle"), Messages.getInformationIcon())

    }
}