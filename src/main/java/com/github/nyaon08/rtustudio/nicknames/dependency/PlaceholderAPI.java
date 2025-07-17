package com.github.nyaon08.rtustudio.nicknames.dependency;

import com.github.nyaon08.rtustudio.nicknames.NickNames;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerName;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerNameManager;
import kr.rtuserver.framework.bukkit.api.integration.RSPlaceholder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends RSPlaceholder<NickNames> {

    private final PlayerNameManager pnm;

    public PlaceholderAPI(NickNames plugin) {
        super(plugin);
        this.pnm = plugin.getPlayerNameManager();
    }

    @Override
    public String request(OfflinePlayer offlinePlayer, String[] params) {
        switch (params[0]) {
            case "display" -> {
                PlayerName pn = pnm.getPlayer(offlinePlayer.getUniqueId());
                if (pn != null) return pn.getName();
                if (offlinePlayer.isOnline()) {
                    Player player = offlinePlayer.getPlayer();
                    if (player.getDisplayName().isEmpty()) return player.getName();
                    return player.getDisplayName();
                } else return offlinePlayer.getName();
            }
        }
        return pnm.getPlayer(offlinePlayer.getUniqueId()).getName();
    }

}
