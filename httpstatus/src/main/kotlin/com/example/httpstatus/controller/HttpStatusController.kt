package com.example.httpstatus.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/http")
class HttpStatusController {

    @GetMapping("/200")
    fun okClient():ResponseEntity<String>{
        return ResponseEntity.status(200).body("요청이 완료되었습니다")
    }

    @RequestMapping(method = [RequestMethod.PUT,RequestMethod.POST], value = ["/201"])
    fun createClient(): ResponseEntity<String>{
        return ResponseEntity.status(201).body("요청이 성공적이며 등록이 완료되었습니다.")
    }

    @GetMapping("/400/{age}")
    fun requestError(@PathVariable age : Int) : ResponseEntity<String>{
        return if(age < 20)
            ResponseEntity.status(200).body("$age 이 성공적으로 전달되었습니다")
        else
            ResponseEntity.status(400).body("요청값을 다시 확인해 주시길 바랍니다.")
    }

    @GetMapping("/403")
    fun notAllowedPage():ResponseEntity<String>{
        return ResponseEntity.status(403).body("해당 페이지에는 접근할 수 없습니다.")
    }

    @GetMapping("/404")
    fun notFoundPage():ResponseEntity<String>{
        return ResponseEntity.status(404).body("해당 페이지는 존재하지 않습니다.")
    }

    @PostMapping("500")
    fun serverError():ResponseEntity<String>{
        return ResponseEntity.status(500).body("서버에러가 발생했습니다. 죄송합니다.")
    }
}