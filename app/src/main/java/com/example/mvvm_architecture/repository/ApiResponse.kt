package com.example.mvvm_architecture.repository

sealed class ApiResponse<T>(val data:T?=null, val errorMessage: String?=null){
    class Loading<T>:ApiResponse<T>()
    class Sucess<T>(data: T?=null):ApiResponse<T>(data=data)
    class Error<T>(errorMessage: String):ApiResponse<T>(errorMessage = errorMessage)
}