package com.github.nyaon08.rtustudio.nicknames.command;

import com.github.nyaon08.rtustudio.nicknames.NickNames;
import com.github.nyaon08.rtustudio.nicknames.configuration.NameConfig;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;

public class MainCommand extends RSCommand<NickNames> {

    private final NameConfig nameConfig;

    public MainCommand(NickNames plugin) {
        super(plugin, "nickname");
        this.nameConfig = plugin.getNameConfig();
        registerCommand(new NickCommand(plugin));
    }

    @Override
    public boolean execute(RSCommandData data) {
        return true;
    }

    @Override
    public void reload(RSCommandData data) {
        nameConfig.reload();
    }

}
