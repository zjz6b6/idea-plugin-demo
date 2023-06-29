package com.github.zjz6b6.ideaplugindemo.error

import com.intellij.openapi.diagnostic.ErrorReportSubmitter

/**
 * @author Adam
 * @since 2023/6/29
 */
class MyErrorHandler : ErrorReportSubmitter() {

    override fun getReportActionText(): String {
        return "Report to Author";
    }
}