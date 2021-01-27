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
        getLogger().info(Bukkit.getWorlds().toString());
        startScheduler();
    }

    public static void startAgain() {
        if (id.isCancelled()) {
            new App().startScheduler();
        }
    }

    public FileConfiguration getConf() {
        File customConfigFile = new File("plugins/geobasedWeather/config.yml");
        FileConfiguration cusconf = YamlConfiguration.loadConfiguration(customConfigFile);
        if (!customConfigFile.exists()) {
            cusconf.set("apikey", "API-key");
            cusconf.set("city", "London");
            try {
                cusconf.save(customConfigFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
                        System.out.println("No connection with Weather Api (possibly have to set your apikey)");
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
                        System.out.println("GOT ERRORSS");
                        return;
                    }
                    if (respo.toLowerCase().contains("thunderstorm")) {
                        Bukkit.getWorld("world").setThundering(true);
                    } else {
                        Bukkit.getWorld("world").setThundering(false);
                    }
                    if (respo.toLowerCase().contains("rain")) {
                        Bukkit.getWorld("world").setStorm(true);
                    } else {
                        Bukkit.getWorld("world").setStorm(false);
                    }

                } catch (IOException e) {
                    System.out.println(
                            "Connection Error: likely lost internet connection. if multiple errors pop up, please look that your connection is stable\n"
                                    + "             You can contact the developer if you have problems.");
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
