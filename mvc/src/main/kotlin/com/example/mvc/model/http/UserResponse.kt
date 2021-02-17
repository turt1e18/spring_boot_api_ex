package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserResponse (
    var result : Result?=null,
    var descriptin:String?=null,
    @JsonProperty("user") //배열을 mutablelist로 변환후 json으로 user라는 데이터를 받아옴
    var userRequest: MutableList<UserRequest>?=null
)

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) //스네이크 케이스로 동작하고록 클래스 전체에 반환
data class Result(
        var resultCode:String?=null,
        var resultMessage:String?=null

)