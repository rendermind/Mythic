package co.viocode.mythic;

import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
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
	regen += Mythic.attributeConfig.getDouble("strength.health_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.strength");
	regen += Mythic.attributeConfig.getDouble("dexterity.health_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity");
	regen += Mythic.attributeConfig.getDouble("vitality.health_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality");
	regen += Mythic.attributeConfig.getDouble("wisdom.health_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom");
	regen += Mythic.attributeConfig.getDouble("luck.health_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.luck");
	
	// return stat
	return (int)regen;
    }
    
    static public int getManaRegen(Player player) {
	
	// initialize variables
	double regen = 0;
	
	// calculate from attributes
	regen += Mythic.attributeConfig.getDouble("strength.mana_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.strength");
	regen += Mythic.attributeConfig.getDouble("dexterity.mana_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity");
	regen += Mythic.attributeConfig.getDouble("vitality.mana_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality");
	regen += Mythic.attributeConfig.getDouble("wisdom.mana_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom");
	regen += Mythic.attributeConfig.getDouble("luck.mana_regen") * Mythic.profileConfig.getInt(player.getName() + ".attribute.luck");
	
	// return stat
	return (int)regen;
    }
    
    static public int getArmor(Player player) {
	
	// initialize variables
	double armor = 0;
	
	// calculate from attributes
	armor += Mythic.attributeConfig.getDouble("strength.armor") * Mythic.profileConfig.getInt(player.getName() + ".attribute.strength");
	armor += Mythic.attributeConfig.getDouble("dexterity.armor") * Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity");
	armor += Mythic.attributeConfig.getDouble("vitality.armor") * Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality");
	armor += Mythic.attributeConfig.getDouble("wisdom.armor") * Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom");
	armor += Mythic.attributeConfig.getDouble("luck.armor") * Mythic.profileConfig.getInt(player.getName() + ".attribute.luck");
	
	// return stat
	return (int)armor;
    }
    
    static public int getMeleeDamage(Player player) {
	
	// initialize variables
	double damage = 0;
	
	// calculate from attributes
	damage += Mythic.attributeConfig.getDouble("strength.melee_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.strength");
	damage += Mythic.attributeConfig.getDouble("dexterity.melee_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity");
	damage += Mythic.attributeConfig.getDouble("vitality.melee_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality");
	damage += Mythic.attributeConfig.getDouble("wisdom.melee_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom");
	damage += Mythic.attributeConfig.getDouble("luck.melee_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.luck");
	
	// return stat
	return (int)damage;
    }
    
    static public int getRangeDamage(Player player) {
	
	// initialize variables
	double damage = 0;
	
	// calculate from attributes
	damage += Mythic.attributeConfig.getDouble("strength.range_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.strength");
	damage += Mythic.attributeConfig.getDouble("dexterity.range_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity");
	damage += Mythic.attributeConfig.getDouble("vitality.range_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality");
	damage += Mythic.attributeConfig.getDouble("wisdom.range_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom");
	damage += Mythic.attributeConfig.getDouble("luck.range_damage") * Mythic.profileConfig.getInt(player.getName() + ".attribute.luck");
	
	// return stat
	return (int)damage;
    }
    
    static public int getMobHealth(EntityType entity) {
        
        // initialize variables
        double health = 0;
        
        // calculate from config
        if (Mythic.mobConfig.isConfigurationSection(entity.toString()))
            health = Mythic.mobConfig.getDouble(entity.toString() + ".health");
        else
            health = 0;
        
        // return stat
        return (int)health;
    }
    
    static public int getMobDamage(EntityType entity) {
        
        // initialize variables
        double damage = 0;
        
        // calculate from config
        if (Mythic.mobConfig.isConfigurationSection(entity.toString()))
            damage = Mythic.mobConfig.getDouble(entity.toString() + ".damage");
        else
            damage = 0;
        
        // return stat
        return (int)damage;
    }
    
    static public void updateHealthBar(Player player) {
	
	// initialize variables
	double health = 20 * (Mythic.profileConfig.getInt(player.getName() + ".secondary.health") / (double)getMaxHealth(player));
	
	// set player health
	player.setHealth((int)health);
	player.sendMessage(ChatColor.LIGHT_PURPLE + "[Mythic] updateHealthBar: " + ChatColor.GOLD + 100 * ((double)player.getHealth() / 20) + "%");
    }
}