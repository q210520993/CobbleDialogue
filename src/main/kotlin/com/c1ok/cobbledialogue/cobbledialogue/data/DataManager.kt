package com.c1ok.cobbledialogue.cobbledialogue.data

import java.util.*

interface DataManager {
    fun getPlayerData(uuid: UUID): PlayerData?
}