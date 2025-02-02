package ru.Fronzter.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.Fronzter.manager.SoundManager;
import ru.Fronzter.gui.SoundGUI;

import java.util.List;

public class GUIListener implements Listener {
    private final SoundManager soundManager;
    private final SoundGUI soundGUI;

    public GUIListener(SoundManager soundManager) {
        this.soundManager = soundManager;
        this.soundGUI = new SoundGUI(soundManager);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains(soundGUI.getTitle())) return;
        event.setCancelled(true);
        if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta()) return;

        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();
        int currentPage = title.matches(".*\\((\\d+)/(\\d+)\\).*") ? Integer.parseInt(title.replaceAll(".*\\((\\d+)/(\\d+)\\).*", "$1")) - 1 : 0;

        if (event.getCurrentItem().getType() == Material.ARROW) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Предыдущая")) {
                player.openInventory(soundGUI.getInventory(player, currentPage - 1));
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Следующая")) {
                player.openInventory(soundGUI.getInventory(player, currentPage + 1));
            }
            return;
        }

        if (event.getCurrentItem().getType() == Material.BARRIER) {
            player.closeInventory();
            return;
        }

        List<Sound> sounds = soundManager.getAvailableSounds();
        int slot = event.getRawSlot() + (currentPage * 45);
        if (slot < 0 || slot >= sounds.size()) return;

        Sound sound = sounds.get(slot);
        soundManager.toggleSound(player.getUniqueId(), sound);

        if (soundManager.getPlayerSounds(player.getUniqueId()).contains(sound)) {
            player.sendMessage(ChatColor.GREEN + "Звук " + sound.toString() + " включен.");
        } else {
            player.sendMessage(ChatColor.RED + "Звук " + sound.toString() + " отключен.");
        }

        player.openInventory(soundGUI.getInventory(player, currentPage));
    }
}
