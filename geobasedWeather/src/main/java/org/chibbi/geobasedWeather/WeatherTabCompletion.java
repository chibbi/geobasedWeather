package org.chibbi.geobasedWeather;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class WeatherTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>();
        if (args.length == 1) {
            // l.add("stop");
            // l.add("start");
            l.add("set");
            l.add("get");
        } else if (args.length == 2) {
            l.add("apikey");
            l.add("city");
            l.add("world");
            l.add("weather");
        } else if (args.length == 3) {
            if (args[1].equals("city")) {
                l.add("London");
                l.add("Washington");
                l.add("Berlin");
            } else if (args[1].equals("world")) {
                for (int i = 0; i < Bukkit.getWorlds().size(); i++) {
                    l.add(Bukkit.getWorlds().get(i).getName());
                }
            } else {
                l.add("https://home.openweathermap.org/api_keys");
            }
        }
        return l;
    }
}
