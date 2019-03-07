package de.joshiworld.commands;

import de.joshiworld.main.ArmZ;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 *
 * @author JoshiWorld
 */
public class Bau_Command implements CommandExecutor {
    
    private Map<Player, ItemStack[]> inv = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            
            if(cmd.getName().equalsIgnoreCase("bau")) {
                if(PermissionsEx.getUser(p).getGroups()[0].has("ArmZ.bau")) {
                    if(ArmZ.bau.contains(p)) {
                        ArmZ.bau.remove(p);
                        
                        p.getInventory().clear();
                        p.getInventory().setContents(inv.get(p));
                        p.setGameMode(GameMode.SURVIVAL);
                        
                        inv.remove(p);
                        
                        p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt nicht mehr im Bau-Modus");
                        return true;
                    } else {
                        ArmZ.bau.add(p);
                        
                        inv.put(p, p.getInventory().getContents());
                        p.getInventory().clear();
                        p.setGameMode(GameMode.CREATIVE);
                        
                        p.sendMessage(ArmZ.getPrefix() + " §3Du bist jetzt im Bau-Modus");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
