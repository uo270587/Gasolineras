package com.example.myapplication.data

import com.example.myapplication.state.AppStatus

sealed class ApiResult <out T> (val status: AppStatus, val data: T?, val message:String?) {

    data class Success<out R>(val _data: R): ApiResult<R>(
        status = AppStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String): ApiResult<Nothing>(
        status = AppStatus.ERROR,
        data = null,
        message = exception
    )
    data class Loaded(val messageLoading: String): ApiResult<Nothing>(
        status = AppStatus.LOADED,
        data = null,
        message = messageLoading
    )

}
