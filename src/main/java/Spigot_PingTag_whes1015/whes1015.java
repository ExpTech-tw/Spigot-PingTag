package Spigot_PingTag_whes1015;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class whes1015 extends JavaPlugin implements Listener {

    String vername="1.0.0-stable";

    @Override
    public void onEnable() {
        String webPage = "https://api.github.com/repos/ExpTechTW/Spigot-PingTag/releases";

        InputStream is = null;
        try {
            is = new URL(webPage).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        JsonElement jsonElement = new JsonParser().parse(reader);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        JsonObject jsonObject = (JsonObject) jsonArray.get(0);
        saveDefaultConfig();
        if (jsonObject.get("tag_name").toString() != vername) {
            if ((getConfig().getString("BetaVersion") == "true" && jsonObject.get("prerelease").getAsBoolean() == true) || (getConfig().getString("BetaVersion") == "false" && jsonObject.get("prerelease").getAsBoolean() == false)) {
                this.getLogger().info("Please Update Your Plugin! "+vername);
                this.getLogger().info( "DownloadLink: https://github.com/ExpTech-tw/Spigot-PingTag/releases");
                this.getPluginLoader().disablePlugin(this);
            } else {
                this.getLogger().info("Spigot_PingTag Update Checking Success! "+vername);
                this.getLogger().info("Spigot_PingTag Loading Success! - Designed by ExpTech.tw (whes1015) "+vername);
                this.getServer().getPluginManager().registerEvents(this, this);
            }
        } else {
            this.getLogger().info("Spigot_PingTag Update Checking Success! "+vername);
            this.getLogger().info("Spigot_PingTag Loading Success! - Designed by ExpTech.tw (whes1015) "+vername);
            this.getServer().getPluginManager().registerEvents(this, this);
        }
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        event.getPlayer().setDisplayName(event.getPlayer().getName()+" ("+event.getPlayer().getPing() + " ms)");
    }
}
