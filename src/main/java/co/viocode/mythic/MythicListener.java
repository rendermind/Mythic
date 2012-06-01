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
	
	if (!Mythic.profileConfig.contains(player.getName())) {
	    
	    Mythic.log.info("[Mythic] Setup " + player.getName() + " as new player");
	}
    }
    
}