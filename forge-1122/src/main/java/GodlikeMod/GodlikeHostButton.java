package GodlikeMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.Sys;

@Mod(
        modid = GodlikeHostButton.MODID,
        name = GodlikeHostButton.NAME,
        version = GodlikeHostButton.VERSION,
        clientSideOnly = true
)
public class GodlikeHostButton {
    public static final String MODID = "godlikemod";
    public static final String NAME = "Godlike Mod";
    public static final String VERSION = "1.0.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof GuiMultiplayer) {
            int x = event.getGui().width - 105;
            int y = 5;

            event.getButtonList().add(new GuiButton(99123, x, y, 100, 20, "Godlike.host") {
                @Override
                public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                    boolean isClicked = super.mousePressed(mc, mouseX, mouseY);
                    if (isClicked) {
                        Sys.openURL("https://godlike.host");
                    }
                    return isClicked;
                }
            });
        }
    }
}