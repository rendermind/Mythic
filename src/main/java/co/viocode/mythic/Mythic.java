package co.viocode.mythic;

import co.viocode.mythic.commands.MythicCommand;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Mythic extends JavaPlugin implements Listener {
    
    static public Logger log = Logger.getLogger("Mythic");
    
    static public FileConfiguration pluginConfig = null;
    static File pluginConfigFile = null;
    
    @Override
    public void onDisable() {
	log.info(this + " is now disabled.");
    }

    @Override
    public void onEnable() {
	
	// setup config files
	loadPluginConfig();
	savePluginConfig();
	
	// register events
	getServer().getPluginManager().registerEvents(new MythicListener(), this);
	
	// register commands
	getCommand("mythic").setExecutor(new MythicCommand(this));
	
	log.info(this + " is now enabled.");
    }
    
    // load plugin config
    public FileConfiguration loadPluginConfig() {
	if (pluginConfig == null) {
	    if (pluginConfigFile == null)
		pluginConfigFile = new File(this.getDataFolder(), "config.yml");
	    if (pluginConfigFile.exists()) {
		pluginConfig = YamlConfiguration.loadConfiguration(pluginConfigFile);
	    } else {
		InputStream defConfigStream = getResource("config.yml");
		pluginConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	    }
	}
	return pluginConfig;
    }
    
    // save plugin config
    public void savePluginConfig() {
	if (pluginConfig == null || pluginConfigFile == null)
	    return;
	try {
	    pluginConfig.save(pluginConfigFile);
	} catch (IOException e) {
	    log.severe("[Mythic] Unable to save plugin config to " + pluginConfigFile);
	}
    }
    
    // check permission node
    static public boolean checkPermission(String permission, Player player) {
	if (!player.hasPermission(permission)) {
	    player.sendMessage(ChatColor.RED + "You do not have permission.");
	    log.info("[Mythic]"  + player.getName() + " was denied permission to " + permission);
	    return false;
	}
	return true;
    }
}