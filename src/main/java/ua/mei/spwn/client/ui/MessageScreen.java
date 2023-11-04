package ua.mei.spwn.client.ui;

import io.wispforest.owo.ui.base.*;
import io.wispforest.owo.ui.component.*;
import io.wispforest.owo.ui.container.*;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import org.jetbrains.annotations.*;

public class MessageScreen extends BaseOwoScreen<FlowLayout> {
    public MutableText title;
    public MutableText message;

    public MessageScreen(MutableText title, MutableText message) {
        this.title = title;
        this.message = message;
    }

    public static void openMessage(MutableText title, MutableText message) {
        MinecraftClient.getInstance().setScreen(new MessageScreen(title, message));
    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.child(
                        Containers.verticalFlow(Sizing.fill(60), Sizing.content())
                                .child(
                                        Components.label(this.title)
                                                .shadow(true)
                                )
                                .child(
                                        Components.label(this.message)
                                                .maxWidth(250)
                                                .horizontalTextAlignment(HorizontalAlignment.CENTER)
                                                .color(Color.ofRgb(Colors.GRAY))
                                                .margins(Insets.top(5))
                                )
                                .child(
                                        Components.button(Text.translatable("gui.spwp.button.ok"), button -> {
                                            MinecraftClient.getInstance().setScreen(null);
                                        }).margins(Insets.top(5)).horizontalSizing(Sizing.fill(100))
                                )
                                .padding(Insets.of(15))
                                .horizontalAlignment(HorizontalAlignment.CENTER)
                                .verticalAlignment(VerticalAlignment.CENTER)
                )
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER)
                .surface(Surface.VANILLA_TRANSLUCENT);
    }
}
