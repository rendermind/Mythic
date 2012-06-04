package co.viocode.mythic.commands;

import co.viocode.mythic.Mythic;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MythicCommand  implements CommandExecutor {
    
    private Mythic plugin;
    public MythicCommand(Mythic plugin) {
	this.plugin = plugin;
    }
    
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
	
	// check if player
	Boolean isPlayer = true;
	if (!(sender instanceof Player))
	    isPlayer = false;
	
	// initialize variables
	Player player = null;
	if (isPlayer)
	    player = (Player) sender;
	
	// command handler
	String cmd = command.getName().toLowerCase();
	if (cmd.equals("mythic")) {
	    
	    // <command>
	    if (args.length == 0) {
		sender.sendMessage(ChatColor.GREEN + plugin.getName() + " v" + plugin.getDescription().getVersion());
		sender.sendMessage(ChatColor.GREEN + plugin.getDescription().getDescription());
		return true;
	    }
	    
	    // <command> [show] [hp]
	    if (args.length == 2 && args[0].equalsIgnoreCase("show") && args[1].equalsIgnoreCase("hp")) {
		
		// check permissions
		if (!Mythic.checkPermission("mythic.stats", player))
		    return true;
		
		// initialize variables
		String path = player.getName() + ".option.show_health_regen";
		
		// toggle option
		if (Mythic.profileConfig.getBoolean(path)) {
		    Mythic.profileConfig.set(path, Boolean.FALSE);
		    player.sendMessage(ChatColor.GREEN + "Show health regeneration " + ChatColor.GOLD + "disabled.");
		} else {
		    Mythic.profileConfig.set(path, Boolean.TRUE);
		    player.sendMessage(ChatColor.GREEN + "Show health regeneration " + ChatColor.GOLD + "enabled.");
		}
		return true;
	    }
	    
	    // <command> [show] [mp]
	    if (args.length == 2 && args[0].equalsIgnoreCase("show") && args[1].equalsIgnoreCase("mp")) {
		
		// check permissions
		if (!Mythic.checkPermission("mythic.stats", player))
		    return true;
		
		// initialize variables
		String path = player.getName() + ".option.show_mana_regen";
		
		// toggle option
		if (Mythic.profileConfig.getBoolean(path)) {
		    Mythic.profileConfig.set(path, Boolean.FALSE);
		    player.sendMessage(ChatColor.GREEN + "Show mana regeneration " + ChatColor.GOLD + " disabled.");
		} else {
		    Mythic.profileConfig.set(path, Boolean.TRUE);
		    player.sendMessage(ChatColor.GREEN + "Show mana regeneration " + ChatColor.GOLD + " enabled.");
		}
		return true;
	    }
	}
	
	// end of command
	return false;
    }
}