package co.viocode.mythic;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

class MythicListener implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
	
	// initialize variables
	Player player = event.getPlayer();
	String defaultClass = Formulas.getDefaultClass();
	
	// create new profile
	if (!Mythic.profileConfig.contains(player.getName())) {
	    Mythic.profileConfig.set(player.getName() + ".general.level", 1);
	    Mythic.profileConfig.set(player.getName() + ".general.exp", 0);
	    Mythic.profileConfig.set(player.getName() + ".attribute.strength", Mythic.classConfig.getInt(defaultClass + ".attribute.strength"));
	    Mythic.profileConfig.set(player.getName() + ".attribute.dexterity", Mythic.classConfig.getInt(defaultClass + ".attribute.dexterity"));
	    Mythic.profileConfig.set(player.getName() + ".attribute.vitality", Mythic.classConfig.getInt(defaultClass + ".attribute.vitality"));
	    Mythic.profileConfig.set(player.getName() + ".attribute.wisdom", Mythic.classConfig.getInt(defaultClass + ".attribute.wisdom"));
	    Mythic.profileConfig.set(player.getName() + ".attribute.luck", Mythic.classConfig.getInt(defaultClass + ".attribute.wisdom"));
	    Mythic.profileConfig.set(player.getName() + ".secondary.health", Formulas.getMaxHealth(player));
	    Mythic.profileConfig.set(player.getName() + ".secondary.mana", Formulas.getMaxMana(player));
	    //Mythic.profileConfig.set(player.getName() + ".secondary.armor", 0);
	    //Mythic.profileConfig.set(player.getName() + ".secondary.melee_damage", 0);
	    //Mythic.profileConfig.set(player.getName() + ".secondary.ranged_damage", 0);
	    //Mythic.profileConfig.set(player.getName() + ".secondary.attack_speed", 0);
	    //Mythic.profileConfig.set(player.getName() + ".secondary.move_speed", 0);
	    Mythic.saveProfileConfig();
	    Mythic.log.info("[Mythic] Created " + player.getName() + " in profiles.yml");
	}
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
	
	// initialize variables
	Player player = event.getPlayer();
	
	// set player health
	Mythic.profileConfig.set(player.getName() + ".secondary.health", Formulas.getMaxHealth(player));
    }
    
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
	
	// check if player
	if (!event.getEntityType().equals(EntityType.PLAYER))
	    return;
	
	// initialize variables
	Player player = (Player)event.getEntity();
	String path = player.getName() + ".secondary.health";
	
	// 
	Mythic.profileConfig.set(path, Mythic.profileConfig.getInt(path) - event.getDamage());
	if (Mythic.profileConfig.getInt(path) < 0)
	    Mythic.profileConfig.set(path, 0);
	player.sendMessage(ChatColor.RED + "[Mythic] onEntityDamage: " + ChatColor.GOLD + event.getDamage());
	
	// update health bar
	event.setDamage(0);
	Formulas.updateHealthBar(player);
	
	
    }
    
    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
	
	// check if player
	if (!event.getEntityType().equals(EntityType.PLAYER))
	    return;
	
	// initliaze variables
	Player player = (Player)event.getEntity();
	String path = player.getName() + ".secondary.health";
	
	//
	Mythic.profileConfig.set(path, Mythic.profileConfig.getInt(path) + Formulas.getHealthRegen(player));
	if (Mythic.profileConfig.getInt(path) > Formulas.getMaxMana(player))
	    Mythic.profileConfig.set(path, Formulas.getMaxHealth(player));
	player.sendMessage(ChatColor.RED + "[Mythic] onEntityRegainHealth: " + ChatColor.GOLD + Mythic.profileConfig.getInt(path));
	
	// update health bar
	event.setAmount(0);
	Formulas.updateHealthBar(player);
    }
}