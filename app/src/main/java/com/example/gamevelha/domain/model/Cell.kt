package com.example.gamevelha.domain.model

import com.example.gamevelha.util.StringUtil

class Cell(var player: Player?) {

    val isEmpty: Boolean
        get() = player == null || StringUtil.isNullOrEmpty(player!!.value)
}