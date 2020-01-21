package com.github.thinkverse.notify.utilities;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public final class ChatUtil {
  public static void message(CommandSender sender, String... message) {
    Arrays.stream(message).map(ChatUtil::translate).forEach(sender::sendMessage);
  }

  public static void actionbar(final Player player, String... message) {
    Arrays.stream(message).map(ChatUtil::translate).forEach(translated -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(translated).create()));
  }

  public static void subtitle(final Player player, String... message) {
    Arrays.stream(message).map(ChatUtil::translate).forEach(translated -> player.sendTitle("", translated, 10, 40, 10));
  }

  public static String translate(String message) { return ChatColor.translateAlternateColorCodes('&', message); }
}
