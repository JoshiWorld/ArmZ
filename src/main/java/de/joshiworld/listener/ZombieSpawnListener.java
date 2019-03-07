package de.joshiworld.listener;

import java.util.Random;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author JoshiWorld
 */
public class ZombieSpawnListener implements Listener {
    
    @EventHandler
    public void onZombieSpawn(EntitySpawnEvent e) {
        if(e.getEntity() instanceof Zombie) {
            Zombie zombie = (Zombie) e.getEntity();
            
            boolean speed = new Random().nextBoolean();
            
            if(speed == true) {
                int rdm = new Random().nextInt(3);
                
                zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, rdm));
            } else {
                zombie.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));
            }
        }
    }
    
}
