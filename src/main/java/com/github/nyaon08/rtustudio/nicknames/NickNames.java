package com.github.nyaon08.rtustudio.nicknames;

import com.github.nyaon08.rtustudio.nicknames.command.MainCommand;
import com.github.nyaon08.rtustudio.nicknames.configuration.NameConfig;
import com.github.nyaon08.rtustudio.nicknames.dependency.PlaceholderAPI;
import com.github.nyaon08.rtustudio.nicknames.listeners.PlayerJoinQuit;
import com.github.nyaon08.rtustudio.nicknames.manager.NickNamesManager;
import com.github.nyaon08.rtustudio.nicknames.player.PlayerNameManager;
import com.github.nyaon08.rtustudio.nicknames.provider.NameProvider;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import lombok.Getter;
import org.bukkit.permissions.PermissionDefault;

public class NickNames extends RSPlugin {

    @Getter
    private static NickNames instance;

    @Getter
    private NickNamesManager nickNamesManager;
    @Getter
    private PlayerNameManager playerNameManager;
    @Getter
    private NameConfig nameConfig;
    @Getter
    private NameProvider nameProvider;

    private PlaceholderAPI placeholder;

    @Override
    public void load() {
        instance = this;
    }

    @Override
    public void enable() {
        getConfigurations().getStorage().init("Nickname");

        nameConfig = new NameConfig(this);
        nickNamesManager = new NickNamesManager(this);
        playerNameManager = new PlayerNameManager(this);

        nameProvider = new NameProvider(this);
        getFramework().getProviders().setName(nameProvider);

        registerPermission(getPlugin().getName() + ".nickname.change", PermissionDefault.TRUE);
        registerPermission(getPlugin().getName() + ".nickname.change.other", PermissionDefault.OP);

        registerCommand(new MainCommand(this), true);

        registerEvent(new PlayerJoinQuit(this));

        if (getFramework().isEnabledDependency("PlaceholderAPI")) {
            placeholder = new PlaceholderAPI(this);
            placeholder.register();
        }
    }

    @Override
    public void disable() {
        if (getFramework().isEnabledDependency("PlaceholderAPI")) {
            placeholder.unregister();
        }
    }

}
