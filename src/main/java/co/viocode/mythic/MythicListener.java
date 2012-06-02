package co.viocode.mythic;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
	    Mythic.profileConfig.set(player.getName() + ".secondary.health", 0);
	    Mythic.profileConfig.set(player.getName() + ".secondary.mana", 0);
	    Mythic.profileConfig.set(player.getName() + ".secondary.armor", 0);
	    Mythic.profileConfig.set(player.getName() + ".secondary.melee_damage", 0);
	    Mythic.profileConfig.set(player.getName() + ".secondary.ranged_damage", 0);
	    Mythic.profileConfig.set(player.getName() + ".secondary.attack_speed", 0);
	    Mythic.profileConfig.set(player.getName() + ".secondary.move_speed", 0);
	    Mythic.saveProfileConfig();
	    Mythic.log.info("[Mythic] Created " + player.getName() + " in profiles.yml");
	}
	
	// set player stats
	player.setHealth(Formulas.getHealth(player));
    }
    
}