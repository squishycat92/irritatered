package io.github.darkerbit.quiltloadingscreen.mixin;

import io.github.darkerbit.quiltloadingscreen.QuiltLoadingScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.WorldGenerationProgressTracker;
import net.minecraft.client.gui.screen.LevelLoadingScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelLoadingScreen.class)
public abstract class LevelLoadingScreenMixin extends Screen {
    // to get compiler to shut up
    protected LevelLoadingScreenMixin(Text title) {
        super(title);
    }

    private QuiltLoadingScreen quiltLoadingScreen$loadingScreen;

    @Inject(
            method = "<init>(Lnet/minecraft/client/gui/WorldGenerationProgressTracker;)V",
            at = @At("TAIL")
    )
    public void constructor(WorldGenerationProgressTracker progressProvider, CallbackInfo ci) {
        quiltLoadingScreen$loadingScreen = new QuiltLoadingScreen(MinecraftClient.getInstance());
    }

    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;IIF)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I")
    )
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        quiltLoadingScreen$loadingScreen.renderPatches(matrices, delta, false);
    }
}
