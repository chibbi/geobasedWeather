package org.chibbi.geobasedWeather;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class App extends JavaPlugin {
    public static BukkitTask id;

    @Override
    public void onEnable() {
        File dir = new File("plugins/geobasedWeather/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        getConf();
        // Initiating other Classes
        this.getCommand("weather").setExecutor(new WeatherCommand());
        this.getCommand("weather").setTabCompleter(new WeatherTabCompletion());
        getLogger().info("Weather initiated!");
        startScheduler();
    }

    public void startAgain() {
        if (id.isCancelled()) {
            startScheduler();
        }
    }

    public FileConfiguration getConf() {
        File customConfigFile = new File("plugins/geobasedWeather/config.yml");
        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
        if (!customConfigFile.exists()) {
            cusconf.set("apikey", "API-key");
            cusconf.set("city", "London");
            cusconf.set("world", "world");
            try {
                cusconf.save(customConfigFile);
            } catch (IOException e) {
                getLogger().severe("Couldn't save config file");
                getLogger().severe(e.getMessage());
            }
        }
        return cusconf;
    }

    public void startScheduler() {
        id = Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                FileConfiguration config = getConf();
                try {
                    String urlString = "api.openweathermap.org/data/2.5/weather?q=" + config.get("city") + "&appid="
                            + config.get("apikey");
                    String respo = null;

                    URL url = new URL("http://" + urlString);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(2000);
                    con.setReadTimeout(2000);

                    int status = con.getResponseCode();

                    if (status >= 400) {
                        getLogger().severe("No connection with Weather Api (possibly have to set your apikey)");
                        con.disconnect();
                        return;
                    }
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    respo = content.toString();
                    in.close();
                    con.disconnect();
                    if (respo == null) {
                        getLogger().severe("Weather API responded without information, likely a problem of the API.");
                        return;
                    }
                    if (config.get("world") == null) {
                        getLogger().severe(
                                "Please define a world for this Plugin, via the '/weather set world YOURWORLD' command,"
                                        + " or manually editing the config file.");
                        return;
                    }
                    String yoWorld = config.get("world").toString();
                    if (respo.toLowerCase().contains("thunderstorm")) {
                        Bukkit.getWorld(yoWorld).setThundering(true);
                    } else {
                        Bukkit.getWorld(yoWorld).setThundering(false);
                    }
                    if (respo.toLowerCase().contains("rain")) {
                        Bukkit.getWorld(yoWorld).setStorm(true);
                    } else {
                        Bukkit.getWorld(yoWorld).setStorm(false);
                    }

                } catch (IOException e) {
                    getLogger().severe(
                            "\033[31mConnection Error\033[39m: likely lost internet connection. if multiple errors pop up, please check your internet connection!"
                                    + " You can contact the developer if you have problems.");
                }

            }
        }, 100L, 100L);

    }

    @Override
    public void onDisable() {
        id.cancel();
        saveConfig();
    }
}
