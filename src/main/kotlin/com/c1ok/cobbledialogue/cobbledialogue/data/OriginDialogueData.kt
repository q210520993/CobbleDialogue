package com.c1ok.cobbledialogue.cobbledialogue.data

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager.addDialogueData
import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager.addOriginDialogueData

object OriginDialogueData {
    fun register() {
        // 创建并注册对话：town1_mayor_start_argue
        val town1_mayor_start_argue = DialogueData(
            id = "town1_mayor_start_argue",
            dialogues = listOf(
                "§bMayor: §6I don't care what you think, I'm the mayor here and I make the rules!",
                "§bYou: §aBut Mayor, you can't just ignore the law!",
                "§bMayor: §6The law? Ha! I make the law!",
                "§bYou: §aThis is ridiculous, we need to talk about this!"
            ),
            options = listOf(
                "§cArgue with the Mayor",  // 红色选项，情绪激烈
                "§7Leave the argument"    // 灰色选项，中立退出
            )
        )
        addDialogueData(town1_mayor_start_argue)
        addOriginDialogueData(town1_mayor_start_argue)

        // 创建并注册对话：town1_village_chief_opening
        val town1_village_chief_opening = DialogueData(
            id = "town1_village_chief_opening",
            dialogues = listOf(
                "§bVillage Chief: §3Stop arguing! Quiet down for a moment, quiet down! My ear infection’s acting up again."
            ),
            options = listOf(
                "§aAsk the Chief to listen to your request." // 绿色选项
            )
        )
        addDialogueData(town1_village_chief_opening)
        addOriginDialogueData(town1_village_chief_opening)

        // 创建并注册对话：player_request
        val player_request = DialogueData(
            id = "player_request",
            dialogues = listOf(
                "§bPlayer: §aThen can you please agree to our request?"
            ),
            options = listOf(
                "§bWait for the Village Chief to respond." // 浅青色选项
            )
        )
        addDialogueData(player_request)
        addOriginDialogueData(player_request)

        // 创建并注册对话：village_chief_response_1
        val village_chief_response_1 = DialogueData(
            id = "village_chief_response_1",
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
            id = "player_rebuttal_1",
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
            id = "village_chief_counter_2",
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