package com.github.nyaon08.rtustudio.nicknames.provider;

import com.github.nyaon08.rtustudio.nicknames.NickNames;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerName;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerNameManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class NameProvider implements kr.rtuserver.framework.bukkit.api.core.provider.name.NameProvider {

    private final NickNames plugin;
    private final PlayerNameManager pnm;

    public NameProvider(NickNames plugin) {
        this.plugin = plugin;
        this.pnm = plugin.getPlayerNameManager();
    }

    public @NotNull List<String> names(Scope scope) {
        List<String> result = new ArrayList<>();
        for (PlayerName player : pnm.getPlayers()) {
            if (scope == Scope.CURRENT_SERVER) {
                if (player.getPlayer() == null) continue;
            }
            result.add("@" + player.getName());
            result.add(player.getPlayer().getName());
        }
        return result;
    }


    public String getName(UUID uniqueId) {
        PlayerName pn = pnm.getPlayer(uniqueId);
        return pn == null ? null : pn.getName();
    }

    public UUID getUniqueId(String name) {
        PlayerName pn;
        if (name.startsWith("@")) pn = pnm.getPlayer(name.substring(1));
        else {
            Player player = Bukkit.getPlayer(name);
            if (player == null) return null;
            pn = pnm.getPlayer(player.getUniqueId());
        }
        return pn == null ? null : pn.getPlayer().getUniqueId();
    }

}