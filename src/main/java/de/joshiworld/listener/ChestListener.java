package de.joshiworld.listener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.joshiworld.main.ArmZ;
import de.joshiworld.methods.Metadata;
import de.joshiworld.misc.Document;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.json.simple.JSONObject;

/**
 *
 * @author JoshiWorld
 */
public class ChestListener implements Listener {
    
    private final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = (Player) e.getPlayer();
        
        if(e.getBlockPlaced().getType().equals(Material.EMERALD_BLOCK)) {
            //<editor-fold defaultstate="collapsed" desc="medical">
            if(ArmZ.medicalChests.contains(p)) {
                Document document = Document.loadDocument(new File("plugins/ArmZ/Chests/medical.json"));
                List<JSONObject> list = new ArrayList<>();
                JSONObject obj = new JSONObject();
                
                int i = 1; 
            
                while(document.getList(String.valueOf(i)) != null) {
                    i++;
                }
                
                obj.put("World", e.getBlockPlaced().getWorld().getName());
                obj.put("X", String.valueOf(e.getBlockPlaced().getX()));
                obj.put("Y", String.valueOf(e.getBlockPlaced().getY()));
                obj.put("Z", String.valueOf(e.getBlockPlaced().getZ()));
                
                list.add(gson.fromJson(gson.toJson(obj), JSONObject.class));
                document.append(String.valueOf(i), list);
                document.save(new File("plugins/ArmZ/Chests/medical.json"));
            
                Metadata.setMetadata("medical", e.getBlockPlaced(), e.getBlockPlaced().getLocation());
                
                p.sendMessage(ArmZ.getPrefix() + " §3Medical-Chest §a#" + i + " §3gesetzt");
            }
            //</editor-fold>
            
            //<editor-fold defaultstate="collapsed" desc="military">
            if(ArmZ.militaryChests.contains(p)) {
                Document document = Document.loadDocument(new File("plugins/ArmZ/Chests/military.json"));
                List<JSONObject> list = new ArrayList<>();
                JSONObject obj = new JSONObject();
                
                int i = 1; 
            
                while(document.getList(String.valueOf(i)) != null) {
                    i++;
                }
                
                obj.put("World", e.getBlockPlaced().getWorld().getName());
                obj.put("X", String.valueOf(e.getBlockPlaced().getX()));
                obj.put("Y", String.valueOf(e.getBlockPlaced().getY()));
                obj.put("Z", String.valueOf(e.getBlockPlaced().getZ()));
                
                list.add(gson.fromJson(gson.toJson(obj), JSONObject.class));
                document.append(String.valueOf(i), list);
                document.save(new File("plugins/ArmZ/Chests/military.json"));
            
                Metadata.setMetadata("military", e.getBlockPlaced(), e.getBlockPlaced().getLocation());
                
                p.sendMessage(ArmZ.getPrefix() + " §3Military-Chest §a#" + i + " §3gesetzt");
            }
            //</editor-fold>
            
            //<editor-fold defaultstate="collapsed" desc="police">
            if(ArmZ.policeChests.contains(p)) {
                Document document = Document.loadDocument(new File("plugins/ArmZ/Chests/police.json"));
                List<JSONObject> list = new ArrayList<>();
                JSONObject obj = new JSONObject();
                
                int i = 1; 
            
                while(document.getList(String.valueOf(i)) != null) {
                    i++;
                }
                
                obj.put("World", e.getBlockPlaced().getWorld().getName());
                obj.put("X", String.valueOf(e.getBlockPlaced().getX()));
                obj.put("Y", String.valueOf(e.getBlockPlaced().getY()));
                obj.put("Z", String.valueOf(e.getBlockPlaced().getZ()));
                
                list.add(gson.fromJson(gson.toJson(obj), JSONObject.class));
                document.append(String.valueOf(i), list);
                document.save(new File("plugins/ArmZ/Chests/police.json"));
            
                Metadata.setMetadata("police", e.getBlockPlaced(), e.getBlockPlaced().getLocation());
                
                p.sendMessage(ArmZ.getPrefix() + " §3Police-Chest §a#" + i + " §3gesetzt");
            }
            //</editor-fold>
            
            //<editor-fold defaultstate="collapsed" desc="normaly">
            if(ArmZ.normalyChests.contains(p)) {
                Document document = Document.loadDocument(new File("plugins/ArmZ/Chests/normaly.json"));
                List<JSONObject> list = new ArrayList<>();
                JSONObject obj = new JSONObject();
                
                int i = 1; 
            
                while(document.getList(String.valueOf(i)) != null) {
                    i++;
                }
                
                obj.put("World", e.getBlockPlaced().getWorld().getName());
                obj.put("X", String.valueOf(e.getBlockPlaced().getX()));
                obj.put("Y", String.valueOf(e.getBlockPlaced().getY()));
                obj.put("Z", String.valueOf(e.getBlockPlaced().getZ()));
                
                list.add(gson.fromJson(gson.toJson(obj), JSONObject.class));
                document.append(String.valueOf(i), list);
                document.save(new File("plugins/ArmZ/Chests/normaly.json"));
            
                Metadata.setMetadata("normaly", e.getBlockPlaced(), e.getBlockPlaced().getLocation());
                
                p.sendMessage(ArmZ.getPrefix() + " §3Normaly-Chest §a#" + i + " §3gesetzt");
            }
            //</editor-fold>
        }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = (Player) e.getPlayer();
        
        if(e.getBlock().getType().equals(Material.EMERALD_BLOCK)) {
            //<editor-fold defaultstate="collapsed" desc="medical">
            if(ArmZ.medicalChests.contains(p)) {
                Document document = Document.loadDocument(new File("plugins/ArmZ/Chests/medical.json"));
                
                int i = 1;
                
                while(document.getList(String.valueOf(i)) != null) {
                    List list = document.getList(String.valueOf(i));
                    int i2 = i;
                    
                    list.forEach((json) -> {
                        JSONObject obj = (JSONObject) json;
                        
                        int x = Integer.valueOf(obj.get("X").toString());
                        int y = Integer.valueOf(obj.get("Y").toString());
                        int z = Integer.valueOf(obj.get("Z").toString());
                        
                        if(x == e.getBlock().getX() && y == e.getBlock().getY() && z == e.getBlock().getZ()) {
                            document.removeKey(String.valueOf(i2));
                        }
                    });
                    
                    i++;
                }
                
                Metadata.removeMetadata("medical", e.getBlock());
                
                document.save(new File("plugins/ArmZ/Chests/medical.json"));
                
                p.sendMessage(ArmZ.getPrefix() + " §3Medical-Chest removed");
            }
            //</editor-fold>
            
            //<editor-fold defaultstate="collapsed" desc="military">
            if(ArmZ.militaryChests.contains(p)) {
                Document document = Document.loadDocument(new File("plugins/ArmZ/Chests/military.json"));
                
                int i = 1;
                
                while(document.getList(String.valueOf(i)) != null) {
                    List list = document.getList(String.valueOf(i));
                    int i2 = i;
                    
                    list.forEach((json) -> {
                        JSONObject obj = (JSONObject) json;
                        
                        int x = Integer.valueOf(obj.get("X").toString());
                        int y = Integer.valueOf(obj.get("Y").toString());
                        int z = Integer.valueOf(obj.get("Z").toString());
                        
                        if(x == e.getBlock().getX() && y == e.getBlock().getY() && z == e.getBlock().getZ()) {
                            document.removeKey(String.valueOf(i2));
                        }
                    });
                    
                    i++;
                }
                
                Metadata.removeMetadata("military", e.getBlock());
                
                document.save(new File("plugins/ArmZ/Chests/military.json"));
                
                p.sendMessage(ArmZ.getPrefix() + " §3Military-Chest removed");
            }
            //</editor-fold>
            
            //<editor-fold defaultstate="collapsed" desc="police">
            if(ArmZ.policeChests.contains(p)) {
                Document document = Document.loadDocument(new File("plugins/ArmZ/Chests/police.json"));
                
                int i = 1;
                
                while(document.getList(String.valueOf(i)) != null) {
                    List list = document.getList(String.valueOf(i));
                    int i2 = i;
                    
                    list.forEach((json) -> {
                        JSONObject obj = (JSONObject) json;
                        
                        int x = Integer.valueOf(obj.get("X").toString());
                        int y = Integer.valueOf(obj.get("Y").toString());
                        int z = Integer.valueOf(obj.get("Z").toString());
                        
                        if(x == e.getBlock().getX() && y == e.getBlock().getY() && z == e.getBlock().getZ()) {
                            document.removeKey(String.valueOf(i2));
                        }
                    });
                    
                    i++;
                }
                
                Metadata.removeMetadata("police", e.getBlock());
                
                document.save(new File("plugins/ArmZ/Chests/police.json"));
                
                p.sendMessage(ArmZ.getPrefix() + " §3Police-Chest removed");
            }
            //</editor-fold>
            
            //<editor-fold defaultstate="collapsed" desc="normaly">
            if(ArmZ.normalyChests.contains(p)) {
                Document document = Document.loadDocument(new File("plugins/ArmZ/Chests/normaly.json"));
                
                int i = 1;
                
                while(document.getList(String.valueOf(i)) != null) {
                    List list = document.getList(String.valueOf(i));
                    int i2 = i;
                    
                    list.forEach((json) -> {
                        JSONObject obj = (JSONObject) json;
                        
                        int x = Integer.valueOf(obj.get("X").toString());
                        int y = Integer.valueOf(obj.get("Y").toString());
                        int z = Integer.valueOf(obj.get("Z").toString());
                        
                        if(x == e.getBlock().getX() && y == e.getBlock().getY() && z == e.getBlock().getZ()) {
                            document.removeKey(String.valueOf(i2));
                        }
                    });
                    
                    i++;
                }
                
                Metadata.removeMetadata("normaly", e.getBlock());
                
                document.save(new File("plugins/ArmZ/Chests/normaly.json"));
                
                p.sendMessage(ArmZ.getPrefix() + " §3Normaly-Chest removed");
            }
            //</editor-fold>
        }
    }
    
    @EventHandler
    public void onCheckChest(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        
        if(e.getClickedBlock().getType().equals(Material.EMERALD_BLOCK)) {
            if(ArmZ.checkChests.contains(p)) {
                if(e.getClickedBlock().hasMetadata("medical")) {
                    p.sendMessage(ArmZ.getPrefix() + " §3Medical");
                } else if(e.getClickedBlock().hasMetadata("military")) {
                    p.sendMessage(ArmZ.getPrefix() + " §3Military");
                } else if(e.getClickedBlock().hasMetadata("police")) {
                    p.sendMessage(ArmZ.getPrefix() + " §3Police");
                } else if(e.getClickedBlock().hasMetadata("normaly")) {
                    p.sendMessage(ArmZ.getPrefix() + " §3Normaly");
                } else {
                    p.sendMessage(ArmZ.getPrefix() + " §3No metadata found");
                }
            }
        }
    }
    
}
