package co.viocode.mythic;

import java.util.Set;
import org.bukkit.ChatColor;
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
    
    static public int getMaxHealth(Player player) {
	
	// initialize variables
	double health = 0;
	
	// calculate from attributes
	health += Mythic.attributeConfig.getDouble("strength.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.strength");
	health += Mythic.attributeConfig.getDouble("dexterity.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity");
	health += Mythic.attributeConfig.getDouble("vitality.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality");
	health += Mythic.attributeConfig.getDouble("wisdom.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom");
	health += Mythic.attributeConfig.getDouble("luck.health") * Mythic.profileConfig.getInt(player.getName() + ".attribute.luck");
	
	// return stat
	return (int)health;
    }
    
    static public int getMaxMana(Player player) {
	
	// initalize variables
	double mana = 0;
	
	// calculate from attributes
	mana += Mythic.attributeConfig.getDouble("strength.mana") * Mythic.profileConfig.getInt(player.getName() + ".attribute.strength");
	mana += Mythic.attributeConfig.getDouble("dexterity.mana") * Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity");
	mana += Mythic.attributeConfig.getDouble("vitality.mana") * Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality");
	mana += Mythic.attributeConfig.getDouble("wisdom.mana") * Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom");
	mana += Mythic.attributeConfig.getDouble("luck.mana") * Mythic.profileConfig.getInt(player.getName() + ".attribute.luck");
	
	// return stat
	return (int)mana;
    }
    
    static public int getHealthRegen(Player player) {
	
	// initialize variables
	double regen = 0;
	
	// calculate from attributes
	regen = 1;
	
	// return stat
	return (int)regen;
    }
    
    static public int getManaRegen(Player player) {
	
	// initialize variables
	double regen = 0;
	
	// calculate from attributes
	regen = 1;
	
	// return stat
	return (int)regen;
    }
    
    static public void updateHealthBar(Player player) {
	
	// initialize variables
	double health = 20 * (Mythic.profileConfig.getInt(player.getName() + ".secondary.health") / (double)getMaxHealth(player));
	
	// set player health
	player.setHealth((int)health);
	player.sendMessage(ChatColor.RED + "[Mythic] updateHealthBar: " + ChatColor.GOLD + 100 * ((double)player.getHealth() / 20) + "%");
    }
}