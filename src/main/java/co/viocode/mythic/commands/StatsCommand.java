package co.viocode.mythic.commands;

import co.viocode.mythic.Mythic;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
    
    private Mythic plugin;
    public StatsCommand(Mythic plugin) {
	this.plugin = plugin;
    }
    
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
	
	// check if player
	if (!(sender instanceof Player)) {
	    sender.sendMessage("[Mythic] Command not supported in console.");
	    return true;
	}
	
	// initialize variables
	Player player = (Player) sender;
	
	// command handler
	String cmd = command.getName().toLowerCase();
	if (cmd.equals("stats")) {
	    
	    // check permission
	    if (!Mythic.checkPermission("mythic.stats", player))
		return true;
	    
	    // invalid args
	    if (args.length > 0)
		return false;
	    
	    // display player stats
	    player.sendMessage(ChatColor.GREEN + "-= Stats =-");
	    player.sendMessage(ChatColor.GREEN + "Lvl: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".general.level"));
	    player.sendMessage(ChatColor.GREEN + "Exp: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".general.exp"));
	    player.sendMessage(ChatColor.GREEN + "HP: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".secondary.health") +
		    " / " + Mythic.profileConfig.getInt(player.getName() + ".secondary.max_health"));
	    player.sendMessage(ChatColor.GREEN + "MP: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".secondary.mana") +
		    " / " + Mythic.profileConfig.getInt(player.getName() + ".secondary.max_mana"));
	    player.sendMessage(ChatColor.GREEN + "Str: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".attribute.strength"));
	    player.sendMessage(ChatColor.GREEN + "Dex: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".attribute.dexterity"));
	    player.sendMessage(ChatColor.GREEN + "Vit: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".attribute.vitality"));
	    player.sendMessage(ChatColor.GREEN + "Wis: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".attribute.wisdom"));
	    player.sendMessage(ChatColor.GREEN + "Lck: " + ChatColor.WHITE + Mythic.profileConfig.getInt(player.getName() + ".attribute.luck"));
	    return true;
	}
	
	// end of command
	return false;
    }
}