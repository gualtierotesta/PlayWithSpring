# Spring Native


https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-native-build-tools

## Setup

    sdk install java 21.3.0.r11-nik
    sdk use java 21.3.0.r11-nik
    gu install native-image

## Build

    # Standard (no native)
    ./mvnw clean verify
    ./mvnw soring-boot:run

    # Build native executable
    ./mvnw -Pnative -DskipTests clean package


## Ubuntu

Install all development tools required by GraalVM

    sudo apt-get install gcc zlib1g-dev build-essential mtools
