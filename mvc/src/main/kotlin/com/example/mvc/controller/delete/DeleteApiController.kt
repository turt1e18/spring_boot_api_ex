package com.example.mvc.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.min

@RestController
@RequestMapping("/api")
@Validated //validated 설정 에노테이션 클래스 전체 선언 //Bean 이 아닌 값에 대한 검사
class DeleteApiController {

    @DeleteMapping(path = ["/delete-mapping"])
    fun deleteMapping(@RequestParam(value = "name") _name : String,
                      @NotNull(message = "age값이 누락되었습니다.") //null값이 되지 않아야한다.
                      @Min(value=20, message="20보다 커야 합니다.") //최소값 설정
                      @RequestParam(name = "age") _age : Int) : String {
        println(_age)
        println(_name)
        return "$_name $_age"
    }

    @DeleteMapping(path = ["/delete-mapping/name/{name}/age/{age}"])
    fun deleteMappingPath(
                        @NotNull(message = "길이는 최소 2-5자리가 되어야합니다")
                        @Size(min=2,max=5)
                        @PathVariable(value = "name") _name: String,
                        @NotNull(message = "age값이 누락되었습니다.") //null값이 되지 않아야한다.
                        @Min(value=20, message="20보다 커야 합니다.") //최소값 설정
                        @PathVariable(name = "age") _age: Int): String {
        println(_age)
        println(_name)
        return "$_name $_age"
    }
}

//@PathVariable 주소창에서 변수를 받아옴
//@RequestParam 파라미터 값을 받아옴