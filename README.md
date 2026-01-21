## Overview

This project is a real-time indoor environment monitoring application developed as a bonus project for EECS 1021. It integrates an Arduino-based sensor system with a JavaFX desktop application to display indoor environmental data and compare it with live outdoor weather information retrieved from the OpenWeather API.
The system focuses on monitoring temperature, humidity, and pressure, presenting the data through a graphical interface with real-time charts and summary panels.

## Features
- Real-time indoor temperature, humidity, and pressure monitoring
- Live outdoor weather data retrieval using OpenWeather API
- JavaFX graphical user interface
- Dynamic line charts for sensor data
- Comparison of indoor and outdoor environmental conditions
- Serial communication between Arduino and Java
- Modular and object-oriented Java design
- Unit testing with JUnit


## Technologies Used
- Hardware
- Arduino (Grove Beginner Kit)
- Temperature and humidity sensor
- Pressure sensor
- USB serial connection
  
## Software
- Java 11
- JavaFX
- Maven
- jSerialComm
- OkHttp
- Gson
- JUnit
- Arduino IDE

## Project Structure
- First Path
- src/
- >main/
- >java/
- >EnvironmentMonitorGUI.java
- >OverviewPanel.java
- >ChartPanel.java
- >SerialReader.java
- >OpenWeatherAPI.java
- >ComfortMessages.java
- Second Path
- src/
- >test/
- >TempMonitorTests.java
- Third Path
- src/
- >main/
- >resources/

  
## How the System Works
1) The Arduino reads temperature, humidity, and pressure from connected sensors.
2) Sensor data is transmitted to the Java application via serial communication.
3) The Java application retrieves outdoor weather data using the OpenWeather API.
4) The JavaFX interface updates in real time to display:
- Indoor sensor readings
- Outdoor weather values
- Time-series charts
- Context-based comfort messages

## Screenshots

[![Temperature Monitoring Graphs](screenshots/Temp%20Monitor%20Graphs.png)](screenshots/Temp%20Monitor%20Graphs.png)


## Testing
Unit Tests are located in TempMonitorTests.java
- which validate application logic and data handling

## Learning Outcomes
- Implemented serial communication between embedded hardware and a desktop application
- Integrated third-party APIs into a Java application
- Designed a modular JavaFX GUI
- Worked with real-time data visualization
- Applied unit testing (JUnit) to validate core application logic


## Author
- Matthew Simpson
- EECS 1021 – York University
