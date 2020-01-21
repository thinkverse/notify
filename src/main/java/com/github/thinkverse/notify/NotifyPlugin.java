package com.github.thinkverse.notify;

import com.github.thinkverse.notify.command.NotifyCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

import java.util.Objects;

public final class NotifyPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    registerCommand("notify", new NotifyCommand(), true);
  }

  @Override
  public void onDisable() { }

  private void registerCommand(final String command, final CommandExecutor executor, boolean tabCompleter) {
    Objects.requireNonNull(this.getCommand(command)).setExecutor(executor);
    if (tabCompleter) registerTabCompleter(command, (TabCompleter) executor);
  }

  private void registerTabCompleter(final String command, final TabCompleter completer) {
    Objects.requireNonNull(this.getCommand(command)).setTabCompleter(completer);
  }

}
