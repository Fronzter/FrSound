package ru.Fronzter.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.Fronzter.manager.SoundManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SoundGUI {
    private final SoundManager soundManager;
    private final String guiTitle = ChatColor.DARK_AQUA + "Выберите звуки";
    private final int pageSize = 45;

    public SoundGUI(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    public Inventory getInventory(Player player, int page) {
        List<Sound> sounds = soundManager.getAvailableSounds();
        Set<Sound> selectedSounds = soundManager.getPlayerSounds(player.getUniqueId());

        int totalPages = (int) Math.ceil((double) sounds.size() / pageSize);
        Inventory gui = Bukkit.createInventory(null, 54, guiTitle + " (" + (page + 1) + "/" + totalPages + ")");

        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, sounds.size());

        for (int i = startIndex; i < endIndex; i++) {
            Sound sound = sounds.get(i);
            ItemStack item = new ItemStack(Material.NOTE_BLOCK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + sound.toString());

            List<String> lore = new ArrayList<>();
            if (selectedSounds.contains(sound)) {
                lore.add(ChatColor.YELLOW + "Выбрано");
            } else {
                lore.add(ChatColor.GRAY + "Не выбрано");
            }

            meta.setLore(lore);
            item.setItemMeta(meta);
            gui.setItem(i - startIndex, item);
        }

        ItemStack previousPage = new ItemStack(Material.ARROW);
        ItemMeta prevMeta = previousPage.getItemMeta();
        prevMeta.setDisplayName(ChatColor.GOLD + "Предыдущая страница");
        previousPage.setItemMeta(prevMeta);

        ItemStack nextPage = new ItemStack(Material.ARROW);
        ItemMeta nextMeta = nextPage.getItemMeta();
        nextMeta.setDisplayName(ChatColor.GOLD + "Следующая страница");
        nextPage.setItemMeta(nextMeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "Закрыть");
        close.setItemMeta(closeMeta);

        if (page > 0) gui.setItem(45, previousPage);
        if (page < totalPages - 1) gui.setItem(53, nextPage);
        gui.setItem(49, close);

        return gui;
    }

    public String getTitle() {
        return guiTitle;
    }
}
