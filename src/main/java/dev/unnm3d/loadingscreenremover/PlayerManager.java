package dev.unnm3d.loadingscreenremover;

import org.bukkit.entity.Player;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {
    private final Set<Player> changingWorlds = ConcurrentHashMap.newKeySet();

    public boolean isPlayerChangingWorlds(Player player) {
        return changingWorlds.contains(player);
    }

    public void addChangingWorldPlayer(Player player) {
        changingWorlds.add(player);
    }

    public void removeChangingWorldPlayer(Player player) {
        changingWorlds.remove(player);
    }
}
