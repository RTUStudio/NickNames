package com.github.nyaon08.rtustudio.nicknames.player;

import com.github.nyaon08.rtustudio.nicknames.NickNames;
import com.github.nyaon08.rtustudio.nicknames.manager.NickNamesManager;
import com.github.nyaon08.rtustudio.nicknames.nick.NickUtil;
import kr.rtuserver.framework.bukkit.api.configuration.translation.TranslationConfiguration;
import kr.rtuserver.framework.bukkit.api.format.ComponentFormatter;
import kr.rtuserver.framework.bukkit.api.player.PlayerChat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public class PlayerName {

    private final PlayerChat chat;
    private final NickNamesManager nnm;

    private final NickNames plugin;

    private final Player player;
    private final TranslationConfiguration message;

    public PlayerName(NickNames plugin, Player player) {
        this.plugin = plugin;
        this.nnm = plugin.getNickNamesManager();
        this.message = plugin.getConfigurations().getMessage();
        this.chat = PlayerChat.of(plugin);
        this.player = player;
        this.name = nnm.getName(player.getUniqueId());
        if (name != null && name.isEmpty()) return;
        this.player.setDisplayName(this.name);
    }

    private String name;

    public void setName(String name) {
        this.name = name;
        player.setDisplayName(name);
        nnm.setName(player.getUniqueId(), name);
    }

    public void changeName(Player player, String name) {
        changeName(null, player, name);
    }

    public void changeName(CommandSender operator, Player player, String name) {
        final String previous = this.name;

        CommandSender sender = operator != null ? operator : player;
        if (name.equals("잘못된닉네임")) {
            chat.announce(sender, ComponentFormatter.mini(message.get(sender, "nickname.change.unavailable")));
            return;
        }
        switch (NickUtil.check(nnm.getNamesFromDB(), name)) {
            case CONTAINS ->
                    chat.announce(sender, ComponentFormatter.mini(message.get(sender, "nickname.change.contains")));
            case LENGTH ->
                    chat.announce(sender, ComponentFormatter.mini(message.get(sender, "nickname.change.length").replace("[mex_length]", String.valueOf(plugin.getNameConfig().getMaxLength()))));
            case DUPLICATED ->
                    chat.announce(sender, ComponentFormatter.mini(message.get(sender, "nickname.change.duplicated")));
            case SUCCESS -> {
                setName(name);
                chat.announce(sender, ComponentFormatter.mini(message.get(sender, operator != null ? "nickname.change.other" : "nickname.change.success").replace("[previous]", previous).replace("[name]", name)));
            }
        }
    }
}
