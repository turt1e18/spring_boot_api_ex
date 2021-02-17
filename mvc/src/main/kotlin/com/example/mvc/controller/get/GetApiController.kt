package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping(path = ["/hello" , "/abc"]) //GET mapping -> http:localhost:8080/api/hello, http:localhost:8080/api/abc
    fun hello() : String {
        return "hello kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET] , path = ["/request-mapping"]) // 위 get과 같은 방법
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}") // GET mapping -> http:localhost:8080/api/get-mapping/path-variable/{name}/{age}
    fun pathVariable(@PathVariable name: String, @PathVariable age : Int) : String {  //name에는 get방식으로 받는 String 형 입력값 대입 age에는 받은 int값 대입
        println("$name , $age")
        return "$name $age"
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}") // GET mapping -> http:localhost:8080/api/get-mapping/path-variable/{name}/{age}
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age : Int) : String {
        println("$_name- , $age")
        return "$_name $age"
    }

    @GetMapping("/get-mapping/query-param") // QueryParam -> http:localhost:8080/api/get-mapping/query-param?name=value&age=value
    fun queryParam(@RequestParam(name = "name") name: String, @RequestParam(value = "age") age: Int): String {
        println("$name,$age")
        return "$name $age"
    }

    @GetMapping("/get-mapping/query-param/object") // QueryParam Object -> http:localhost:8080/api/get-mapping/query-param/object?name=value&age=value...
    fun queryParamObject(userRequest: UserRequest):UserRequest{ // 만약 쿼리파라미터가 주소에 -(하이픈)이 있는경우 사용 불가능
        println(userRequest) //Object return 시 JSON 타입의 데이터가 return 됨
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map") // QueryParam Object -> http:localhost:8080/api/get-mapping/query-param/object?name=value&age=value...
    fun queryParamMap(@RequestParam map: Map<String,Any>) : Map<String, Any> { // 만약 쿼리파라미터가 주소에 -(하이픈)이 있는경우 map으로 사용
        println(map)
        val phoneNumber = map["phone-number"] //phonenumber만 받아오기
        return map
    }
}