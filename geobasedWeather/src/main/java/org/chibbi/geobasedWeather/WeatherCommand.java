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
                        App.startAgain();
                        player.sendMessage("started Weather");
                        return true;
                    case "set":
                        if (args.length != 3) {
                            player.sendMessage("§6Wrong Usage");
                            return false;
                        }
                        App.startAgain();
                        File customConfigFile = new File("plugins/geobasedWeather/config.yml");
                        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
                        cusconf.set(args[1], args[2]);
                        try {
                            cusconf.save(customConfigFile);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        player.sendMessage("§6set §7" + args[1] + " to §7" + args[2]);
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
