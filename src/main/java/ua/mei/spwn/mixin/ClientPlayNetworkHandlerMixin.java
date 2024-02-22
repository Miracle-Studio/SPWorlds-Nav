package ua.mei.spwn.mixin;

import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @ModifyVariable(method = "sendChatMessage", at = @At("HEAD"), argsOnly = true)
    private String replaceContent(String content) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            long x = Math.round(player.getX());
            long y = Math.round(player.getY());
            long z = Math.round(player.getZ());

            if (player.clientWorld.getRegistryKey() == World.NETHER) {
                x *= 8;
                z *= 8;
            }

            content = content.replaceAll(":pos:", String.format(":pos:%s:%s:%s:", x, y, z));
        }

        return content;
    }

    @ModifyVariable(method = "sendChatCommand", at = @At("HEAD"), argsOnly = true)
    private String replaceCommand(String command) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            long x = Math.round(player.getX());
            long y = Math.round(player.getY());
            long z = Math.round(player.getZ());

            if (player.clientWorld.getRegistryKey() == World.NETHER) {
                x *= 8;
                z *= 8;
            }

            command = command.replaceAll(":pos:", String.format(":pos:%s:%s:%s:", x, y, z));
        }

        return command;
    }
}
