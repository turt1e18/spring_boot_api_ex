package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) // 해당 class는 스네이크 형식의 변수 데이터를 받아옴
data class UserRequest(
        @field:Size(min = 2, max = 8)
        @field:NotEmpty
        var name:String?=null,

        @field:PositiveOrZero // 0 < n을 검증 0포함
        var age:Int?=null,

        @field:Email //양식 검증
        var email:String?=null,

        @field:NotBlank // 공백검증
        var address:String?=null,

        //@JsonProperty("phone_number") // kotlin은 카멜백방식 표기법이지만 json은 snake 표기법 임으로 phone_number로 오는 값 json 데이터를 변환시켜서 받음
        @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") //정규식 검증
        var phoneNumber:String?=null,

        @field:StringFormatDateTime(pattern ="yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.") // 커스텀 어노테이션
        var createAt:String? =null //yyyy-MM-dd hh:mm:ss ex) 2020-10-02 : 13:00:00
        ){
       /* @AssertTrue(message = "생성 일자의 패턴은 yyyy-MM-dd HH:mm:ss 이어야 합니다") //메소드 자체에는 field를 붙이지 않음, 원하는 기준이 없으면 AssertTrue를 사용
        private fun isValidCreateAt() { //현재 시간의 기준의 패턴을 이용하여 생성시간 검증
                 try {
                    LocalDateTime.parse(this.createAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        true
                }catch (e:Exception){
                        false
                }
        }
        */
}


