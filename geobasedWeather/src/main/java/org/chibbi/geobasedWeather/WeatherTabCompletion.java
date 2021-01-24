package org.chibbi.geobasedWeather;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class WeatherTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> l = new ArrayList<String>(); // makes a ArrayList
        // define the possible possibility's for argument 1
        if (args.length == 1) {
            l.add("stop"); // Possibility #1
            l.add("start"); // Possibility #2
            l.add("set");
            l.add("get");
        }
        // define the possible possibility's for argument 2
        else if (args.length == 2) {
            l.add("apikey"); // Possibility #1
            l.add("city"); // Possibility #2
        } else if (args.length == 3) {
            if (args[1].equals("city")) {
                l.add("London"); // Possibility #1
                l.add("Washington"); // Possibility #1
                l.add("Berlin"); // Possibility #1
            } else {
                l.add("https://openweathermap.org/current"); // Possibility #2
            }
        }
        return l; // returns the possibility's to the client
    }
}
