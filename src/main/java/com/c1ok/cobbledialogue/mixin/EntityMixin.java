package com.c1ok.cobbledialogue.mixin;

import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueManager;
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.DialogueSession;
import com.c1ok.cobbledialogue.cobbledialogue.dialogue.text.ShiftDialogueText;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    //FIXME
    @Unique public boolean crouched = false;
    @Inject(method = "isCrouching", at = @At("RETURN"), cancellable = true)
    private void onSendChatMessageHead(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity)(Object)this;
        if(!cir.getReturnValue()){
            this.crouched = false;
            return;
        }
        if(this.crouched){
            return;
        }
        if(entity instanceof ServerPlayer player) {
            this.crouched = true;
            //TODO
            DialogueSession session = DialogueManager.INSTANCE.getActiveSessions().get(player.getUUID());
            if (session==null) {
                return;
            }
            if(session.getCurrentNode().getText() instanceof ShiftDialogueText text){
                text.setCrouched(true);
            }
        }
    }
}
