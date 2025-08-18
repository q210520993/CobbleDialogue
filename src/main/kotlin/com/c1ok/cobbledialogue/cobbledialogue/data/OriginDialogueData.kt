package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.nodes.TownOneArgue
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.nodes.TownOneZhang

/**
 * Dialogue格式
 * id 城镇名_npc名_对话场景_条数
 * 对话内容详细 [&e/&2/&7/&c/&5/&b]npc名称: &a话语[其中人名用&b 物名用&e 神奇宝宝名用&d]
 * 选项用&a表示友善选项 &c表示进攻选项 &f表示一般选项
 */
object OriginDialogueData {
    fun regsiter() {
        TownOneZhang.registerOrigin()
        TownOneArgue.registerOrigin()
    }
}