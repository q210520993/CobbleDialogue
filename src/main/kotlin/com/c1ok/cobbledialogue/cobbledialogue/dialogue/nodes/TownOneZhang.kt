package com.c1ok.cobbledialogue.cobbledialogue.dialogue.nodes

import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueData
import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager
import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager.addDialogueData
import com.c1ok.cobbledialogue.cobbledialogue.data.DialogueDataManager.addOriginDialogueData
import com.c1ok.cobbledialogue.cobbledialogue.data.PlayerDataManager
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.*
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.PhaseDialogueBuilder
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.Text
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.TextUnit
import com.c1ok.cobbledialogue.cobbledialogue.task.TaskManager
import com.c1ok.cobbledialogue.cobbledialogue.task.goals.DialogueEvent

object TownOneZhang {

    val town1_zhang_intro_1 by lazy {
        object : DialogueNode {
            override val id: String
                get() = "old_zhang_intro"
            val dialogueData = DialogueDataManager.getDialogue("town1_zhang_intro_1")
            override val text: Text<TextUnit>
                get() = PhaseDialogueBuilder().addText(
                    dialogueData
                ).build()
            override val options: List<DialogueOption>
                get() = listOf(
                    DialogueOption(
                        id = "option",
                        text = dialogueData.options[0],
                        action = { session ->
                            val result = DialogueActionResult.Advance("aunt_chang_encounter")
                            val player = session.dialoguer as? PlayerDialoguer ?: return@DialogueOption result
                            TaskManager.updateTask(player.player, DialogueEvent(session), "findZhang")
                            result
                        }
                    )
                )
            override val result: DialogueActionResult
                get() = DialogueActionResult.Exit
        }
    }

    val town1_chang_encounter_1 by lazy {
        object : DialogueNode {
            override val id: String get() = "aunt_chang_encounter"
            val dialogueData = DialogueDataManager.getDialogue("town1_chang_encounter_1")
            override val text: Text<TextUnit> get() = PhaseDialogueBuilder().addText(dialogueData).build()
            override val options: List<DialogueOption> get() = listOf(
                DialogueOption(
                    id = "option",
                    text = dialogueData.options[0],
                    action = { session ->
                        // 每一个Todo 都是任务插入的地方
                        // TODO 向 Aunt Chang 作进一步解释
                        DialogueActionResult.Advance("aunt_chang_final_conditions")
                    }
                )
            )
            override val result: DialogueActionResult get() = DialogueActionResult.Exit
        }
    }


    val town1_chang_encounter_2 by lazy {
        object : DialogueNode {
            override val id: String get() = "aunt_chang_final_conditions"
            val dialogueData = DialogueDataManager.getDialogue("town1_chang_encounter_2")
            override val text: Text<TextUnit> get() = PhaseDialogueBuilder().addText(dialogueData).build()
            override val options: List<DialogueOption> get() = listOf(
                DialogueOption(
                    id = "option",
                    text = dialogueData.options[0],
                    action = { session ->
                        // TODO 接受条件并完成对话
                        DialogueActionResult.Exit
                    }
                )
            )
            override val result: DialogueActionResult get() = DialogueActionResult.Exit
        }
    }

    val tree by lazy {
        SimpleDialogueTree(DialogueRootSelector {
            val data = PlayerDataManager.getPlayerData(it.id) ?: return@DialogueRootSelector ""
            val taskData = data.tasks["findZhang"] ?: return@DialogueRootSelector ""
            if (taskData.taskDoingData == null) return@DialogueRootSelector ""
            return@DialogueRootSelector "old_zhang_intro"
        }, listOf(town1_zhang_intro_1, town1_chang_encounter_1, town1_chang_encounter_2))
    }

    fun registerOrigin() {
        val town1_zhang_intro_1  = DialogueData(
            id = "town1_zhang_intro_1",
            dialogues = listOf(
                "§bPlayer: §aGrandpa! The village chief won’t agree! We tried everything, but he still kicked us out!",
                """§bOld Zhang: §3How many times has this been now? How many times this month? I’ve told you, 
        if you can’t convince the people in the village, there’s no way around it!""",
                """§bPlayer: §aWe thought we could convince the chief with the eloquence you taught us!""",
                """§bOld Zhang: §3Well… that eloquence can work in some cases, but the method has to be right. 
        You can’t just keep banging your head against a wall. You need to find a way around it!""",
                """§bOld Zhang: §3What you should do is go along with the chief’s point of view, 
        then use the skills I taught you to persuade the respected people in the village!""",
                "§bPlayer: §aSo, in other words, we need to persuade you first?",
                "§bOld Zhang: §3Ha! You’re using my own techniques against me, huh?",
                """§bPlayer: §aHehe, it’s nothing special. We just want to borrow the three starters 
        you’ve been raising with Aunt Chang...""",
                """§bOld Zhang: §3Oh? The three royal Pokémon that Aunt Chang and I have been raising all these years? 
        You think you can just take them away like that without anything in return?""",
                """§bPlayer: §aThey’re just sitting there, gathering dust. Why not let us take them out for some real-world training? 
        You’ve taught us so well, and they’ve been raised so well. Taking them out will only make you look good!""",
                """§bOld Zhang: §3Well, what you say does make sense, but you have to see if those three are even 
        willing to go with you. I don’t care what you do, just take whichever one agrees to go and bring me 
        back that championship trophy!"""
            ),
            options = listOf(
                "§aChoose a Pokémon."
            )
        )
        addDialogueData(town1_zhang_intro_1)
        addOriginDialogueData(town1_zhang_intro_1)

        val aunt_chang_encounter = DialogueData(
            id = "town1_chang_encounter_1",
            dialogues = listOf(
                """§bOld Zhang: §3Otherwise, Aunt Chang will just make you stay for dinner again. 
        You’re not even embarrassed, coming here every day to mooch off us!""",
                """§bAunt Chang: §3Hey, what’s wrong with a kid coming here for a meal when they’re hungry? 
        Don’t pass your stinginess on to them!""",
                """§bAunt Chang: §3Oh, what’s that you’re holding, little rascal? That’s… isn’t that a Charmander 
        from Old Zhang’s backyard?!""",
                """§bPlayer: §aAunt Chang, it’s... it’s...""",
                """§bOld Zhang: §3Haha, has Chang-yao come back? The kid just saw the Charmander and thought it was cute, 
        so they just held it for a bit. I’ll get it back right away.""",
                """§bAunt Chang: §3Explain? Look at that look on the kid’s face, just like you did when you were trying to trick me. 
        Little rascal, tell me the truth—has Old Zhang been getting you into trouble?"""
            ),
            options = listOf(
                "§aExplain the reason for borrowing Charmander."
            )
        )
        addDialogueData(aunt_chang_encounter)
        addOriginDialogueData(aunt_chang_encounter)

        val aunt_chang_final_conditions = DialogueData(
            id = "town1_chang_encounter_2",
            dialogues = listOf(
                """§bAunt Chang: §3You want to borrow it, huh? Fine! But my Pokémon rental company doesn’t make bad deals. 
        You can take the Charmander, but it won’t come free.""",
                """§bPlayer: §aReally?!""",
                """§bAunt Chang: §3Really. But it won’t be free. You owe me one. 
        You remember that chicken you ate? I’m missing a chicken now, and you’ll need to replace it.""",
                """§bPlayer: §aAh, Aunt Chang, that empty spot isn’t for just any chicken. That’s clearly a place for an eagle!""",
                """§bAunt Chang: §3Hey, hey, hey, so you figured it out, huh? You’ve learned well from Old Zhang. 
        Alright, here are a few eggs for you. Look at you, all small and scrawny, 
        if people see you, they’ll think you can’t even feed yourself. Don’t starve on the way, or you’ll embarrass me.""",
                """§bAunt Chang: §3Also, don’t think this is the end! No matter where you go, whether you make it big or get your butts kicked, 
        every New Year, you must come back to see me. Otherwise, I’ll track you down and break your legs!""",
                """§bOld Zhang: §3Did you hear that? Your Aunt Chang... she’s tough on the outside but soft on the inside. 
        These eggs? She’s been saving them to hatch some scallion ducks... they’re her precious ones. 
        Now, let’s go to the village head and find Uncle Tu. He’ll teach you how to train Pokémon. 
        Though, I suspect he’s just an old stubborn guy who won’t actually teach you much."""
            ),
            options = listOf(
                "§aAccept the terms and thank Aunt Chang."
            )
        )
        addDialogueData(aunt_chang_final_conditions)
        addOriginDialogueData(aunt_chang_final_conditions)

    }
}