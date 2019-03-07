package de.joshiworld.methods;

import de.joshiworld.main.ArmZ;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;

/**
 *
 * @author JoshiWorld
 */
public class Metadata {
    
    public static void setMetadata(String metadata, Block block, Object obj) {
        if(!block.hasMetadata(metadata)) {
            block.setMetadata(metadata, new FixedMetadataValue(ArmZ.getInstance(), obj));
        }
    }
    
    public static void removeMetadata(String metadata, Block block) {
        if(block.hasMetadata(metadata)) {
            block.removeMetadata(metadata, ArmZ.getInstance());
        }
    }
    
}
