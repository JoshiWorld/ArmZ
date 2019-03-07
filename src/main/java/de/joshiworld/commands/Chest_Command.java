package de.joshiworld.commands;

import de.joshiworld.main.ArmZ;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 *
 * @author JoshiWorld
 */
public class Chest_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            
            if(cmd.getName().equalsIgnoreCase("chest")) {
                if(PermissionsEx.getUser(p).getGroups()[0].has("ArmZ.chest")) {
                    if(args.length == 0) {
                        p.sendMessage("/chest");
                        p.sendMessage("/chest normal");
                        p.sendMessage("/chest medic");
                        p.sendMessage("/chest military");
                        p.sendMessage("/chest police");
                        p.sendMessage("/chest checkChest");
                        return true;
                    }
                    
                    if(args.length == 1) {
                        //<editor-fold defaultstate="collapsed" desc="normal">
                        if(args[0].equalsIgnoreCase("normal")) {
                            if(!ArmZ.normalyChests.contains(p)) {
                                ArmZ.normalyChests.add(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt im Chest-Modus für §aNormal");
                            } else {
                                ArmZ.normalyChests.remove(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt nicht mehr im Chest-Modus für §aNormal");
                            }
                        }
                        //</editor-fold>
                        
                        //<editor-fold defaultstate="collapsed" desc="medic">
                        if(args[0].equalsIgnoreCase("medic")) {
                            if(!ArmZ.medicalChests.contains(p)) {
                                ArmZ.medicalChests.add(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt im Chest-Modus für §aMedic");
                            } else {
                                ArmZ.medicalChests.remove(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt nicht mehr im Chest-Modus für §aMedic");
                            }
                        }
                        //</editor-fold>
                        
                        //<editor-fold defaultstate="collapsed" desc="military">
                        if(args[0].equalsIgnoreCase("military")) {
                            if(!ArmZ.militaryChests.contains(p)) {
                                ArmZ.militaryChests.add(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt im Chest-Modus für §aMilitary");
                            } else {
                                ArmZ.militaryChests.remove(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt nicht mehr im Chest-Modus für §aMilitary");
                            }
                        }
                        //</editor-fold>
                        
                        //<editor-fold defaultstate="collapsed" desc="police">
                        if(args[0].equalsIgnoreCase("police")) {
                            if(!ArmZ.policeChests.contains(p)) {
                                ArmZ.policeChests.add(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt im Chest-Modus für §aPolice");
                            } else {
                                ArmZ.policeChests.remove(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt nicht mehr im Chest-Modus für §aPolice");
                            }
                        }
                        //</editor-fold>
                        
                        //<editor-fold defaultstate="collapsed" desc="checkChest">
                        if(args[0].equalsIgnoreCase("checkChest")) {
                            if(!ArmZ.checkChests.contains(p)) {
                                ArmZ.checkChests.add(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt im Check-Modus");
                            } else {
                                ArmZ.checkChests.remove(p);
                                p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt nicht mehr im Check-Modus");
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
