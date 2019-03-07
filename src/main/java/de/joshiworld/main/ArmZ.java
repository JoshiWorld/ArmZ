package de.joshiworld.main;

import de.joshiworld.commands.Bau_Command;
import de.joshiworld.commands.Chest_Command;
import de.joshiworld.commands.Spawn_Command;
import de.joshiworld.listener.ChestListener;
import de.joshiworld.listener.PlayerListener;
import de.joshiworld.listener.ZombieSpawnListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author JoshiWorld
 */
public class ArmZ extends JavaPlugin {
    private static ArmZ plugin;
    private static String Prefix = "§7[§aArmZ§7]";
    
    public static List<Player> combat = new ArrayList<>();
    
    public static List<Player> bau = new ArrayList<>();
    
    public static List<Player> medicalChests = new ArrayList<>();
    public static List<Player> militaryChests = new ArrayList<>();
    public static List<Player> policeChests = new ArrayList<>();
    public static List<Player> normalyChests = new ArrayList<>();
    public static List<Player> checkChests = new ArrayList<>();
    
    @Override
    public void onEnable() {
        plugin = this;
        
        //<editor-fold defaultstate="collapsed" desc="Ordner & File create">
        File ordner = new File("plugins/ArmZ/");
        if(!ordner.exists()) {
            ordner.mkdir();
        }
        
        File chests = new File("plugins/ArmZ/Chests/");
        if(!chests.exists()) {
            chests.mkdir();
            
            File medical = new File("plugins/ArmZ/Chests/medical.json");
            try {
                medical.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ArmZ.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            File military = new File("plugins/ArmZ/Chests/military.json");
            try {
                military.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ArmZ.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            File police = new File("plugins/ArmZ/Chests/police.json");
            try {
                police.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ArmZ.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            File normaly = new File("plugins/ArmZ/Chests/normaly.json");
            try {
                normaly.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ArmZ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //</editor-fold>
        
        init();
        
        System.out.println(getPrefix() + " §aArmZ enabled");
    }
    
    @Override
    public void onDisable() {
        System.out.println(getPrefix() + " §cArmZ disabled");
    }
    
    //<editor-fold defaultstate="collapsed" desc="getInstance, getPrefix">
    public static ArmZ getInstance() {
        return plugin;
    }
    
    public static String getPrefix() {
        return Prefix;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="init">
    private void init() {
        initListeners();
        initCommands();
    }
    
    //<editor-fold defaultstate="collapsed" desc="initListeners">
    private void initListeners() {
        register(new PlayerListener());
        register(new ChestListener());
        register(new ZombieSpawnListener());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="initCommands">
    private void initCommands() {
        this.getCommand("spawn").setExecutor(new Spawn_Command());
        this.getCommand("chest").setExecutor(new Chest_Command());
        this.getCommand("bau").setExecutor(new Bau_Command());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="register">
    private void register(Listener listener) {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        
        pm.registerEvents(listener, this);
    }
    //</editor-fold>
    //</editor-fold>
    
}
