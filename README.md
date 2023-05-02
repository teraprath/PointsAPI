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
- Compatible with [StatsAPI](https://github.com/teraprath/StatsAPI)

## Implementation

You can see the latest version [here](https://github.com/teraprath/PointsAPI/releases/latest).

**Using Maven:**

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

**Using Gradle:**
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

## Basic Usage
An overview of the methods provided by the PointsAPI.

```java

PointsAPI.getPoints(player);
PointsAPI.setPoints(player, amount);
PointsAPI.addPoints(player, amount);
PointsAPI.removePoints(player, amount);
PointsAPI.reset(player);

```
Visit [wiki](https://github.com/teraprath/PointsAPI/wiki/) page to see usage guide.
