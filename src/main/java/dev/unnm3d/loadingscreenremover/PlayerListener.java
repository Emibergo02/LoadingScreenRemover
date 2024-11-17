package dev.unnm3d.loadingscreenremover;

import com.github.retrooper.packetevents.event.SimplePacketListenerAbstract;
import com.github.retrooper.packetevents.event.simple.PacketPlaySendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

@AllArgsConstructor
public class PlayerListener extends SimplePacketListenerAbstract implements Listener {
    private final LoadingScreenRemover plugin;

    @EventHandler
    public void onPlayerTeleportWorld(PlayerTeleportEvent event) {
        if (event.getFrom().getWorld() == null || event.getTo().getWorld() == null) return;

        // If the player is changing worlds, and the environments are the same
        if (event.getFrom().getWorld() != event.getTo().getWorld() &&
                event.getFrom().getWorld().getEnvironment() == event.getTo().getWorld().getEnvironment()) {
            plugin.getPlayerManager().addChangingWorldPlayer(event.getPlayer());
        }else return;
        plugin.getTaskScheduler().runTaskLater(() -> plugin.getPlayerManager().removeChangingWorldPlayer(event.getPlayer()), 2);
    }

    @Override
    public void onPacketPlaySend(PacketPlaySendEvent event) {
        if (event.getPacketType() != PacketType.Play.Server.RESPAWN) {
            return;
        }

        if (plugin.getPlayerManager().isPlayerChangingWorlds(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
