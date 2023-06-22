package pl.to1maszproblem.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public static String fixColor(String string) {
        Pattern pattern = Pattern.compile("&(#[a-fA-F0-9]{6})");
        for (Matcher matcher = pattern.matcher(string); matcher.find(); matcher = pattern.matcher(string)) {
            String color = string.substring(matcher.start() + 1, matcher.end());
            string = string.replace("&" + color, net.md_5.bungee.api.ChatColor.of(color) + "");
        }
        return ChatColor.translateAlternateColorCodes('&', string)
                .replace(">>", "»")
                .replace("<<", "«")
                .replace("->", "→")
                .replace("<-", "←")
                .replace("**", "•");
    }
}
