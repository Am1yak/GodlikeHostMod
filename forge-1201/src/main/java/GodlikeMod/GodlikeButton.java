package GodlikeMod;

import net.minecraft.Util;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("godlikemod")
public class GodlikeButton {
    public GodlikeButton() {}

    @Mod.EventBusSubscriber(modid = "godlikemod", value = Dist.CLIENT)
    private static class ClientEvents {
        @SubscribeEvent
        public static void onScreenInit(ScreenEvent.Init.Post event) {
            if (event.getScreen() instanceof JoinMultiplayerScreen screen) {
                Button webButton = Button.builder(
                    Component.literal("Godlike.host"),
                    button -> {
                        Util.getPlatform().openUri("https://godlike.host");
                    }
                ).bounds(screen.width - 105, 5, 100, 20)
                        .build();

                event.addListener(webButton);
            }
        }
    }
}
