package com.github.thinkverse.notify.command;

import com.github.thinkverse.notify.utilities.ChatUtil;
import com.github.thinkverse.notify.utilities.enums.EnumUtil;
import com.github.thinkverse.notify.utilities.enums.MessageType;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public final class NotifyCommand implements CommandExecutor, TabExecutor {

  private boolean notifyPlayer(CommandSender sender, Command command, String label, String[] args) {

    if (args[0].equalsIgnoreCase("*")) {
      Bukkit.getOnlinePlayers().forEach(target -> sendNotification(target, args));
    } else {
      final Player target = Bukkit.getPlayerExact(args[0]);

      if (target == null) {
        ChatUtil.message(sender, String.format("&cPlayer, %s seems to be offline.", args[0]));
      } else {
        sendNotification(target, args);
      }
    }

    return true;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    if (!(args.length < 3) && sender.hasPermission("notify.use")) {
      if (EnumUtil.isValidEnumIgnoreCase(MessageType.class, args[1])) {
        return notifyPlayer(sender, command, label, args);
      }
    }

    return false;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

    if (args.length == 1) {
      return onTabReturn(args[args.length -1 ], getPlayers());
    } else if (args.length == 2) {
      return onTabReturn(args[args.length -1 ], getTypes());
    }

    return Collections.emptyList();
  }

  private void sendNotification(final Player player, String[] args) {
    final MessageType type = EnumUtil.getEnumIgnoreCase(MessageType.class, args[1]);
    type.send(player, getJoinedArgs(Arrays.copyOfRange(args, 2, args.length)));
  }

  private List<String> onTabReturn(String args, List<String> list) {
    return StringUtil.copyPartialMatches(args, list, Lists.newArrayList());
  }

  private String getJoinedArgs(final String[] args) {
    final StringJoiner joiner = new StringJoiner(" ");

    for (final String word : args) {
      joiner.add(word);
    }

    return joiner.toString();
  }

  private List<String> getTypes() {
    final List<String> types = Lists.newArrayList();

    for (final MessageType type : MessageType.values()) {
      types.add(type.name().toLowerCase());
    }

    return types;
  }

  private List<String> getPlayers() {
    final List<String> players = Lists.newArrayList();

    for (final Player player : Bukkit.getOnlinePlayers()) {
      players.add(player.getName());
    }

    players.add("*");
    Collections.sort(players);

    return players;
  }

}
