package com.github.nyaon08.rtustudio.nicknames.dependency;

import com.github.nyaon08.rtustudio.nicknames.NickNames;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerNameManager;
import kr.rtuserver.framework.bukkit.api.integration.RSPlaceholder;
import org.bukkit.OfflinePlayer;

public class PlaceholderAPI extends RSPlaceholder<NickNames> {

    private final PlayerNameManager pnm;

    public PlaceholderAPI(NickNames plugin) {
        super(plugin);
        this.pnm = plugin.getPlayerNameManager();
    }

    @Override
    public String request(OfflinePlayer offlinePlayer, String[] params) {
        return pnm.getPlayer(offlinePlayer.getUniqueId()).getName();
    }

}
