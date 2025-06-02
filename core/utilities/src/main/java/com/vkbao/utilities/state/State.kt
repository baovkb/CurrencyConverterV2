package com.vkbao.utilities.state

import com.vkbao.utilities.error.ErrorEntity

sealed class State<out O> {
    data class Success<O>(val data: O) : State<O>()
    data class Error(val errorEntity: ErrorEntity) : State<Nothing>()
}