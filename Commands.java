package Apples;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		System.out.println("@onCommand");
		if(cmd.getName().equalsIgnoreCase("tpw")){ 
			try{
				Utilities.teleport2World(Bukkit.getPlayer(args[0]),args[1]);
			}catch(Exception e){}
			return true;
		} 
		else if(cmd.getName().equalsIgnoreCase("givekill")){ 
			try{
				System.out.println("Attempting to give a kill");
				Utilities.killmgr(args[0], true);
				
				System.out.println("Attempting Over");
			}catch(Exception e){e.printStackTrace();}
			return true;
		} else if(cmd.getName().equalsIgnoreCase("tpp")){ 
			try{
				System.out.println("Attempting to teleport");
				Utilities.setPlayerLocation(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), args[0].toString());
				
				System.out.println("Attempting Over");
			}catch(Exception e){e.printStackTrace();}
			return true;
		} else if(cmd.getName().equalsIgnoreCase("cw")){ 
			try{
				System.out.println("Attempting to Create World!");
				Utilities.createWorld(args[0]);
				
				System.out.println("Attempting Over");
			}catch(Exception e){e.printStackTrace();}
			return true;
		} else if(cmd.getName().equalsIgnoreCase("removekill")){ 
			try{
				System.out.println("Attempting to remove a kill");
				Utilities.killmgr(args[0], false);
				System.out.println("Attempting Over");
			}catch(Exception e){e.printStackTrace();}
			return true;
		} else if(cmd.getName().equalsIgnoreCase("listkills")){ 
			try{
				System.out.println("Attempting to load kill");
				Utilities.loadkilldata();
				System.out.println("Attempting Over");
			}catch(Exception e){e.printStackTrace();}
			return true;
		} else if(cmd.getName().equalsIgnoreCase("addkiller")){ 
			try{
				System.out.println("Attempting to add " + args[0] + " to the roster");
				Utilities.addKiller(args[0]);
				System.out.println("Attempting Over");
			}catch(Exception e){e.printStackTrace();}
			return true;
		} else if(cmd.getName().equalsIgnoreCase("kexist")){ 
				try{
					Utilities.Exist(args[0]);
				}catch(Exception e){e.printStackTrace();}
				return true;
			} else
			
		return false;
			
	}
}
