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

## Core Classes

The application is organized using a modular, object-oriented design where each class is responsible for a single, well-defined task.

- **EnvironmentMonitorGUI**  
  Main application entry point. Initializes the JavaFX window, manages layout, and coordinates interactions between all components.

- **OverviewPanel**  
  Displays summarized indoor and outdoor environmental data, allowing users to quickly compare conditions at a glance.

- **ChartPanel**  
  Handles real-time visualization of temperature, humidity, and pressure using JavaFX charts. Maintains time-series data and updates graphs dynamically.

- **SerialReader**  
  Manages serial communication between the Arduino and the Java application. Parses incoming sensor data and forwards it to the GUI for processing.

- **OpenWeatherAPI**  
  Fetches live outdoor weather data from the OpenWeather API using HTTP requests and JSON parsing.

- **ComfortMessages**  
  Generates context-aware messages based on comparisons between indoor sensor readings and outdoor weather conditions.

- **TempMonitorTests**  
  Contains JUnit test cases used to validate application logic and data handling.


  
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


## Learning Outcomes
- Implemented serial communication between embedded hardware and a desktop application
- Integrated third-party APIs into a Java application
- Designed a modular JavaFX GUI
- Worked with real-time data visualization
- Applied unit testing (JUnit) to validate core application logic


## Author
- Matthew Simpson
- Electrical Engineering Student
- EECS 1021 – York University
