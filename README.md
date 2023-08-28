# Meteorological REST API

Meteorological REST API is a Java-based web service developed using Spring REST to receive and process data from meteorological sensors.

## Project Description

In various domains, REST APIs have become an integral tool for data exchange. The Meteorological REST API project focuses on creating a service that can receive and process data from meteorological sensors. Developed using Spring REST, this project enables the reception of data, sensor registration, and data analysis.

## Features

- Register new meteorological sensors
- Add measurements from sensors
- Retrieve all saved measurements
- Obtain the count of rainy days based on measurements

## Endpoints

- `POST /sensors/registration`: Register a new sensor with a unique name.
- `POST /measurements/add`: Add a new measurement from a sensor.
- `GET /measurements`: Retrieve a list of all saved measurements.
- `GET /measurements/rainyDaysCount`: Get the count of rainy days based on measurements.

## JSON Format

### Register Sensor Request

```json
{
    "name": "Sensor name"
}
```
### Add Measurement Request


```json
{
    "value": 30,
    "raining": true,
    "sensor": {
        "name": "Sensor name"
    }
}
```
### Measurement Response
```json
{
    "value": 30,
    "raining": true,
    "time": "2023-08-28T12:34:56.789Z"
}

```
### Requirements
- Java 8 or higher
- Spring Boot
- Maven

### Usage
- Utilize tools like Postman to interact with the API endpoints.
- Register a sensor using the /sensors/registration endpoint.
- Add measurements using the /measurements/add endpoint.
- Retrieve all measurements using the /measurements endpoint.
- Obtain the count of rainy days using the /measurements/rainyDaysCount endpoint.

