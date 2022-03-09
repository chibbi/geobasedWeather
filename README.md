<p align="center">
  <a href="https://github.com/chibbi/geobasedWeather/blob/main/LICENSE.txt"><img src="https://img.shields.io/github/license/chibbi/geobasedWeather?style=for-the-badge" alt="License"></a>
  <a href="https://github.com/chibbi/geobasedWeather/stargazers"><img src="https://img.shields.io/github/stars/chibbi/geobasedWeather?style=for-the-badge" alt="GitHub stars"></a>
  <a href="https://github.com/chibbi/geobasedWeather/issues"><img src="https://img.shields.io/github/issues/chibbi/geobasedWeather?style=for-the-badge" alt="GitHub issues"></a>
  <br>
  <a href="https://github.com/chibbi/geobasedWeather/actions"><img src="https://img.shields.io/github/workflow/status/chibbi/geobasedWeather/build?style=for-the-badge"></a>
    <a href="https://github.com/chibbi/geobasedWeather/actions"><img src="https://img.shields.io/github/workflow/status/chibbi/geobasedWeather/CodeQL?label=CodeQL&style=for-the-badge"></a>
  <!-- <a href="https://github.com/chibbi/geobasedWeather/"><img src="https://img.shields.io/github/commit-activity/m/chibbi/geobasedWeather?style=for-the-badge"></a>
  <br> -->
  <a href="https://github.com/chibbi/geobasedWeather/releases/latest"><img src="https://img.shields.io/github/downloads/chibbi/geobasedWeather/total?style=for-the-badge"></a>
  <a href="https://github.com/chibbi/geobasedWeather/releases/latest"><img src="https://img.shields.io/github/downloads/chibbi/geobasedWeather/latest/total?style=for-the-badge"></a>
</p>
<h1>geobasedWeather</h1>
<p>
  <strong><a href="https://www.spigotmc.org/resources/realtime-geobased-weather.88405/">geobasedWeather</a></strong>
  syncronises the weather of your server with the weather of an actual real location.
</p>
<h2>Quickstart guide</h2>
<p>
<ol>
  <li>
    You need an api key from 
  <strong><a href="https://openweathermap.org/price">openweathermap</a></strong> (the <strong>free</strong> tier is sufficient)
  </li>
  <li>
    Installing the plugin
    <ol>
      <li>download the latest <a href="https://github.com/chibbi/geobasedWeather/releases/latest">version</a></li>
      <li>move the jar file into your plugin folder</li>
    </ol> 
  </li>
  <li>
    Configure the plugin by either
    <ol>
      <li>
        <details>
          <summary>use the provided commands</summary>
          <br>
          <ol>
            <li>restart your server</li>
            <li>run <code>/weather set apikey YOUR-API-KEY</code></li>
            <li>use the <a href="#Commands">set command</a> to change other variables as desired</li>
          </ol> 
          <br>
        </details>
      </li>
      <li>
        <details>
          <summary>create the <a href="https://raw.githubusercontent.com/chibbi/geobasedWeather/main/config-template.yml">config file</a> manually (<strong>NOT advised</strong>)</summary>
          <br>
          <ol>
            <li>create a file: <code>plugins/geobasedWeather/config.yml</code></li>
            <li>replace <code>YOUR-API-KEY</code> with your actual apikey</li>
            <li>change the other two variables as desired and needed.</li>
            <li>restart your server</li>
            <li>If you encounter errors, just use <a href="#Commands">set command</a></li>
          </ol>
          <br>
        </details>
      </li>
    </ol> 
  </li>
</ol> 
</p>
<h2>Commands</h2>
<p>
          <ol>
          <li>
            <details>
              <summary>Get information and configuration</summary>
              <br>
              <ol>
                <li><code>/weather get city</code></li>
                <li><code>/weather get world</code></li>
                <li><code>/weather get weather</code></li>
                <li><code>/weather get apikey</code></li>
              </ol> 
            </details>
          </li>
          <li>
            <details>
              <summary>Set configuration</summary>
              <br>
              <ol>
                <li>
                  <code>/weather set city YOUR-DESIRED-CITY</code>
                  <details>
                    <summary>examples for YOUR-DESIRED-CITY</summary>
                    <br>
                    <ol>
                      <li><code>London</code></li>
                      <li><code>Berlin</code></li>
                      <li><code>London,uk</code></li>
                      <li><code>Berlin,de</code></li>
                    </ol> 
                  </details>
                </li>
                <li><code>/weather set world WORLD-NAME</code></li>
                <li><code>/weather set apikey YOUR-API-KEY</code></li>
              </ol> 
            </details>
          <li>
            <details>
              <summary>stop</summary>
              <br>
              Stops the plugin. The weather will return back to normal.
              <br><strong>Starting the plugin will require a server restart or reload.</strong>
            </details>
          </li>
            <!--
          <li>
            <details>
              <summary>start  - <strong>NOT IMPLEMENTED YET</strong></summary>
              <br>
              Starts the plugin again. The weather will be syncronized with the weather of the set city again.
              <br><strong>This is not implemented yet.</strong>
            </details>
          </li>
-->
        </ol> 
</p>
<h2>Breakdown of apicalls</h2>
<p>
  You can use a free plan from <a href="https://openweathermap.org/price">openweathermap</a> for an accurate weather, because in the free version you have
  <br>60 calls/minute
  <br>1,000,000 calls/month
  <br>which are enough to make one call ca. 2.63seconds if you have the server up and running 24/7/30days a month
  <br>
  <br>It is currently set on 1 call every 5 seconds, so don't worry about using up your api calls (because you won't)  
  The Time between a call could be lowered, but it's not actually necessary.  
  <br>
  <br>If you think about it, the most it could ever lag behind is 5 seconds, and rain doesn't start and stop in 5 seconds so its more than fine.
</p>
<details>
<summary><strong>Ideas for the further development</strong></summary>
<p>
  Add more Input possibilities. openweathermap also supports longitude and aptitude, which might be needed in some cases.
  <br>Make the time between api calls configurable, so you can f**k up.
  <br>But i have to say, the plugin does everything i would want it to do, so there is no reason for me to develop it further.
  <br>If you would like to see further development or specific features, just create an <a href="https://github.com/chibbi/geobasedWeather/issues/new/choose">issue</a>
</p>
</details>
