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
        PlayerManager playerManager = plugin.getPlayerManager();
        if (event.getFrom().getWorld() == null || event.getTo().getWorld() == null) return;


        if (event.getFrom().getWorld() != event.getTo().getWorld() &&
                event.getFrom().getWorld().getEnvironment() == event.getTo().getWorld().getEnvironment()) {
            playerManager.addChangingWorldPlayer(event.getPlayer());
        }
        plugin.getTaskScheduler().runTaskLater(() -> playerManager.removeChangingWorldPlayer(event.getPlayer()), 2);
    }

    @Override
    public void onPacketPlaySend(PacketPlaySendEvent event) {
        if (event.getPacketType() != PacketType.Play.Server.RESPAWN) {
            return;
        }
        if (!(event.getPlayer() instanceof Player player)) return;

        if (plugin.getPlayerManager().isPlayerChangingWorlds(player)) {
            event.setCancelled(true);
        }
    }
}
