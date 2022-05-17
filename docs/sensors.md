# Sensors

## Professional option

Davis is a reputable brand that produces weather station sensors.
This is their wind speed and wind direction sensor combo. It is expensive but might be an option if we need more precise data.
It also has the benefit of working out of the box. Therefore leaving us more time to work on the rest of the features.
<center>


|![Davis 6410](6410.jpg)|
| :-:|
|[Davis 6410](https://www.davis-europe.nl/product/davis-6410-anemometer-for-vantage-pro2-vantage-pro/)|


</center>

## DIY option

This option is the cheapest one.  The drawbacks are accuracy, the time it will take to build / calibrate the device, and it will not look as good as the professional option. Waterproofing is also a concern.

<center>


|![sketch_sensor.jpg](sketch_sensor.jpg)|
| :-:|
|Rotary sensor sketch|


</center>

### Wind direction sensor

We would use a rotary encoder, an electronic component used mainly in knobs and dials. The idea would be 
to 3D print a specific shape and put it on top of the encoder.
The wind would push the shape which would turn the "knob" until it reaches the position where   
the resistance is minimal, therefore determining the direction.
Another option that we might use is a hall effect sensor. It uses the same logic as the previous methods
but this one uses a gravity field to measure the rotation of the object. 

### Wind speed sensor (Anemometer)

the idea here is to have wings on a frictionless axle.  
That way the wind will push the wings and make them rotate.  
Then we would use an IR emitter and receiver. The wings would pass in between the two.  
It would block the  signal.  
We would count the amount of interruptions of the signal and guess the speed that way.   
To calibrate the sensor we would use a car. We'd get the device out of the window and measure the amount of rotations the wings do at certain speeds, that we can precisely identify using the car's speedometer.

### Temperature & humidity sensor

We're using a DHT22 sensor.
Here is the code we're running  
```cpp
#include "DHTesp.h"
DHTesp dht;
 
void setup() {
    Serial.begin(115200);
    dht.setup(D2, DHTesp::DHT22);
}
 
void loop() {
    float h = dht.getHumidity();
    float t = dht.getTemperature();
 
    Serial.print("humidity: ");
    Serial.println(h);
    Serial.print("temp: ");
    Serial.println(t);
 
    delay(2000);
}

```
It is wired this way:  

|![DHT22_sensor_wiring.png](DHT22_sensor_wiring.png)|
| :-:|
|DHT22 Humidity and Temperature sensor|




this is how the wiring looks in reality:


|![Humidity_and_Temp_sensor.jpg](Humidity_and_Temp_sensor.jpg)|
| :-:|
|DHT22 Humidity and Temperature sensor|


this is the data coming from the sensor: 

|![data_from_dht22.png](data_from_dht22.png)|
| :-:|
|Data coming form the DHT22 sensor|


|![Weather Station](weather_station_we_choosed_with_customer.png)|
| :-:|
|[Weahter station we choosed with our customer](https://www.robotshop.com/nl/nl/sparkfun-weermeterset.html)|


