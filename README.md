# GeneralUtils

[![](https://jitpack.io/v/JL-III/GeneralUtils.svg)](https://jitpack.io/#JL-III/GeneralUtils)

GeneralUtils is a Java library primarily developed for personal use. This utility library is designed to streamline the coding process by providing a collection of commonly used code snippets, reusable components, and essential utilities that are frequently required in the software development process.

This library also houses key logic required for the seamless integration of custom plugins on Theatria. The purpose of GeneralUtils is to expedite the development and integration process of custom plugins, ensuring consistency across developments and reducing redundancy.

## Goal of the Repository

The aim of this repository is to manage and continually evolve the GeneralUtils library. It serves as a central hub for tracking issues, improvements, and potential bugs associated with the library. Moreover, it ensures traceability and transparency by keeping a record of all enhancements and modifications made to the library.

## About GeneralUtils

GeneralUtils contains a wide array of utilities and reusable code segments for:

- Custom Logging
- Player Messages
- Commonly Used Enums
- And many more...

## How to Use

To use GeneralUtils in your projects, follow these steps:

1. Add the JitPack repository to your build file.

For Maven, add the following in your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
2. Add the GeneralUtils dependency to your project.
```xml
<dependency>
    <groupId>com.github.JL-III</groupId>
    <artifactId>GeneralUtils</artifactId>
    <version>ReleaseVersion</version>
</dependency>
```