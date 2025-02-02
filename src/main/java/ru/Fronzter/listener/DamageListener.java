package ru.Fronzter.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.Fronzter.manager.SoundManager;

import java.util.Set;

public class DamageListener implements Listener {
    private final SoundManager soundManager;

    public DamageListener(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player attacker = (Player) event.getDamager();
        Set<Sound> sounds = soundManager.getPlayerSounds(attacker.getUniqueId());
        if (sounds.isEmpty()) return;
        for (Sound sound : sounds) {
            attacker.playSound(attacker.getLocation(), sound, 1.0f, 1.0f);
        }
    }
}
