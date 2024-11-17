package dev.unnm3d.loadingscreenremover;

import com.github.Anon8281.universalScheduler.UniversalScheduler;
import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;


@Getter
public final class LoadingScreenRemover extends JavaPlugin {

    private PlayerManager playerManager;
    private TaskScheduler taskScheduler;


    @Override
    public void onEnable() {
        this.playerManager = new PlayerManager();
        this.taskScheduler = UniversalScheduler.getScheduler(this);

        final PlayerListener playerListener = new PlayerListener(this);

        getServer().getPluginManager().registerEvents(playerListener, this);

        PacketEvents.getAPI().getEventManager().registerListeners(playerListener);
        new Metrics(this, 20950);
    }

}
