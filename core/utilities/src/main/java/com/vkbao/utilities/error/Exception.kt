package com.vkbao.utilities.error

import kotlin.Exception

class Exception(
    val code: String,
    override val message: String
): Exception(message = message)