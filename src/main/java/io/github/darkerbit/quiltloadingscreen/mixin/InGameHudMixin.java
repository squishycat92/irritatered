package io.github.darkerbit.quiltloadingscreen.mixin;

import io.github.darkerbit.quiltloadingscreen.QuiltLoadingScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    private QuiltLoadingScreen wtf;

    @Inject(
            method = "<init>(Lnet/minecraft/client/MinecraftClient;)V",
            at = @At("TAIL")
    )
    public void constructor(MinecraftClient client, CallbackInfo ci) {
        wtf = new QuiltLoadingScreen(client);
    }

    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;F)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;getArmorStack(I)Lnet/minecraft/item/ItemStack;")
    )
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        wtf.renderPatches(matrices, tickDelta, false);
    }
}
