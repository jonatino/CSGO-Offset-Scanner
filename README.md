# Abendigo-Offset-Scanner
_Java Based CSGO Offset Scanner_

[![Build Status](https://travis-ci.org/Jonatino/Abendigo-Offset-Scanner.svg?branch=master)](https://travis-ci.org/Jonatino/Abendigo-Offset-Scanner)
[![Dependency Status](https://www.versioneye.com/user/projects/578ab65fc3d40f004685241e/badge.svg?style=flat)](https://www.versioneye.com/user/projects/578ab65fc3d40f004685241e)
 [![Download](https://api.bintray.com/packages/jonatino/maven/Abendigo-Offset-Scanner/images/download.svg) ](https://bintray.com/jonatino/maven/Abendigo-Offset-Scanner/_latestVersion)
[![license](https://img.shields.io/github/license/Jonatino/Abendigo-Offset-Scanner.svg?style=flat)](Apache License 2.0)

This library is licensed under the Apache License 2.0 and was created for use in my game modding platform called Abendigo which you
can see here: [https://github.com/Jire/Abendigo](https://github.com/Jire/Abendigo)

This project is a utility for my CSGO cheat Abendigo. This utility is used to scan the memory using byte-patterns to find the relative memory addresses for our cheat.

### Gradle
```groovy
compile 'com.github.jonatino:Abendigo-Offset-Scanner:1.6.3'
```

### Maven
```xml
<dependency>
  <groupId>com.github.jonatino</groupId>
  <artifactId>Abendigo-Offset-Scanner</artifactId>
  <version>1.6.3</version>
  <type>pom</type>
</dependency>
```

---

## Dependencies

- [Java-Memory-Manipulation](https://github.com/Jonatino/Java-Memory-Manipulation) as the foundation for memory reading/writing

## Plans

As of right now the project is currently not complete. The following are features I plan on adding:
 - Documentation
 - txt, xml, JSON: expect the ability to `dump` the offsets to a file format of your choice.
 - Support for Mac OSX, and other Unix based operating systems
