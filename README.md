# geobasedWeather [![](https://tokei.rs/b1/github/chibbi/geobasedWeather)]()
a small plugin, which allows you to configure, where you live, to make it rain in minecraft, when it rains in reality.

It lets it rain in Minecraft, if it rains in the city, you set (with: ```/weather set city YOURCITIY```)  
It lets it THUNDERR in  Minecraft, if it THUNDERSS in the city, you set (with: ```/weather set city YOURCITIY```)  
And logically, the sun will shine according to the weather in reality.  

You can try out London (because you can set it to every City on earth), i have yet to switched to London and see Sunshine after it xD.
Uses the [openweathermap](https://home.openweathermap.org/subscriptions) api.



You can use a free plan from [openweathermap](https://home.openweathermap.org/subscriptions) for an accurate weather, because in the free version you have  
60 calls/minute  
1,000,000 calls/month  
which are enough to make one call ca. 2.6seconds if you have the server up and running 24/7/30days a month

It is currently set on 1 call every 5 seconds, so don't worry about using up your api calls (because you won't)  
The Time between a call could be lowered, and i will probably make it configurable in the future, so you can fuck up your api calls yourself, but it's not actually necessary.  

If you think about it, the most it could ever lag behind is 5 seconds, and rain doesn't start and stop in 5 seconds so its fine xD.
