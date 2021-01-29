package org.chibbi.geobasedWeather;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class WeatherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        File customConfigFile = new File("plugins/geobasedWeather/config.yml");
        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
        switch (args[0]) {
            case "stop":
                App.id.cancel();
                sender.sendMessage("§6stopped Weather");
                return true;
            case "start":
                // new App().startAgain();
                sender.sendMessage("sorry, not working yet, you'd have to reload the server, or restart. SORRYYY");
                return true;
            case "get":
                if (args.length != 2) {
                    sender.sendMessage("§6Wrong Usage");
                    return false;
                }
                if (args[1] == "weather") {
                    sender.sendMessage("Storm: " + Bukkit.getWorld("world").hasStorm() + " Rain: "
                            + Bukkit.getWorld("world").isThundering());
                    return false;
                }
                sender.sendMessage("§6current reallife location: §7" + cusconf.get(args[1]));
                return true;
            case "set":
                if (args.length != 3) {
                    sender.sendMessage("§6Wrong Usage");
                    return false;
                }
                cusconf.set(args[1], args[2]);
                try {
                    cusconf.save(customConfigFile);
                    sender.sendMessage("§6Successfully set §7" + args[1] + "§6 to §7" + args[2]);
                } catch (IOException e) {
                    System.out.println("Couldn't save config file!!");
                    sender.sendMessage("Couldn't save config file");
                }
                return true;
        }
        System.out.println(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
        return false;
    }
}
