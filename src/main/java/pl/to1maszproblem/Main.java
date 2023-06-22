package pl.to1maszproblem;

import org.bukkit.plugin.java.JavaPlugin;
import pl.to1maszproblem.commands.CustomEnchantCommand;

import java.util.Objects;

public final class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("customenchant")).setExecutor(new CustomEnchantCommand());
    }
    public static Main getInstance() {
        return instance;
    }
}
