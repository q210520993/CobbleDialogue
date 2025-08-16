package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager.addDialogueData
import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager.addOriginDialogueData

/**
 * Dialogue格式
 * id 城镇名_npc名_对话场景_条数
 * 对话内容详细 [&e/&2/&7/&c/&5/&b]npc名称: &a话语[其中人名用&b 物名用&e 神奇宝宝名用&d]
 * 选项用&a表示友善选项 &c表示进攻选项 &f表示一般选项
 */
object OriginDialogueData {
    fun register() {
        // 创建并注册对话：town1_c_opening
        val town1_village_chief_opening = DialogueData(
            id = "town1_village_chief_opening_1",
            dialogues = listOf(
                "§bVillage Chief: §3Stop arguing! Quiet down for a moment, quiet down! My ear infection’s acting up again.",
                "§bPlayer: §aThen can you please agree to our request?",
                """§bVillage Chief: §3Agree to what? I’ve told you already, you’re the only ones left in this village.
        If you leave, what’s gonna happen to it? Sure, this village might be a bit behind the times,
        but you’ve got everything you need here—food, clothes, no worries. Isn’t living a peaceful life good enough?""",
                """§bVillage Chief: §3It’s all that old §eZhang’s §3fault, filling your heads with progressive ideas.
        He’s the one who talked to you about the Pokémon Championship, and now look, you’re all craving it.
        The §eFennekin §3even tried to squeeze into our chicken coop! You’re all just like that §eFennekin§3—always trying to get out!"""
            ),
            options = listOf(
                "§aArgue that you’ve outgrown the village.",  // 绿色选项，继续争论
                "§7Leave the argument quietly."              // 灰色选项，中立退出
            )
        )
        addDialogueData(town1_village_chief_opening)
        addOriginDialogueData(town1_village_chief_opening)
        // 创建并注册对话：player_request
        val player_request = DialogueData(
            id = "town1_village_chief_opening_2",
            dialogues = listOf(
                """§bPlayer: §aWe’ve seen everything in this village since we were kids. We’ve even counted every chicken feather in §eAunt Chang’s §achicken coop.""",
                """§bVillage Chief: §3Not just counted, you’ve plucked every feather! And you, of all people, ate the cleanest, even leaving no bones for §eAunt Chang.""",
                """§bPlayer: §aThat’s ancient history! Ancient history! I was just a kid back then!
        But now we’re thinking of going out to explore, maybe bring §eAunt Chang §csomething nice in return.
        Maybe we can even catch a §ePidgeot §cfor her!""",
                """§bVillage Chief: §3Pidgeot?! Look at you! You want to take a bunch of village kids out there and make a fool of us!""",
                """§bPlayer: §aWe won’t make a fool of anyone! Look at me—sure, I grew up without parents,
        but the village’s grandpas and grandmas raised us like their own.
        We’re not leaving just for us; we’re taking the vitality, the spirit of this village with us,
        so that the old folks can feel like they didn’t raise us for nothing.""",
                """§bVillage Chief: §3And what about the old folks here? What happens to them when you leave?
        They’re all in their seventies and eighties. What if you leave without a trace, and by the time you come back,
        the crooked tree by the graveyard will have been standing for twenty years? If you really want to leave,
        you’d better talk to everyone else and see if they agree. Otherwise, you’ll have to step over my old, fat §eWigglytuff §3to even bring up that gym badge issue.""",
                """§7With that, the Village Chief kicks us out of the village committee room."""
            ),
            options = listOf(
                "§bWait for the Village Chief to respond." // 浅青色选项
            )
        )
        addDialogueData(player_request)
        addOriginDialogueData(player_request)

        // 创建并注册对话：village_chief_response_1
        val village_chief_response_1 = DialogueData(
            id = "town1_village_chief_opening_3",
            dialogues = listOf(
                "§bVillage Chief: §3Agree to what? I’ve told you already, you’re the only ones left in this village. If you leave, what’s gonna happen to it? Sure, this village might be a bit behind the times, but you’ve got everything you need here—food, clothes, no worries. Isn’t living a peaceful life good enough?",
                "§bVillage Chief: §3It’s all that old §eZhang’s §3fault, filling your heads with these progressive ideas. He’s the one who talked to you about the Pokémon Championship, and now look, you’re all craving it. The §eFennekin §3even tried to squeeze into our chicken coop! You’re all just like that §eFennekin§3—always trying to get out!"
            ),
            options = listOf(
                "§aArgue that you’ve outgrown the village." // 绿色选项
            )
        )
        addDialogueData(village_chief_response_1)
        addOriginDialogueData(village_chief_response_1)


        // 创建并注册对话：player_rebuttal_1
        val player_rebuttal_1 = DialogueData(
            id = "town1_village_chief_opening_4",
            dialogues = listOf(
                "§bPlayer: §aWe’ve seen everything in this village since we were kids. We’ve even counted every chicken feather in §eAunt Chang’s §achicken coop."
            ),
            options = listOf(
                "§aEmphasize leaving for exploration." // 绿色提示积极态度
            )
        )
        addDialogueData(player_rebuttal_1)
        addOriginDialogueData(player_rebuttal_1)


        // 创建并注册对话：village_chief_counter_2
        val village_chief_counter_2 = DialogueData(
            id = "town1_village_chief_opening_5",
            dialogues = listOf(
                "§bVillage Chief: §3Not just counted, you’ve plucked every feather! And you, of all people, ate the cleanest, even leaving no bones for §eAunt Chang."
            ),
            options = listOf(
                "§cExpress frustration and move the argument forward." // 红色显示情绪高涨
            )
        )
        addDialogueData(village_chief_counter_2)
        addOriginDialogueData(village_chief_counter_2)
    }
}