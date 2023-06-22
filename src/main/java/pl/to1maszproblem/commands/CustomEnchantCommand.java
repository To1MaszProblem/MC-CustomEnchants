package pl.to1maszproblem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.to1maszproblem.Main;
import pl.to1maszproblem.utils.TextUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CustomEnchantCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission(Objects.requireNonNull(Main.getInstance().getConfig().getString("enchantment.permission")))) {
                player.sendMessage(TextUtil.fixColor("&cNie posiadasz uprawnień do tej komendy"));
            }
            if (args.length < 2) {
                player.sendMessage(TextUtil.fixColor("&7Poprawne użycie: &a/customenchant [typ] [poziom]"));
                return false;
            }
            String amount = args[1];
            if (!isInt(amount)) {
                player.sendMessage(TextUtil.fixColor("&cPodaj poprawnie liczbe!"));
                return false;
            }
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta != null) {
                List<String> lore = itemMeta.getLore();
                if (lore == null) {
                    lore = new ArrayList<>();
                }
                switch (args[0].toLowerCase()) {
                    case "protection" -> {
                        lore.add(TextUtil.fixColor(Main.getInstance().getConfig().getString("enchantment.lore.protection").replace("[amount]", amount)));
                        itemMeta.setLore(lore);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        itemMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, Integer.parseInt(amount), true);
                        item.setItemMeta(itemMeta);
                    }
                    case "sharpness" -> {
                        lore.add(TextUtil.fixColor(Main.getInstance().getConfig().getString("enchantment.lore.sharpness").replace("[amount]", amount)));
                        itemMeta.setLore(lore);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        itemMeta.addEnchant(Enchantment.DAMAGE_ALL, Integer.parseInt(amount), true);
                        item.setItemMeta(itemMeta);
                    }
                    case "unbreaking" -> {
                        lore.add(TextUtil.fixColor(Main.getInstance().getConfig().getString("enchantment.lore.unbreaking").replace("[amount]", amount)));
                        itemMeta.setLore(lore);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        itemMeta.addEnchant(Enchantment.DURABILITY, Integer.parseInt(amount), true);
                        item.setItemMeta(itemMeta);
                    }
                    case "fire_aspect" -> {
                        lore.add(TextUtil.fixColor(Main.getInstance().getConfig().getString("enchantment.lore.fire_aspect").replace("[amount]", amount)));
                        itemMeta.setLore(lore);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        itemMeta.addEnchant(Enchantment.FIRE_ASPECT, Integer.parseInt(amount), true);
                        item.setItemMeta(itemMeta);
                    }
                    case "fortune" -> {
                        lore.add(TextUtil.fixColor(Main.getInstance().getConfig().getString("enchantment.lore.fortune").replace("[amount]", amount)));
                        itemMeta.setLore(lore);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, Integer.parseInt(amount), true);
                        item.setItemMeta(itemMeta);
                    }
                    case "efficiency" -> {
                        lore.add(TextUtil.fixColor(Main.getInstance().getConfig().getString("enchantment.lore.efficiency").replace("[amount]", amount)));
                        itemMeta.setLore(lore);
                        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        itemMeta.addEnchant(Enchantment.DIG_SPEED, Integer.parseInt(amount), true);
                        item.setItemMeta(itemMeta);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> argument = Arrays.asList("protection", "sharpness", "unbreaking","fire_aspect", "fortune", "efficiency");
            return argument;
        }
        if (args.length == 2) {
            List<String> argument = Arrays.asList("1", "10", "50","100");
            return argument;
        }
        return null;
    }
    private boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
