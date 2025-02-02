package ru.Fronzter;

import org.bukkit.plugin.java.JavaPlugin;
import ru.Fronzter.command.SoundCommand;
import ru.Fronzter.listener.DamageListener;
import ru.Fronzter.listener.GUIListener;
import ru.Fronzter.manager.SoundManager;

public class FrSound extends JavaPlugin {
    private SoundManager soundManager;

    @Override
    public void onEnable() {
        soundManager = new SoundManager();
        getCommand("FrSound").setExecutor(new SoundCommand(this, soundManager));
        getServer().getPluginManager().registerEvents(new GUIListener(soundManager), this);
        getServer().getPluginManager().registerEvents(new DamageListener(soundManager), this);
    }

    @Override
    public void onDisable() {
    }
}
