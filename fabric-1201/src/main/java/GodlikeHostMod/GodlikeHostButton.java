package GodlikeHostMod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;


public class GodlikeHostButton implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenEvents.AFTER_INIT.register(((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof MultiplayerScreen) {
                ButtonWidget webButton = ButtonWidget.builder(
                        Text.literal("Godlike.host"),
                        button -> {
                            Util.getOperatingSystem().open("https://godlike.host");
                        }
                ).dimensions(scaledWidth - 105, 5,100, 20)
                        .build();

                Screens.getButtons(screen).add(webButton);
            }
        }));
    }
}
