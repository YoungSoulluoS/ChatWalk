package net.chatwalk.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.chatwalk.LiterallyJustOneBoolean.LieAboutMovingForward;

@Mixin(value = Minecraft.class, priority = 999)
public abstract class StartLying {
	@Shadow protected abstract void openChatScreen(String string);

	@Redirect(method = "handleKeybinds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;openChatScreen(Ljava/lang/String;)V"))
	void moveChatBoi(Minecraft instance, String string) {
		// if the player is holding W
		if (Minecraft.getInstance().options.keyUp.isDown()) {
			// lie and tell the server that we are still moving forward despite having chat open
			LieAboutMovingForward = true;
		}

		// open chat screen because we redirected
		openChatScreen(string);
	}
}
