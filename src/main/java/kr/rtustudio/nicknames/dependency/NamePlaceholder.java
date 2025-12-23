package kr.rtustudio.nicknames.dependency;

import kr.rtustudio.framework.bukkit.api.integration.wrapper.PlaceholderWrapper;
import kr.rtustudio.nicknames.NickNames;
import kr.rtustudio.nicknames.player.PlayerName;
import kr.rtustudio.nicknames.player.PlayerNameManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class NamePlaceholder extends PlaceholderWrapper<NickNames> {

    private final PlayerNameManager pnm;

    public NamePlaceholder(NickNames plugin) {
        super(plugin);
        this.pnm = plugin.getPlayerNameManager();
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String[] params) {
        switch (params[0]) {
            case "display" -> {
                PlayerName pn = pnm.getPlayer(offlinePlayer.getUniqueId());
                if (pn != null) {
                    String name = pn.getName();
                    if (name != null && !name.isEmpty()) return name;
                }
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
