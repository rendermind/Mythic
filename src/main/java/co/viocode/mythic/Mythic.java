package co.viocode.mythic;

import co.viocode.mythic.commands.MythicCommand;
import co.viocode.mythic.commands.StatsCommand;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Mythic extends JavaPlugin implements Listener {
    
    static public Logger log = Logger.getLogger("Mythic");
    
    static public FileConfiguration pluginConfig = null;
    static File pluginConfigFile = null;
    static public FileConfiguration profileConfig = null;
    static File profileConfigFile = null;
    static public FileConfiguration classConfig = null;
    static File classConfigFile = null;
    static public FileConfiguration attributeConfig = null;
    static File attributeConfigFile = null;
    static public FileConfiguration mobConfig = null;
    static File mobConfigFile = null;
    
    @Override
    public void onDisable() {
	log.info("[Mythic] Plugin is now disabled");
    }

    @Override
    public void onEnable() {
	
	// setup config files
	loadPluginConfig();
	savePluginConfig();
	loadProfileConfig();
	saveProfileConfig();
	loadClassConfig();
	saveClassConfig();
	loadAttributeConfig();
	saveAttributeConfig();
        loadMobConfig();
        saveMobConfig();
	
	// register events
	getServer().getPluginManager().registerEvents(new MythicListener(), this);
	
	// register commands
	getCommand("mythic").setExecutor(new MythicCommand(this));
	getCommand("stats").setExecutor(new StatsCommand(this));
        
        // set mob health
        log.info("[Mythic] Setting mob health...");
        for (World eachWorld : getServer().getWorlds()) {
            for (LivingEntity eachEntity: getServer().getWorld(eachWorld.getUID()).getLivingEntities()) {
                if (mobConfig.isConfigurationSection(eachEntity.getType().toString()))
                    eachEntity.setHealth(mobConfig.getInt(eachEntity.getType() + ".hp"));
            }
        }
	
	log.info("[Mythic] Plugin is now enabled");
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
    static public void savePluginConfig() {
	if (pluginConfig == null || pluginConfigFile == null)
	    return;
	try {
	    pluginConfig.save(pluginConfigFile);
	} catch (IOException e) {
	    log.severe("[Mythic] Unable to save plugin config to " + pluginConfigFile);
	}
    }
    
    // load profile confie
    public FileConfiguration loadProfileConfig() {
	if (profileConfig == null) {
	    if (profileConfigFile == null)
		profileConfigFile = new File(this.getDataFolder(), "profiles.yml");
	    if (profileConfigFile.exists()) {
		profileConfig = YamlConfiguration.loadConfiguration(profileConfigFile);
	    } else {
		InputStream defConfigStream = getResource("profiles.yml");
		profileConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	    }
	}
	return profileConfig;
    }
    
    // save profile config
    static public void saveProfileConfig() {
	if (profileConfig == null || profileConfigFile == null)
	    return;
	try {
	    profileConfig.save(profileConfigFile);
	} catch (IOException e) {
	    log.severe("[Mythic] Unable to save profile config to " + profileConfigFile);
	}
    }
    
    // load class config
    public FileConfiguration loadClassConfig() {
	if (classConfig == null) {
	    if (classConfigFile == null)
		classConfigFile = new File(this.getDataFolder(), "classes.yml");
	    if (classConfigFile.exists()) {
		classConfig = YamlConfiguration.loadConfiguration(classConfigFile);
	    } else {
		InputStream defConfigStream = getResource("classes.yml");
		classConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	    }
	}
	return classConfig;
    }
    
    // save class config
    static public void saveClassConfig() {
	if (classConfig == null || classConfigFile == null)
	    return;
	try {
	    classConfig.save(classConfigFile);
	} catch (IOException e) {
	    log.severe("[Mythic] Unable to save class config to " + attributeConfigFile);
	}
    }
    
    // load attribute config
    public FileConfiguration loadAttributeConfig() {
	if (attributeConfig == null) {
	    if (attributeConfigFile == null)
		attributeConfigFile = new File(this.getDataFolder(), "attributes.yml");
	    if (attributeConfigFile.exists()) {
		attributeConfig = YamlConfiguration.loadConfiguration(attributeConfigFile);
	    } else {
		InputStream defConfigStream = getResource("attributes.yml");
		attributeConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	    }
	}
	return attributeConfig;
    }
    
    // save attribute config
    static public void saveAttributeConfig() {
	if (attributeConfig == null || attributeConfigFile == null)
	    return;
	try {
	    attributeConfig.save(attributeConfigFile);
	} catch (IOException e) {
	    log.severe("[Mythic] Unable to save attribute config to " + attributeConfigFile);
	}
    }
    
    // load mobs config
    public FileConfiguration loadMobConfig() {
        if (mobConfig == null) {
            if (mobConfigFile == null)
                mobConfigFile = new File(this.getDataFolder(), "mobs.yml");
            if (mobConfigFile.exists()) {
                mobConfig = YamlConfiguration.loadConfiguration(mobConfigFile);
            } else {
                InputStream defConfigStream = getResource("mobs.yml");
                mobConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            }
        }
        return mobConfig;
    }
    
    // save mob config
    static public void saveMobConfig() {
        if (mobConfig == null || mobConfigFile == null)
            return;
        try {
            mobConfig.save(mobConfigFile);
        } catch (IOException e) {
            log.severe("[Mythic] Unable to save mob config to " + mobConfigFile);
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