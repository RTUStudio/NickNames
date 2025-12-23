package kr.rtustudio.nicknames.configuration;

import kr.rtustudio.framework.bukkit.api.configuration.ConfigurationPart;
import lombok.Getter;

@Getter
@SuppressWarnings({
        "FieldCanBeLocal",
        "FieldMayBeFinal",
        "InnerClassMayBeStatic"
})
public class NameConfig extends ConfigurationPart {

    private String allowedRegex = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9_]*$";
    private int maxLength = 15;

}
