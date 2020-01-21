package com.github.thinkverse.notify.utilities.enums;

public final class EnumUtil {
  public static <E extends Enum<E>> boolean isValidEnumIgnoreCase(final Class<E> clazz, final String name) {
    return getEnumIgnoreCase(clazz, name) != null;
  }

  public static <E extends Enum<E>> E getEnumIgnoreCase(final Class<E> clazz, final String name) {
    if (name == null || !clazz.isEnum()) return null;
    for (final E constant : clazz.getEnumConstants()) {
      if (constant.name().equalsIgnoreCase(name)) return constant;
    }
    return null;
  }
}
