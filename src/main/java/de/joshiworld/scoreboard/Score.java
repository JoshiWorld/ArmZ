package de.joshiworld.scoreboard;

import de.joshiworld.misc.Document;
import java.io.File;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.json.simple.JSONObject;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

/**
 *
 * @author JoshiWorld
 */
public class Score {
    
    @SuppressWarnings("deprecation")
    public static void setScore(Player p){
		
        ScoreboardManager sm = Bukkit.getScoreboardManager();
	Scoreboard board = sm.getNewScoreboard();
	Objective o = board.registerNewObjective("test", "dummy");
	    
	o.setDisplaySlot(DisplaySlot.SIDEBAR);
	o.setDisplayName("§8§l► §e§lArmy§f§lOfMC §8§l◄");
		
	o.getScore("§0").setScore(12);
	o.getScore("§cTeamspeak-IP§8:").setScore(11);
	o.getScore("§farmyofmc.com").setScore(10);
	o.getScore("§1").setScore(9);
	PermissionUser pm = PermissionsEx.getUser(p);
	    
        PermissionGroup pg = pm.getGroups()[0];
	    
	o.getScore("§7Dein Rang§8:").setScore(8);
	Team rang = board.getTeam("rang") != null ? board.getTeam("rang") : board.registerNewTeam("rang");
            
	Document document = Document.loadDocument(new File("plugins/ArmySystem/sidebar.json"));
        List list = document.getList("Sidebar");
        list.forEach((object) -> {
            JSONObject obj = (JSONObject) object;
                
            rang.setPrefix(ChatColor.translateAlternateColorCodes('&', (String) obj.get(pg.getName())));
        });
            
	rang.addEntry("§2");
	    
	o.getScore("§2").setScore(7);
	o.getScore("§6").setScore(6);
	    
	o.getScore("§6Armys§8:").setScore(5);
	Team coins = board.getTeam("coins") != null ? board.getTeam("coins") : board.registerNewTeam("coins");
	coins.setPrefix("§f" + Coins.getCoins(p.getUniqueId().toString()));
	coins.addEntry("§8");
	    
	o.getScore("§8").setScore(4);
	o.getScore("§5").setScore(3);
	o.getScore("§aOnline-Spieler§8:").setScore(2);
	    
	Team online = board.getTeam("online") != null ? board.getTeam("online") : board.registerNewTeam("online");
	int on = Bukkit.getOnlinePlayers().size();
	online.setPrefix("§f" + on + "§f/50");
	online.addEntry("§3");
	o.getScore("§3").setScore(1);
	o.getScore("§4").setScore(0);
	   
	p.setScoreboard(board);
    }
	
    public static void updateScore(Player p){
        Objective o = p.getScoreboard().getObjective("test");
		
        if(o == null || p.getScoreboard() == null) {
            setScore(p);
	}
		
        Scoreboard board = p.getScoreboard();
		
	Team coins = board.getTeam("coins") != null ? board.getTeam("coins") : board.registerNewTeam("coins");
	Team online = board.getTeam("online") != null ? board.getTeam("online") : board.registerNewTeam("online");
		
	coins.setPrefix("§f" + Coins.getCoins(p.getUniqueId().toString()));
	int on = Bukkit.getOnlinePlayers().size();
	online.setPrefix("§f" + on + "§f/50");
    }
	
    public static void setTab(Player p) {
        if(p.getScoreboard() == null) {
            setScore(p);
        }
		
	Scoreboard board = p.getScoreboard();
		
		
	Team admin = board.getTeam("0001Admin") != null ? board.getTeam("0001Admin") : board.registerNewTeam("0001Admin");
	Team dev = board.getTeam("0002Dev") != null ? board.getTeam("0002Dev") : board.registerNewTeam("0002Dev");
	Team mod = board.getTeam("0003Mod") != null ? board.getTeam("0003Mod") : board.registerNewTeam("0003Mod");
	Team headb = board.getTeam("0004Headb") != null ? board.getTeam("0004Headb") : board.registerNewTeam("0004Headb");
	Team builder = board.getTeam("0005Builder") != null ? board.getTeam("0005Builder") : board.registerNewTeam("0005Builder");
	Team supp = board.getTeam("0006Supp") != null ? board.getTeam("0006Supp") : board.registerNewTeam("0006Supp");
	Team yt = board.getTeam("0007YT") != null ? board.getTeam("0007YT") : board.registerNewTeam("0007YT");
	Team premium = board.getTeam("0008Premium") != null ? board.getTeam("0008Premium") : board.registerNewTeam("0008Premium");
	Team member = board.getTeam("0009Member") != null ? board.getTeam("0009Member") : board.registerNewTeam("0009Member");
		
	admin.setPrefix("§4Admin §7| ");
	dev.setPrefix("§bDev §7| ");
	mod.setPrefix("§cMod §7| ");
	headb.setPrefix("§eH-Builder §7| ");
	builder.setPrefix("§eBuilder §7| ");
	supp.setPrefix("§9Supp §7| ");
	yt.setPrefix("§5YT §7| ");
	premium.setPrefix("§6Premium §7| ");
	member.setPrefix("§8Member §7| ");
		
	for(Player all : Bukkit.getOnlinePlayers()) {
            PermissionUser pu = PermissionsEx.getUser(all);
		
            if(pu.inGroup("Admin")) {
                admin.addEntry(all.getName());
            } else if(pu.inGroup("Dev")) {
		dev.addEntry(all.getName());
            } else if(pu.inGroup("Mod")) {
                mod.addEntry(all.getName());
            } else if(pu.inGroup("HeadBuilder")) {
		headb.addEntry(all.getName());
            } else if(pu.inGroup("Builder")) {
                builder.addEntry(all.getName());
            } else if(pu.inGroup("Supp")) {
		supp.addEntry(all.getName());
            } else if(pu.inGroup("YT")) {
		yt.addEntry(all.getName());
            } else if(pu.inGroup("Premium")) {
		premium.addEntry(all.getName());
            } else {
		member.addEntry(all.getName());
            }
	}
    }
	
    public static void updateTab(Player p) {
        Scoreboard board = p.getScoreboard();
		
	if(board.getTeam("0001Admin") == null || board.getTeam("0002Dev") == null || board.getTeam("0003Mod") == null || board.getTeam("0004Headb") == null ||
            board.getTeam("0005Builder") == null || board.getTeam("0006Supp") == null || board.getTeam("0007YT") == null || board.getTeam("0008Premium") == null || board.getTeam("0009Member") == null) {
                setTab(p);
	}
		
	Team admin = board.getTeam("0001Admin");
	Team dev = board.getTeam("0002Dev");
	Team mod = board.getTeam("0003Mod");
	Team headb = board.getTeam("0004Headb");
	Team builder = board.getTeam("0005Builder");
	Team supp = board.getTeam("0006Supp");
	Team yt = board.getTeam("0007YT");
	Team premium = board.getTeam("0008Premium");
	Team member = board.getTeam("0009Member");
        
        for(Player all : Bukkit.getOnlinePlayers()) {
            PermissionUser pu = PermissionsEx.getUser(all);
			
            if(pu.inGroup("Admin")) {
		admin.addEntry(all.getName());
            } else if(pu.inGroup("Dev")) {
                dev.addEntry(all.getName());
            } else if(pu.inGroup("Mod")) {
                mod.addEntry(all.getName());
            } else if(pu.inGroup("HeadBuilder")) {
		headb.addEntry(all.getName());
            } else if(pu.inGroup("Builder")) {
		builder.addEntry(all.getName());
            } else if(pu.inGroup("Supp")) {
		supp.addEntry(all.getName());
            } else if(pu.inGroup("YT")) {
		yt.addEntry(all.getName());
            } else if(pu.inGroup("Premium")) {
		premium.addEntry(all.getName());
            } else {
		member.addEntry(all.getName());
            }
	}
    }
    
}
