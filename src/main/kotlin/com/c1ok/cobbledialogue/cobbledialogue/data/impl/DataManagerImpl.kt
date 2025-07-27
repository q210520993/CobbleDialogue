package com.c1ok.cobbledialogue.cobbledialogue.data.impl

import com.c1ok.cobbledialogue.cobbledialogue.data.DataManager
import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerData
import java.util.*
import java.util.concurrent.ConcurrentHashMap

object DataManagerImpl: DataManager {
    val datas = ConcurrentHashMap<UUID, PlayerData>()

    override fun getPlayerData(uuid: UUID): PlayerData? {
        if (datas[uuid] == null) {
            return datas.put(uuid, PlayerData(uuid, null))
        }
        return datas[uuid]
    }

}