package de.joshiworld.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.joshiworld.main.ArmZ;
import de.joshiworld.misc.Document;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 *
 * @author JoshiWorld
 */
public class Spawn_Command implements CommandExecutor {
    
    private final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            
            if(cmd.getName().equalsIgnoreCase("spawn")) {
                if(PermissionsEx.getUser(p).getGroups()[0].has("ArmZ.spawn")) {
                    if(args.length == 0) {
                        p.sendMessage("/spawn");
                        p.sendMessage("/spawn set");
                        p.sendMessage("/spawn delete");
                        p.sendMessage("/spawn tp");
                        return true;
                    }
                    
                    if(args.length == 1) {
                        //<editor-fold defaultstate="collapsed" desc="set">
                        if(args[0].equalsIgnoreCase("set")) {
                            if(!(new File("plugins/ArmZ/spawn.json")).exists()) {
                                Document document = new Document();
                                List<JSONObject> list = new ArrayList<>();
                            
                                JSONObject obj = new JSONObject();
                            
                                obj.put("World", p.getLocation().getWorld().getName());
                                obj.put("X", p.getLocation().getX());
                                obj.put("Y", p.getLocation().getY());
                                obj.put("Z", p.getLocation().getZ());
                                obj.put("Yaw", p.getLocation().getYaw());
                                obj.put("Pitch", p.getLocation().getPitch());
                            
                                list.add(gson.fromJson(gson.toJson(obj), JSONObject.class));
                            
                                document.append("Spawn", list);
                                document.save(new File("plugins/ArmZ/spawn.json"));
                            
                                p.sendMessage(ArmZ.getPrefix() + " §3Du hast den Spawn gesetzt");
                            } else {
                                Document document = Document.loadDocument(new File("plugins/ArmZ/spawn.json"));
                                List list = document.getList("Spawn");
                                
                                list.forEach((json) -> {
                                    JSONObject obj = (JSONObject) json;
                                    
                                    obj.put("World", p.getLocation().getWorld().getName());
                                    obj.put("X", p.getLocation().getX());
                                    obj.put("Y", p.getLocation().getY());
                                    obj.put("Z", p.getLocation().getZ());
                                    obj.put("Yaw", p.getLocation().getYaw());
                                    obj.put("Pitch", p.getLocation().getPitch());
                                    
                                    list.add(gson.fromJson(gson.toJson(obj), JSONObject.class));
                                });
                            
                                document.append("Spawn", list);
                                document.save(new File("plugins/ArmZ/spawn.json"));
                            
                                p.sendMessage(ArmZ.getPrefix() + " §3Du hast den Spawn gesetzt");
                            }
                        }
                        //</editor-fold>
                        
                        //<editor-fold defaultstate="collapsed" desc="delete">
                        if(args[0].equalsIgnoreCase("delete")) {
                            if((new File("plugins/ArmZ/spawn.json")).exists()) {
                                (new File("plugins/ArmZ/spawn.json")).delete();
                                p.sendMessage(ArmZ.getPrefix() + " §3Du hast den Spawn gelöscht");
                            } else {
                                p.sendMessage(ArmZ.getPrefix() + " §3Es exestiert noch kein Spawn");
                            }
                        }
                        //</editor-fold>
                        
                        //<editor-fold defaultstate="collapsed" desc="tp">
                        if(args[0].equalsIgnoreCase("tp")) {
                            if(!(new File("plugins/ArmZ/spawn.json")).exists()) {
                                p.sendMessage(ArmZ.getPrefix() + " §cEs exestiert noch kein Spawn");
                            } else {
                                Document document = Document.loadDocument(new File("plugins/ArmZ/spawn.json"));
                                List list = document.getList("Spawn");
                                
                                list.forEach((json) -> {
                                    JSONObject obj = (JSONObject) json;
                                    
                                    String world = (String) obj.get("World");
                                    double x = (double) obj.get("X");
                                    double y = (double) obj.get("Y");
                                    double z = (double) obj.get("Z");
                                    double yaw = (double) obj.get("Yaw");
                                    double pitch = (double) obj.get("Pitch");
                                    Location loc = new Location(Bukkit.getWorld(world), x, y, z, (float) yaw, (float) pitch);
                                    
                                    p.teleport(loc);
                                });
                            }
                        }
                        //</editor-fold>
                        
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
