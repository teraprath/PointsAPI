<!--suppress HtmlDeprecatedAttribute -->
<div align="center">

[![](https://jitpack.io/v/teraprath/PointsAPI.svg)](https://jitpack.io/#teraprath/PointsAPI)
<div>
    <h1>PointsAPI</h1>
    <p>for Spigot 1.19+<p>
</div>
</div>

## Features

- Easy to use and lightweight
- MySQL-Support
- Points Command (100% Configurable)

## Installation

**Step 1:** Download the plugin [here](https://github.com/teraprath/PointsAPI/releases/latest) and install it on your server.<br>
**Step 2:** Edit `config.yml` in `plugins/PointsAPI` folder and insert your data.<br>
```yaml
mysql:
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
```
**Step 3:** Import API using:<br>

## Development API

### Implementation

#### Using Maven:

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

#### Using Gradle:

````groovy
repositories {
    maven { url 'https://jitpack.io' }
}
````
````
dependencies {
    implementation 'com.github.teraprath:PointsAPI:INSERT_VERSION_HERE'
}
````

You can see the latest version [here](https://github.com/teraprath/PointsAPI/releases/latest).
<br>
Add PointsAPI as dependency in your `plugin.yml`:

```yaml
name: TestPlugin
version: 1.0
main: net.name.testplugin.Main
api-version: 1.19
authors: [ Names ]
depend: [ PointsAPI ]
```

### Basic Usage
An overview of the methods provided by the PointsAPI.

```java

int points = PointsAPI.getPoints(player);

        PointsAPI.setPoints(player, amount);
        PointsAPI.addPoints(player, amount);
        PointsAPI.removePoints(player, amount);

```
### Asynchronous Processing
To process tasks asynchronously, use the following method provided by the Bukkit library.

```java
Bukkit.getScheduler().runTaskAsynchronously(plugin, task -> {
        PointsAPI.addPoints(100);
        // do stuff
        });
```