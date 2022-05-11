# Daily documentation

## Week 1 (25-04-2022 - 01-05-2022)

### Day 1 (Monday)

Today we did a brainstorming session. We discussed about the sensors we would buy or make. We also had quite a lengthy discussion with Bas about it. We came up with solutions for the DIY sensors.  
We started working the design of the webpage and the collaboration contract.  
We also planned the sprints.  

### Day 2 (Tuesday)

Today, we worked on making a webdesign, to iterate on and give the client an idea of what we want the website to look like. More info on this can be found [here](https://iot.dev.hihva.nl/2021-2022-feb-jun/group-project/teamnl-bmx-windspeed-logging/iot-bmx-team-nl/Front%20end/Website-Design/).  
Secondly, we researched the MyLaps device manual, to check how we can use this device with our product.  
Lastly, we made sure the gitlab pages were setup correctly and documented everything we have done so far.  
We made the following pages:  

- [Sensors](https://iot.dev.hihva.nl/2021-2022-feb-jun/group-project/teamnl-bmx-windspeed-logging/iot-bmx-team-nl/sensors/)  
- [Web application](https://iot.dev.hihva.nl/2021-2022-feb-jun/group-project/teamnl-bmx-windspeed-logging/iot-bmx-team-nl/Front%20end/Web-Application/)  
- [Web design](https://iot.dev.hihva.nl/2021-2022-feb-jun/group-project/teamnl-bmx-windspeed-logging/iot-bmx-team-nl/Front%20end/Website-Design/)

### Day 3 (Wednesday)

Today we prepared the presentation for our meeting with Aukje. 
We did a retrospective of what we have done so far, and made sure the documentation was up to date.

## Week 2 (9-5-2022 - 15-5-2022)

### Day 1 (Monday)

Today we prepared the performance reviews for our meeting with Bas in the afternoon. We talked about our project and the way we fill in our forms. 

For the backend, we laid the foundation for our models, repositories and controllers. We also searched for tutorials on making relations between our models.

[Backend tutorial link](https://www.baeldung.com/hibernate-one-to-many)

|![img.png](img.png)|
|:-:|
|Backend foundation|


### Day 2 (Tuesday)

Today we made the documentation look better, added some orange accents and some TeamNL logos.
A proper homepage was written.

|![Homepage_mkdocs.png](Homepage_mkdocs.png)|
|:-:|
|Homepage for documentation|



Then got a temperature and humidity sensor.
We were able to wire it properlu and get the first data.

|![Humidity_and_Temp_sensor.jpg](Humidity_and_Temp_sensor.jpg)|
|:-:|
|DHT22 Humadity and Temperature sensor|



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


This is the first prototype of the web application.


|![front_end_1st_prototype.png](front_end_1st_prototype.png)|
|:-:|
|DHT22 Humadity and Temperature sensor|

The basis of the back end is done.
Models are filled in with variables and methods.
Controllers and repositories are filled with methods as well.
The backend is now connected to a remote database.
Routes were checked.

### Day 3 (Wednesday)

The wemos can now be connected to the internet and perform get requests.  

```cpp
void setup_wifi() {
  // Connect WiFi
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.hostname("Name");
  WiFi.begin(ssid, password);
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print("-");
    Serial.flush();
  }
  Serial.println("");
  Serial.println("WiFi connected");
 
  // Print the IP address
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
}



int httpGETRequest(const char* serverName, JsonObject& json) {
  WiFiClient client;
  HTTPClient http;
  http.useHTTP10(true);
  http.begin(client, serverName);
  int errcode = http.GET();
  if (errcode) {
    String err = http.errorToString(errcode);
    Serial.print("error get request for url: ");
    Serial.println(serverName);
    return -1;
  } else {
    // Parse response
    DynamicJsonDocument doc(2048);
    deserializeJson(doc, http.getStream());
    json = doc["data"];
    // Disconnect
    http.end();
  }

}

```
