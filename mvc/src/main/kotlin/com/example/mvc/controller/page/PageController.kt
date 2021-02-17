package com.example.mvc.controller.page

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller //static 하위의 html 파일을 탐색
// @RestController 는 text로 내어버림
//RSET API 는 따로RestController 로 관리하고 PAGE의 경우에는 Controller 로 관리하는 것이 올바르다
class PageController {

    //http://localhost:8080/main
    @GetMapping("/main")
    fun main(): String {
        println("init main")
        return "main.html"
    }

    @ResponseBody // -> RestController 와 같이 플레인텍스트로 내보냄 Json 형태가 필요할 시 달아놓는 어노테이션
    @GetMapping("/test")
    fun response(): String {

        return "main.html"
    }
}