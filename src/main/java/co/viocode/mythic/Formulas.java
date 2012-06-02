package co.viocode.mythic;

import java.util.Set;
import org.bukkit.entity.Player;

public class Formulas {
    
    private Mythic plugin;
    public Formulas(Mythic plugin) {
	this.plugin = plugin;
    }
    
    static public String getDefaultClass() {
	
	// initialize variables
	String defaultClass = null;
	Set<String> classes = Mythic.classConfig.getConfigurationSection("").getKeys(false);
	
	// calculate
	for (String each : classes)
	    if (Mythic.classConfig.contains(each + ".default"))
		if (Mythic.classConfig.getBoolean(each + ".default"))
		    defaultClass = each;
	
	// return
	return defaultClass;
    }
    
    static public int getHealth(Player player) {
	
	// initialize variables
	double health = 0;
	
	// calculate from attributes
	health += Mythic.attributeConfig.getDouble("strength.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.strength");
	health += Mythic.attributeConfig.getDouble("dexterity.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity");
	health += Mythic.attributeConfig.getDouble("vitality.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality");
	health += Mythic.attributeConfig.getDouble("wisdom.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom");
	health += Mythic.attributeConfig.getDouble("luck.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.luck");
	
	// return stat
	return (int) health;
    }
}