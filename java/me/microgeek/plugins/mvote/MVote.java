package me.microgeek.plugins.mvote;

import java.util.List;
import java.util.Map.Entry;

import me.microgeek.plugins.mvote.util.config.ConfigWrapper;
import me.microgeek.plugins.mvote.vote.VoteHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MVote extends JavaPlugin{

	@Override
	public void onEnable() {
		Wrapper.instance = this;
		Bukkit.getPluginManager().registerEvents(new VoteHandler(), this);
		ConfigWrapper.VOTE_SCRIPT.setSave(false);
	}
	
	@Override
	public void onDisable() {
		for(Entry<Integer, List<String>> e : Wrapper.EXPIRY_QUEUE.entrySet()) {
			for(String s : e.getValue()) {
				Bukkit.dispatchCommand(Wrapper.instance.getServer().getConsoleSender(), s);
			}
		}
	}
		
}
