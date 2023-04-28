

[![](https://jitpack.io/v/teraprath/PointsAPI.svg)](https://jitpack.io/#teraprath/PointsAPI)

# PointsAPI

PointsAPI (MySQL-Support) for Spigot 1.19+

## Features

- Easy to use and lightweight
- MySQL-Support
- Points Command (100% Configurable)

## Installation

**Step 1:** Download the plugin [here](https://github.com/teraprath/PointsAPI/releases/latest) and install it on your server.<br> **Step 2:** Edit `config.yml` in `plugins/PointsAPI` folder and insert your data.<br>
```yaml mysql:    
  host: localhost    
  port: 3306    
  database: points    
  user: root    
  password: password    
points:    
  start_value: 1000    
language:    
  prefix: "&7[&aPointsAPI&7] "    
  player_not_found: "&7Player &c&o%player% &7not found."    
  points_self: "&7You have &a&l%points% &7points."    
  points_others: "&2%player% has &a&l%points% &7points."    
``` **Step 3:** Import API using:<br>  
  
### Maven  
  
````xml    
 <repository>    
    <id>jitpack.io</id>    
    <url>https://jitpack.io</url>    
</repository>    
````   
````xml    
 <dependency>    
    <groupId>com.github.teraprath</groupId>    
    <artifactId>PointsAPI</artifactId>    
    <version>INSERT_VERSION_HERE</version>    
</dependency>    
````   
### Gradle

````groovy repositories {    
    maven { url 'https://jitpack.io' }    
}    
```` ```` dependencies {    
    implementation 'com.github.teraprath:PointsAPI:INSERT_VERSION_HERE'    
}    
````   
You can see the latest version [here](https://github.com/teraprath/PointsAPI/releases/latest).

**Step 4:** You're done!

## Development API

### Basic Usage
An overview of the methods provided by the PointsAPI.

```java    
int points = PointsAPI.getPoints(player, amount);    
    
PointsAPI.setPoints(player, amount);    
PointsAPI.addPoints(player, amount);    
PointsAPI.removePoints(player, amount);    
    
boolean registered = PointsAPI.hasRegistered(player, amount);    
    
```   
### Asynchronous Processing
To process tasks asynchronously, use the following method provided by the Bukkit library.

```java
Bukkit.getScheduler().runTaskAsynchronously(plugin, task -> {
	PointsAPI.addPoints(100);
	// do stuff
});
```
