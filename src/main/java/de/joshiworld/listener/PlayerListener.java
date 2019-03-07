package de.joshiworld.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.joshiworld.main.ArmZ;
import de.joshiworld.misc.Document;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.json.simple.JSONObject;

/**
 *
 * @author JoshiWorld
 */
public class PlayerListener implements Listener {
    
    private final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();
    
    //<editor-fold defaultstate="collapsed" desc="PlayerJoinEvent">
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        
        File file = new File("plugins/ArmZ/Players/" + p.getName() + ".json");
        
        //<editor-fold defaultstate="collapsed" desc="if file not exists">
        if(!file.exists()) {
            Document document = new Document();
            
            List<JSONObject> list = new ArrayList<>();
            JSONObject obj = new JSONObject();
            
            obj.put("Demark", (int) 0);
            obj.put("Aggro", "Normal");
            
            list.add(gson.fromJson(gson.toJson(obj), JSONObject.class));
            
            document.append("Player", list);
            document.save(file);
            
            Document spawn = Document.loadDocument(new File("plugins/ArmZ/spawn.json"));
            List spawnList = spawn.getList("Spawn");
            
            spawnList.forEach((json) -> {
                JSONObject object = (JSONObject) json;
                
                String world = (String) object.get("World");
                double x = (double) object.get("X");
                double y = (double) object.get("Y");
                double z = (double) object.get("Z");
                double yaw = (double) object.get("Yaw");
                double pitch = (double) object.get("Pitch");
                Location loc = new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);
                                    
                p.teleport(loc);
            });
        }
        //</editor-fold>
        
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        
        e.setJoinMessage("§7[§a+§7] " + p.getName());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PlayerQuitEvent">
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = (Player) e.getPlayer();
        
        if(ArmZ.combat.contains(p)) {
            p.sendMessage(ArmZ.getPrefix() + " §cCOMBATLOG");
            p.setHealth(0);
        }
        
        e.setQuitMessage("§7[§c+§7] " + p.getName());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AsyncPlayerChatEvent">
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = (Player) e.getPlayer();
        
        e.setCancelled(true);
        
        p.getNearbyEntities(40, 30, 40).forEach((nearbys) -> {
            nearbys.sendMessage(p.getName() + "§7: §f" + e.getMessage());
        });
    }
    //</editor-fold>
    
}
