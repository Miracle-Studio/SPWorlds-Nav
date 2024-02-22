package ua.mei.spwn.mixin;

import net.kyori.adventure.text.serializer.gson.*;
import net.minecraft.client.network.message.*;
import net.minecraft.text.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import ua.mei.spwn.client.*;

import java.util.regex.*;

@Mixin(MessageHandler.class)
public class MessageHandlerMixin {
    @ModifyVariable(method = "onGameMessage", at = @At("HEAD"), argsOnly = true)
    private Text replaceMessage(Text message) {
        String content = message.getString();
        Pattern pattern = Pattern.compile(":pos:(-?\\d+):(-?\\d+):(-?\\d+):");
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            message = Text.Serialization.fromJson(GsonComponentSerializer.gson().serialize(
                    message.asComponent().replaceText(builder ->
                            builder.match(pattern).replacement(SPMath.posText(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))))
                    )
            ));
        }

        return message;
    }
}
