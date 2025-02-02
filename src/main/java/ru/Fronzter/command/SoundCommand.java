package ru.Fronzter.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.Fronzter.FrSound;
import ru.Fronzter.manager.SoundManager;
import ru.Fronzter.gui.SoundGUI;

public class SoundCommand implements CommandExecutor {
    private final SoundManager soundManager;

    public SoundCommand(FrSound frSound, SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Эта команда доступна только игрокам с правом");
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("frsound.use")) {
            player.sendMessage(ChatColor.RED + "У вас нет прав для использования этой команды");
            return true;
        }

        SoundGUI gui = new SoundGUI(soundManager);
        player.openInventory(gui.getInventory(player, 0));
        return true;
    }
}
