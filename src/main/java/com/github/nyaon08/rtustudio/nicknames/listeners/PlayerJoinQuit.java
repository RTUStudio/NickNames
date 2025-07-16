package com.github.nyaon08.rtustudio.nicknames.listeners;

import com.github.nyaon08.rtustudio.nicknames.NickNames;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerNameManager;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuit extends RSListener<NickNames> {

    private final PlayerNameManager pnm;

    public PlayerJoinQuit(NickNames plugin) {
        super(plugin);
        this.pnm = plugin.getPlayerNameManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
       pnm.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        pnm.removePlayer(e.getPlayer());
    }

}
