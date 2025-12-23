package kr.rtustudio.nicknames.command;

import kr.rtustudio.framework.bukkit.api.command.RSCommand;
import kr.rtustudio.framework.bukkit.api.command.RSCommandData;
import kr.rtustudio.nicknames.NickNames;
import kr.rtustudio.nicknames.player.PlayerName;
import kr.rtustudio.nicknames.player.PlayerNameManager;
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
    public Result execute(RSCommandData data) {
        if (data.length(2)) {
            if (player() == null) return Result.ONLY_PLAYER;

            if (hasPermission(getPlugin().getName() + ".nickname.change")) {
                PlayerName playerName = playerNameManager.getPlayer(player().getUniqueId());
                playerName.changeName(player(), data.args(1));
                return Result.SUCCESS;
            }

        } else if (data.length(3) && hasPermission(getPlugin().getName() + ".nickname.change.other")) {
            UUID targetUuid = provider().getUniqueId(data.args(2));
            if (targetUuid == null) return Result.NOT_FOUND_ONLINE_PLAYER;
            Player target = getPlugin().getServer().getPlayer(targetUuid);
            PlayerName playerName = playerNameManager.getPlayer(targetUuid);
            playerName.changeName(player(), target, data.args(1));
            return Result.SUCCESS;
        }
        return Result.FAILURE;
    }

    @Override
    public List<String> tabComplete(RSCommandData data) {
        if (data.length(3) && hasPermission(getPlugin().getName() + ".nickname.change.other")) {
            return provider().names();
        }
        return List.of();
    }

}
