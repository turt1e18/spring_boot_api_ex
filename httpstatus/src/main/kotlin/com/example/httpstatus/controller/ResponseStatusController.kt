package com.example.httpstatus.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/response")
class ResponseStatusController {

    @GetMapping("/200")
    @ResponseStatus(HttpStatus.OK)
    fun okResponse():String{
        return "요청을 처리되었습니다."
    }

    @RequestMapping(method = [RequestMethod.PUT,RequestMethod.POST], value = ["/201"])
    @ResponseStatus(HttpStatus.CREATED)
    fun createResponse():String{
        return "요청에 대한 등록이 완료되었습니다."
    }

    @GetMapping("/400")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun badRequestResponse():String{
        return "잘못된 요청입니다."
    }

    @GetMapping("/403")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun forbiddenResponse():String{
        return "잘못된 접근입니다."
    }

    @GetMapping("/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundResponse():String{
        return "존재하지 않는 페이지입니다."
    }

    @PostMapping("/500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun serverErrorResponse():String{
        return "서버 에러입니다. 수정중이니 잠시만 기다려주세요."
    }
}