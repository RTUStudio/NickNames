package com.github.nyaon08.rtustudio.nicknames.nick;

import com.github.nyaon08.rtustudio.nicknames.NickNames;

import java.util.Collections;
import java.util.List;

public class NickUtil {

    public static Result check(List<String> list, String name) {
        if (!name.matches("^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9_]*$")) return Result.CONTAINS;
        if (name.length() > NickNames.getInstance().getNameConfig().getMaxLength()) return Result.LENGTH;
        if (Collections.frequency(list, name) >= 1) return Result.DUPLICATED;
        return Result.SUCCESS;
    }

    public enum Result {
        /**
         * 이름에 잘못된 문자를 포함
         */
        CONTAINS,
        /**
         * 이름의 길이가 최대 길이보다 초과
         */
        LENGTH,
        /**
         * 이미 존재하는 이름
         */
        DUPLICATED,
        /**
         * 문제 없음
         */
        SUCCESS
    }
}
