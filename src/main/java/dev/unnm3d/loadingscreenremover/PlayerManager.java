package dev.unnm3d.loadingscreenremover;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {
    private final Set<Player> changingWorlds = ConcurrentHashMap.newKeySet();

    public boolean isPlayerChangingWorlds(@NotNull Player player) {
        return changingWorlds.contains(player);
    }

    public void addChangingWorldPlayer(@NotNull Player player) {
        changingWorlds.add(player);
    }

    public void removeChangingWorldPlayer(@NotNull Player player) {
        changingWorlds.remove(player);
    }
}
