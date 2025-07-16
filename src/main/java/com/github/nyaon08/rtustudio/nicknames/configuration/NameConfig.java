package com.github.nyaon08.rtustudio.nicknames.configuration;

import com.github.nyaon08.rtustudio.nicknames.NickNames;
import kr.rtuserver.framework.bukkit.api.configuration.RSConfiguration;
import lombok.Getter;

public class NameConfig extends RSConfiguration<NickNames> {

    @Getter
    private int maxLength = 10;

    public NameConfig(NickNames plugin) {
        super(plugin, "Name.yml", 1);
        setup(this);
    }

    private void init() {
        maxLength = getInt("maxLength", maxLength);
    }

}
