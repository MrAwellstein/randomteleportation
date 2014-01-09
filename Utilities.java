package Apples;

import java.io.File;
import java.io.InputStream;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Utilities {

	
	

	public static void teleport2World(Player p, String world){
		try{
			try{
				if(Bukkit.getServer().getWorlds().add(Bukkit.getWorld(world)) == true)
				{
				System.out.println(world + "is loaded");
				}
			}catch(Exception e){brodcast("Load Already Loaded or Doesnt Exist");}
		World worldz = Bukkit.getWorld(world);
		/*
	if(world == null){
			WorldCreator creator = new WorldCreator(world);
			creator.environment(World.Environment.NORMAL);
			creator.generateStructures(true);
			worldz = creator.createWorld();
			p.teleport(worldz.getSpawnLocation());
			}else{ 
			} 
		try{
				createWorld(world);
			}catch(Exception e){e.printStackTrace();}
			*/
		p.teleport(worldz.getSpawnLocation());
		}catch(Exception e){e.printStackTrace();brodcast(ChatColor.RED + "Couldnt Change World For " + p.getName().toString());}
	}
	

	public static void brodcast(String msg){
		Bukkit.getServer().broadcastMessage(msg);
	}
	
	
	public static void createWorld(String name){
		@SuppressWarnings("unused")
		World worldz = Bukkit.createWorld(new WorldCreator(name).environment(World.Environment.NORMAL));
	}
	
	

	
	

	public static String ConfigStuff(String varable, String msg){
		if (getCustomConfig().getString(varable) == null) {
			getCustomConfig().set(varable, msg);
			saveCustomConfig();
			return msg;
		}else
			return getCustomConfig().getString(varable);
	}
	
	private static FileConfiguration data = null;
	private static File dataFile = null;
	
	// Reload custom config.
	private static void reloadCustomConfig() {
	     if (dataFile == null) {
	     dataFile = new File(Main.dataFolder, "kills.yml");
	     }
	     data = YamlConfiguration.loadConfiguration(dataFile);
	  
	     // Look for defaults in the jar
	     InputStream defConfigStream = Main.killstream;
	     if (defConfigStream != null) {
	         YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	         data.setDefaults(defConfig);
	     }
	 }
	// Get custom config.
	static FileConfiguration getCustomConfig() {
	  if (data == null) {
	   reloadCustomConfig();
	  }
	  return data;
	 }
	// Save custom config.
	static void saveCustomConfig() {
	     if (data == null || dataFile == null) {
	         return;
	     }
	     try {
	         getCustomConfig().save(dataFile);
	         System.out.println("DataFile: " + dataFile);
	     } catch (Exception e) {}
	}
	
	public static String[] Kills;
	
	public static void loadkilldata(){
		try{
			reloadCustomConfig();
			Kills = getCustomConfig().getString("kills").split("!");
			saveCustomConfig();
		}catch(Exception e){brodcast("ERROR LOADING USERNAMES");}
	}
	
	public static boolean addKiller(String username){
		saveCustomConfig();
		reloadCustomConfig();
		try{
			if(getCustomConfig().getString("kills")!= null && !getCustomConfig().getString("kills").contains(username + ":")){
				getCustomConfig().set("kills", getCustomConfig().getString("kills")+ username+":0!");
				Utilities.brodcast(username+" Was Added To The Roster");
			}else if (!getCustomConfig().getString("kills").contains(username)&& getCustomConfig().getString("kills")!= null ){
				ConfigStuff("kills", username+":0!");
	    		Utilities.brodcast(username+" Was Added To The Roster");
			}
			saveCustomConfig();
			reloadCustomConfig();
			loadkilldata();
			return true;
		}catch(Exception e){saveCustomConfig();brodcast("ERROR SAVING USERNAME"); return false;}
		
	}
	public static boolean Exist(String username){
		reloadCustomConfig();
		loadkilldata();
		if(getCustomConfig().getString("kills").contains(username + ":")){
			
			return true;
		}else{
			brodcast("The User Isn't On The Roster");
			//if(addKiller(username)){
			brodcast("But Now They Are");
			loadkilldata();
			return false;
			//}else{
				//loadkilldata();
				//return false;
			//}
			
		}	
	}
	
	public static String fullusername(String name){
		try{
			@SuppressWarnings("unused")
			String temp = "";
			for(String temp1: Kills){
				try{
					String[] temp2 = temp1.split(":");
					temp+=temp2[0]+" ";
				}catch(Exception e){}
			}
		}catch(Exception e){}
		return name;
	}
	public static String part2(String names, String name){
		try{
			String[] temp = names.split(" ");
			for(String temp1: temp){
				if(temp1.equals(name)){
					
				}else
				{
					return name;
				}
			}
		}catch(Exception e){}
		return "a";
	}
	
	public static int getKills(String username){
		if(Exist(username)){
			@SuppressWarnings("unused")
			String temp = "";
			for(String temp1: Kills){
				if(temp1.contains(username)){
					String[] temp2 = temp1.split(":");
					int kills = (int) Double.parseDouble(temp2[1]);
					return kills;
				}
			}
			return 0;
		}else{
			return 0;
		}
	}
	
	public static void killmgr(String username, Boolean hol){
		saveCustomConfig();
		reloadCustomConfig();
		if(Exist(username)){
			String temp = "";
			for(String temp1: Kills){
				if(temp1.contains(username)){
					String[] temp2 = temp1.split(":");
					int kills = (int) Double.parseDouble(temp2[1]);
					if(hol){
						kills++;
					}else{
						kills--;
					}
					temp+= temp2[0]+":"+kills+"!";
					System.out.println(temp2[0]+":"+kills);
					System.out.println(temp2[0]+":"+kills+"!");
				}else{
				System.out.println("adding: " + temp1.toString());
				temp+=temp1.toString()+"!";
				}
			}
			getCustomConfig().set("kills", temp);
			saveCustomConfig();
			reloadCustomConfig();
			brodcast("Gave "+ username + " a killz");
			loadkilldata();
		}else{
			brodcast("Couldn't give the user Any Kills");
		}
	}
	
	
	
	
	
	
	
	
	public void a(){
		Player p = Bukkit.getPlayer("a");
		p.getLocation().toString();
	}
	
	public static void t2rp(String playername){
		t2rp(Bukkit.getServer().getPlayer(playername));
	}
	public static void t2rp(Player p){
		try{
			int randomziethus = Bukkit.getOnlinePlayers().length;
			Random r = new Random();
			int picker = r.nextInt(randomziethus)+1;
			for(Player player: Bukkit.getOnlinePlayers()){
				
			}
		}catch(Exception e){}
	}
	
	public static void setFly(Player p, boolean settings){
		if(settings){
			p.setFlying(true);
		}else{
			p.setFlying(false);
		}
	}
	
	public static void randomTeleport(Player p){
		Random r = new Random();
		double x = r.nextInt(10000) + 1;
		if(x > 5000){
			x = (x/2)*-1;
		}
		double y = r.nextInt(10000) + 1;
		if(y > 5000){
			y = (y/2)*-1;
		}
		setPlayerLocation(x, a2ftl(x,y,p), y, p);
	}
	public static void randomTeleport(Player p, double y){
		Random r = new Random();
		double x = r.nextInt(10000) + 1;
		if(x > 5000){
			x = (x/2)*-1;
		}
		double z = r.nextInt(10000) + 1;
		if(z > 5000){
			z = (y/2)*-1;
		}
		setPlayerLocation(x, y, z, p);
	}
	
	public static void setPlayerLocation(final double x, final double y, final double z, final Player p){
		try{
				final Main m = new Main();
				System.out.println("Attmepting to transport them");
				
		    			 brodcast("Teeehh");
		    			 Location location = new Location(p.getWorld(), x, y, z);
		 	             p.teleport(location);
		 	             if(location.getBlock().isEmpty()){
		 	            	 brodcast("Teleporting into the air!");
		 	             }
		    		 
				
	            
			
		}catch(Exception e){}
	}
	public static void setPlayerLocation(double x, double y, double z, String name){
		setPlayerLocation(x,y,z,Bukkit.getServer().getPlayer(name));
	}
	
	public static int a2ftl(double x, double z, Player p){
		try{
			int counter = 200;
			while(true){
				Location l = new Location(p.getWorld(), x, counter, z);
			
				Block block = p.getWorld().getBlockAt(l);
				if(!block.isEmpty()){
					System.out.println("Found Floor");
					counter++;
					return counter++;
				}
				if(counter <= 30){
					System.out.println("Can't Find Floor");
					return 80;
				}
				counter--;
			}
		}catch(Exception e){}
		return 1;
	}
	
	public static void radiusfinder(double x, double z, String name){radiusfinder(x,z,Bukkit.getServer().getPlayer(name));}
	
	public static void radiusfinder(double x, double z, Player name){
		int[] a = new int[3];
		int distance = getDistance();
		int angle = angle();
		setPlayerLocation(randomX(distance, angle, x),250,randomZ(distance, angle, z), name);	
	}
	
	public static int getDistance(){
		try{
			Random random = new Random();
			return random.nextInt(200)+301;
		}catch(Exception e){System.out.println("Error Setting Distance... Setting To 500"); return 500;}
	}
	public static int angle(){
		Random random = new Random();
		int temp = random.nextInt(360)+1;
		return temp;
	}
	public static double randomZ(int Distance, int Angle, double center){
		return center + Math.sin(Angle)*Distance;
	}
	
	public static double randomX(int Distance, int Angle, double center){
		return center + Math.cos(Angle)*Distance;
	}
	
	
	
	
	
	
	
}
