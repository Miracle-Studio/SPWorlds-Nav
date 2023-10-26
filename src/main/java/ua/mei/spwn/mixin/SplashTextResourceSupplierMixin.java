package ua.mei.spwn.mixin;

import net.minecraft.client.resource.*;
import net.minecraft.resource.*;
import net.minecraft.util.profiler.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Inject(method="apply(Ljava/util/List;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    protected void apply(List<String> list, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        for (int i = 0; i < 2; i++) {
            list.add("Если думаешь что ты занимаешся чем-то бесполезным, вспомни что жаба инвестировал в наши моды 1730 рублей");
        }
    }
}
