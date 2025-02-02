package ru.Fronzter.manager;

import org.bukkit.Sound;

import java.util.*;

public class SoundManager {
    private final Map<UUID, Set<Sound>> playerSounds = new HashMap<>();
    private final List<Sound> availableSounds = Arrays.asList(
            Sound.ENTITY_COW_AMBIENT,
            Sound.ENTITY_COW_HURT,
            Sound.ENTITY_COW_DEATH,
            Sound.ENTITY_SHEEP_AMBIENT,
            Sound.ENTITY_SHEEP_HURT,
            Sound.ENTITY_SHEEP_DEATH,
            Sound.ENTITY_PIG_AMBIENT,
            Sound.ENTITY_PIG_HURT,
            Sound.ENTITY_PIG_DEATH,
            Sound.ENTITY_CHICKEN_AMBIENT,
            Sound.ENTITY_CHICKEN_HURT,
            Sound.ENTITY_CHICKEN_DEATH,
            Sound.ENTITY_WOLF_AMBIENT,
            Sound.ENTITY_WOLF_HURT,
            Sound.ENTITY_WOLF_DEATH,
            Sound.ENTITY_WOLF_GROWL,
            Sound.ENTITY_WOLF_WHINE,
            Sound.ENTITY_VILLAGER_AMBIENT,
            Sound.ENTITY_VILLAGER_HURT,
            Sound.ENTITY_VILLAGER_DEATH,
            Sound.ENTITY_VILLAGER_TRADE,
            Sound.ENTITY_IRON_GOLEM_HURT,
            Sound.ENTITY_IRON_GOLEM_DEATH,
            Sound.ENTITY_ZOMBIE_AMBIENT,
            Sound.ENTITY_ZOMBIE_HURT,
            Sound.ENTITY_ZOMBIE_DEATH,
            Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR,
            Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR,
            Sound.ENTITY_CREEPER_PRIMED,
            Sound.ENTITY_CREEPER_HURT,
            Sound.ENTITY_CREEPER_DEATH,
            Sound.ENTITY_SKELETON_AMBIENT,
            Sound.ENTITY_SKELETON_HURT,
            Sound.ENTITY_SKELETON_DEATH,
            Sound.ENTITY_ENDERMAN_AMBIENT,
            Sound.ENTITY_ENDERMAN_HURT,
            Sound.ENTITY_ENDERMAN_DEATH,
            Sound.ENTITY_ENDERMAN_SCREAM,
            Sound.ENTITY_ENDERMAN_STARE,
            Sound.ENTITY_WITHER_AMBIENT,
            Sound.ENTITY_WITHER_HURT,
            Sound.ENTITY_WITHER_DEATH,
            Sound.ENTITY_WITHER_SPAWN,
            Sound.BLOCK_ANVIL_USE,
            Sound.BLOCK_ANVIL_BREAK,
            Sound.BLOCK_ANVIL_LAND,
            Sound.BLOCK_GLASS_BREAK,
            Sound.BLOCK_GRASS_STEP,
            Sound.BLOCK_GRAVEL_STEP,
            Sound.BLOCK_WOOD_STEP,
            Sound.BLOCK_STONE_STEP,
            Sound.BLOCK_LAVA_POP,
            Sound.BLOCK_FIRE_AMBIENT,
            Sound.BLOCK_WATER_AMBIENT,
            Sound.BLOCK_NOTE_BLOCK_BASS,
            Sound.BLOCK_NOTE_BLOCK_HARP,
            Sound.BLOCK_NOTE_BLOCK_PLING,
            Sound.ENTITY_PLAYER_LEVELUP,
            Sound.ENTITY_PLAYER_HURT,
            Sound.ENTITY_PLAYER_DEATH,
            Sound.ENTITY_EXPERIENCE_ORB_PICKUP,
            Sound.ENTITY_ARROW_HIT_PLAYER,
            Sound.ENTITY_FIREWORK_ROCKET_BLAST,
            Sound.ITEM_TOTEM_USE
            // тут вы можете просто звуки добать и все
    );

    public List<Sound> getAvailableSounds() {
        return availableSounds;
    }

    public Set<Sound> getPlayerSounds(UUID playerId) {
        return playerSounds.getOrDefault(playerId, new HashSet<>());
    }

    public void toggleSound(UUID playerId, Sound sound) {
        Set<Sound> sounds = playerSounds.getOrDefault(playerId, new HashSet<>());
        if (sounds.contains(sound)) {
            sounds.remove(sound);
        } else {
            sounds.add(sound);
        }
        playerSounds.put(playerId, sounds);
    }
}
