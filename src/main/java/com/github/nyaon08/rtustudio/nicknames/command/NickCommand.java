package com.github.nyaon08.rtustudio.nicknames.command;

import com.github.nyaon08.rtustudio.nicknames.NickNames;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerName;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerNameManager;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;
import kr.rtuserver.framework.bukkit.api.configuration.translation.message.MessageTranslation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class NickCommand extends RSCommand<NickNames> {

    private final PlayerNameManager playerNameManager;

    public NickCommand(NickNames plugin) {
        super(plugin, "change");
        this.playerNameManager = plugin.getPlayerNameManager();
    }

    @Override
    public boolean execute(RSCommandData data) {
        if (data.length(2)) {
            if (player() == null) {
                chat().announce(message().getCommon(sender(), String.valueOf(MessageTranslation.ONLY_PLAYER)));
                return true;
            }

            if (hasPermission(getPlugin().getName() + ".nickname.change")) {
                PlayerName playerName = playerNameManager.getPlayer(player().getUniqueId());
                System.out.println(data.args(1));
                playerName.changeName(player(), data.args(1));
                return true;
            }

        } else if (data.length(3) && hasPermission(getPlugin().getName() + ".nickname.change.other")) {
            UUID targetUuid = provider().getUniqueId(data.args(2));
            if (targetUuid == null) {
                return true;
            }
            Player target = getPlugin().getServer().getPlayer(targetUuid);
            PlayerName playerName = playerNameManager.getPlayer(targetUuid);
            playerName.changeName(player(), target, data.args(1));
            return true;
        }
        return false;
    }

    @Override
    public List<String> tabComplete(RSCommandData data) {
        if (data.length(3) && hasPermission(getPlugin().getName() + ".nickname.change.other")) {
            return provider().names();
        }
        return List.of();
    }

}
