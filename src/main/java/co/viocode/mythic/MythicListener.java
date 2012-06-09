package co.viocode.mythic;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

class MythicListener implements Listener {
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
	
	// initialize variables
	Player player = event.getPlayer();
	String defaultClass = Formulas.getDefaultClass();
	String path = null; String path2 = null;
	
	// create new profile
	if (!Mythic.profileConfig.contains(player.getName())) {
	    
	    // create options
	    path = player.getName() + ".option.";
	    Mythic.profileConfig.set(path + "show_health_regen", Boolean.TRUE);
	    Mythic.profileConfig.set(path + "show_mana_regen", Boolean.TRUE);
	    
	    // create general
	    path = player.getName() + ".general.";
	    Mythic.profileConfig.set(path + "level", 1);
	    Mythic.profileConfig.set(path + "exp", 0);
	    
	    // create attribute
	    path = player.getName() + ".attribute."; path2 = defaultClass + ".attribute.";
	    Mythic.profileConfig.set(path + "strength", Mythic.classConfig.getInt(path2 + "strength"));
	    Mythic.profileConfig.set(path + "dexterity", Mythic.classConfig.getInt(path2 + "dexterity"));
	    Mythic.profileConfig.set(path + "vitality", Mythic.classConfig.getInt(path2 + "vitality"));
	    Mythic.profileConfig.set(path + "wisdom", Mythic.classConfig.getInt(path2 + "wisdom"));
	    Mythic.profileConfig.set(path + "luck", Mythic.classConfig.getInt(path2 + "wisdom"));
	    
	    // create secondary
	    path = player.getName() + ".secondary.";
	    Mythic.profileConfig.set(path + "health", Formulas.getMaxHealth(player));
	    Mythic.profileConfig.set(path + "mana", Formulas.getMaxMana(player));
	    
	    // update health bar
	    Mythic.saveProfileConfig();
	    Formulas.updateHealthBar(player);
	    
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
        Boolean isPlayer = false;
        if (event.getEntityType().equals(EntityType.PLAYER))
            isPlayer = true;
	//if (!event.getEntityType().equals(EntityType.PLAYER))
	//    return;
	
	// initialize variables
        Player player = null; String path = null;
        if (isPlayer) {
            player = (Player)event.getEntity();
            path = player.getName() + ".secondary.health";
        }
	
	// check if creative
        if (isPlayer)
            if (player.getGameMode().equals(GameMode.CREATIVE))
                return;
	
	// check if dead
        if (isPlayer)
            if (player.getHealth() <= 0)
                return;
	
	// calculate attacker damage
	if (event instanceof EntityDamageByEntityEvent) {
	    EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
            
            // bow & arrow damage
	    if(e.getDamager() instanceof Arrow) {
		Arrow arrow = (Arrow)e.getDamager();
		if (arrow.getShooter().getType().equals(EntityType.PLAYER)) {
		    e.setDamage(Formulas.getRangeDamage((Player)arrow.getShooter()));
                } else {
		    e.setDamage(Formulas.getMobDamage(arrow.getShooter().getType())); // mob attacking w/ arrow
                }
            // player melee damage
	    } else if (e.getDamager() instanceof Player) {
                Player damager = (Player)e.getDamager();
		e.setDamage(Formulas.getMeleeDamage(damager));
                damager.sendMessage(ChatColor.LIGHT_PURPLE + "[Mythic] onEntityDamageByEntity: " + ChatColor.GOLD + e.getDamage());
	    // mob melee damage
            } else {
                e.setDamage(Formulas.getMobDamage(e.getEntityType()));
            }
	}
	
	// calculate modified damage
        if (isPlayer) {
            double damage = event.getDamage() - Formulas.getArmor(player);
            if (damage < 0)
                damage = 0;
            Mythic.profileConfig.set(path, Mythic.profileConfig.getInt(path) - damage);
            if (Mythic.profileConfig.getInt(path) < 0)
                Mythic.profileConfig.set(path, 0);
            player.sendMessage(ChatColor.LIGHT_PURPLE + "[Mythic] onEntityDamage: " + ChatColor.GOLD + event.getDamage());
	
            // update health bar
            event.setDamage(0);
            Formulas.updateHealthBar(player);
        }
    }
    
    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
	
	// check if player
	if (!event.getEntityType().equals(EntityType.PLAYER))
	    return;
	
	// initliaze variables
	Player player = (Player)event.getEntity();
	String path = null;
	
	// health regen
	path = player.getName() + ".secondary.health";
	Mythic.profileConfig.set(path, Mythic.profileConfig.getInt(path) + Formulas.getHealthRegen(player));
	if (Mythic.profileConfig.getInt(path) > Formulas.getMaxHealth(player))
	    Mythic.profileConfig.set(path, Formulas.getMaxHealth(player));
	else
	    if (Mythic.profileConfig.getBoolean(player.getName() + ".option.show_health_regen"))
		player.sendMessage(ChatColor.RED + "HP: " + Mythic.profileConfig.getInt(player.getName() + ".secondary.health") + " / " +
		    Formulas.getMaxHealth(player) + ", " + ChatColor.GOLD + " +" + Formulas.getHealthRegen(player));
	
	// mana regen
	path = player.getName() + ".secondary.mana";
	Mythic.profileConfig.set(path, Mythic.profileConfig.getInt(path) + Formulas.getManaRegen(player));
	if (Mythic.profileConfig.getInt(path) > Formulas.getMaxMana(player))
	    Mythic.profileConfig.set(path, Formulas.getMaxMana(player));
	else
	    if (Mythic.profileConfig.getBoolean(player.getName() + ".option.show_mana_regen"))
		player.sendMessage(ChatColor.BLUE + "MP: " + Mythic.profileConfig.getInt(player.getName() + ".secondary.mana") + " / " +
		    Formulas.getMaxMana(player) + ", " + ChatColor.GOLD + " +" + Formulas.getManaRegen(player));
	
	// update health bar
	event.setAmount(0);
	Formulas.updateHealthBar(player);
    }
    
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        event.setDroppedExp(0);
    }
    
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        
        // set mob health
        if (Formulas.getMobHealth(event.getEntityType()) > 0) {
            event.getEntity().setHealth(Formulas.getMobHealth(event.getEntityType()));
            Mythic.log.warning("[Mythic] " + event.getEntityType() + ": " + Formulas.getMobHealth(event.getEntityType()));
        }
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
	
	// initialize variables
	//Player player = event.getPlayer();
	//Vector vector = player.getLocation().getDirection().setY(0);
	
	//player.setVelocity(vector.multiply(1));
    }
}