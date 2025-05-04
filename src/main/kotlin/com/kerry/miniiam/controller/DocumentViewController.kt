package com.kerry.miniiam.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DocumentViewController {

    @GetMapping("/my-documents")
    fun myDocumentsPage(): String = "my-documents" // templates/my-documents.html
}
