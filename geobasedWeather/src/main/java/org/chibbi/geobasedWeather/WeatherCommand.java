package org.chibbi.geobasedWeather;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 2) {
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                File customConfigFile = new File("plugins/geobasedWeather/config.yml");
                FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
                switch (args[0]) {
                    case "stop":
                        if (args.length != 1) {
                            player.sendMessage("§6Wrong Usage");
                            return false;
                        }
                        App.id.cancel();
                        player.sendMessage("§6stopped Weather");
                        return true;
                    case "start":
                        if (args.length != 1) {
                            player.sendMessage("§6Wrong Usage");
                            return false;
                        }
                        // new App().startAgain();
                        player.sendMessage(
                                "sorry, not working yet, you'd have to reload the server, or restart. SORRYYY");
                        return true;
                    case "get":
                        if (args.length != 1) {
                            player.sendMessage("§6Wrong Usage");
                            return false;
                        }
                        player.sendMessage("§6current World: §7" + cusconf.get("city"));
                        return true;
                    case "set":
                        if (args.length != 3) {
                            player.sendMessage("§6Wrong Usage");
                            return false;
                        }
                        cusconf.set(args[1], args[2]);
                        try {
                            cusconf.save(customConfigFile);
                            player.sendMessage("Successfully set §7" + args[1] + "§6 to §7" + args[2]);
                        } catch (IOException e) {
                            System.out.println("Couldn't save config file!!");
                            player.sendMessage("Couldn't save config file");
                        }
                        return true;
                }
                ;
                return true;
            } else {
                System.out
                        .println(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
            }
        }
        System.out.println(sender.getName() + " (" + sender.getClass() + ") has tried: " + Arrays.toString(args));
        return false;
    }
}
