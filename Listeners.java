package Apples;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class Listeners implements Listener{
	
	private Main plugin;
	
	public Listeners(Main plugin) {
			
		this.plugin = plugin;
	}
	
	
	
	
	@EventHandler (priority = EventPriority.HIGHEST) 
	public void onJoin(final PlayerLoginEvent e){
		try{
			System.out.println("Stuff");
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			     @Override
			     public void run() {
			    	 if(!e.getPlayer().hasPlayedBefore()) {
			    		 e.getPlayer().sendMessage("Welcome to the Server!");
				    	 try{
				    		 Utilities.addKiller(e.getPlayer().getName());
				    		 Utilities.brodcast("X: "+e.getPlayer().getLocation().getX()+"\nY: "+e.getPlayer().getLocation().getY());
				    		 
				    	 }catch(Exception e){}
			    	 }		 
			    		
		           	 new fuckyou(e).start();
		           	 
		           	 
			     }
			    }, 1L);
			System.out.println(e.getPlayer().getName().toString());
		}catch(Exception ex){System.out.println("ISSUES");}
	}
	
	public static class fuckyou extends Thread{
		PlayerLoginEvent e;
		public fuckyou(PlayerLoginEvent e){
			this.e = e;
		}
        public void run(){
			 Utilities.randomTeleport(e.getPlayer(),200);
        }
	}
	
	
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent e){
		try{
			if(e.getEntity().getPlayer().getWorld().toString().equals("CraftWorld{name=world}")){
				if(e.getEntity().getKiller()!=null){
					
					Utilities.brodcast(e.getEntity().getPlayer().getName().toString() + " was killed by " + e.getEntity().getKiller().toString());
					try{
						Utilities.killmgr(e.getEntity().getKiller().getName().toString(), true);
						Utilities.brodcast(e.getEntity().getKiller().getName().toString() + " now has " + Utilities.getKills(e.getEntity().getKiller().getName().toString()) + " kills!");
					}catch(Exception error){Utilities.brodcast("ERROR");}
				}
				e.setDeathMessage(e.getEntity().getPlayer().getName().toString()+" died with "+Utilities.getKills(e.getEntity().getPlayer().getName().toString())+" kills");
			}
		}catch(Exception ex){}
		
	}
	
	
	

	
}
