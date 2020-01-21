package com.github.thinkverse.notify.utilities.enums;

import com.github.thinkverse.notify.utilities.ChatUtil;
import org.bukkit.entity.Player;

public enum MessageType {

  CHAT {
    @Override
    public void send(final Player player, String... string) {
      ChatUtil.message(player, string);
    }
  },
  ACTIONBAR {
    @Override
    public void send(final Player player, String... string) {
      ChatUtil.actionbar(player, string);
    }
  },
  SUBTITLE {
    @Override
    public void send(final Player player, String... string) {
      ChatUtil.subtitle(player, string);
    }
  };

  public abstract void send(Player player, String... message);
}
