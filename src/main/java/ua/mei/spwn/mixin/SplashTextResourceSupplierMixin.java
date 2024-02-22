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
    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    protected void apply(List<String> list, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        for (int i = 0; i < 40; i++) {
            list.add("Скажем ПАТОМ переменам на СП! - Jabochca_Soviet");
        }
        for (int i = 0; i < 8; i++) {
            list.add("СП Секс! - _snowier");
        }
        for (int i = 0; i < 4; i++) {
            list.add("§6Попробуйте также мод RPRenames! - HiWord9");
        }
        for (int i = 0; i < 4; i++) {
            list.add("https://youtu.be/dQw4w9WgXcQ - Moksy44");
        }
        for (int i = 0; i < 2; i++) {
            list.add("продам гараж пишите в тг - To4no_Ne_avaewww");
        }
    }
}
