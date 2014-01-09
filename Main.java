package Apples;
import java.io.File;
import java.io.InputStream;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	
	@Override
	public void onEnable(){
		
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		getCommand("tpw").setExecutor(new Commands());
		getCommand("givekill").setExecutor(new Commands());
		getCommand("listkills").setExecutor(new Commands());
		getCommand("kexist").setExecutor(new Commands());
		getCommand("addkiller").setExecutor(new Commands());
		getCommand("removekill").setExecutor(new Commands());
		getCommand("tpp").setExecutor(new Commands());
		getCommand("cw").setExecutor(new Commands()); //create world
		Bukkit.getServer().createWorld(new WorldCreator("worldend").environment(World.Environment.NORMAL));
		Bukkit.getServer().createWorld(new WorldCreator("skyworld").environment(World.Environment.NORMAL));
		Bukkit.getServer().createWorld(new WorldCreator("mainworld").environment(World.Environment.NORMAL));
	}
	
	
	public static File dataFolder;
	public static InputStream killstream;
	@Override
	public void onLoad(){
		dataFolder = getDataFolder();
		killstream = getResource("kills.yml");
		
	}

}
