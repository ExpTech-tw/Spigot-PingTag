package Spigot_PingTag_whes1015;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class whes1015 extends JavaPlugin implements Listener {

    int vercode=100;
    String vername="V 1.0.0-beta";

    @Override
    public void onEnable() {

        URL url = null;
        try {
            url = new URL("http://exptech.mywire.org/Spigot_PingTag.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection http = null;
        try {
            assert url != null;
            http = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert http != null;
            http.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        try {
            InputStream input = http.getInputStream();
            byte[] data = new byte[1024];
            int idx = input.read(data);
            String str = new String(data, 0, idx);
            int x = Integer.parseInt(str);
            if(vercode < x) {
                this.getLogger().info("Please Update Your Plugin! "+vername);
                this.getLogger().info( "DownloadLink: https://github.com/ExpTech-tw/Spigot-PingTag/tags");
                this.getPluginLoader().disablePlugin(this);
            }else{
                this.getLogger().info("Spigot_PingTag Update Checking Success! "+vername);
                this.getLogger().info("Spigot_PingTag Loading Success! - Designed by ExpTech.tw (whes1015) "+vername);
                this.getServer().getPluginManager().registerEvents(this, this);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        http.disconnect();

    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        event.getPlayer().setDisplayName(event.getPlayer().getName()+" ("+event.getPlayer().getPing() + " ms)");
    }
}
