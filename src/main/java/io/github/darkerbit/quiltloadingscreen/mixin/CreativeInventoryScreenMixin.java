package io.github.darkerbit.quiltloadingscreen.mixin;

import io.github.darkerbit.quiltloadingscreen.QuiltLoadingScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
    public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    private QuiltLoadingScreen quiltLoadingScreen$loadingScreen;

    @Inject(
            method = "<init>(Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At("TAIL")
    )
    public void constructor(PlayerEntity player, CallbackInfo ci) {
        quiltLoadingScreen$loadingScreen = new QuiltLoadingScreen(MinecraftClient.getInstance());
    }

    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/AbstractInventoryScreen;render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V")
    )
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        quiltLoadingScreen$loadingScreen.renderPatches(matrices, delta, false);
    }
}
