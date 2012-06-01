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
	    
	    // check permission
	    if (isPlayer)
		if (!Mythic.checkPermission("mythic.about", player))
		    return true;
	    
	    // invalid args
	    if (args.length > 0)
		return false;
	    
	    // <command>
	    sender.sendMessage(ChatColor.GREEN + plugin.getName() + " v" + plugin.getDescription().getVersion());
	    sender.sendMessage(ChatColor.GREEN + plugin.getDescription().getDescription());
	    
	    return true;
	}
	
	// end of command
	return false;
    }
}
