package kr.rtustudio.nicknames.manager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kr.rtustudio.framework.bukkit.api.platform.JSON;
import kr.rtustudio.framework.bukkit.api.storage.Storage;
import kr.rtustudio.nicknames.NickNames;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class NickNamesManager {

    private final NickNames plugin;

    private final HashMap<UUID, String> cache = new HashMap<>();

    public void setName(UUID uuid, String name) {
        Storage storage = plugin.getStorage();
        storage.get("Nickname", JSON.of("uuid", uuid.toString())).thenAccept(result -> {
            cache.put(uuid, name);
            if (result == null || result.isEmpty()) {
                storage.add("Nickname", JSON.of("uuid", uuid.toString()).append("name", name));
            } else storage.set("Nickname", JSON.of("uuid", uuid.toString()), JSON.of("name", name));
        });
    }

    @Nullable
    public String getName(UUID uuid) {
        if (cache.containsKey(uuid)) return cache.get(uuid);
        Storage storage = plugin.getStorage();
        CompletableFuture<List<JsonObject>> result = storage.get("Nickname", JSON.of("uuid", uuid.toString()));
        if (result == null) return "";
        List<JsonObject> list = result.join();
        if (list == null || list.isEmpty()) return "";
        JsonElement name = list.getFirst().get("name");
        if (name == null) return "";
        cache.put(uuid, name.getAsString());
        return name.getAsString();
    }

    public List<String> getNamesFromDB() {
        return cache.values().stream().toList();
    }

}
