package com.example.mvc.controller.post

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping") //http:localhost:8080:/api/post-mapping Postmapping 방법
    fun postMapping(): String {
        return "post-mapping"
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"]) //GET과 POST사이에 URI가 곂쳐도 호환이됨 다만, 같은방식의 통신방식에 같은URI 이면 오류가 남
    fun requestMapping(): String {
        return "request-mapping"
    }

    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest { // 데이터가 들어오면 json -> object 나갈때는 object -> json
        println(userRequest)
        return userRequest
    }


}