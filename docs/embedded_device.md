# Wemos configuration

The wemos is now connected to the internet thanks to the following function:  

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

```

(the ssid and password are static variables)  
It can perform GET requests:  

```cpp
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

This is the POST Request function:  

```cpp
void httpPOSTRequest(const char* serverName, char[] httpRequestData){
  WiFiClient client;
  HTTPClient http;
  http.useHTTP10(true);
  http.begin(client, serverName);
    // Specify content-type header
  http.addHeader("Content-Type", "application/x-www-form-urlencoded");
  // Data to send with HTTP POST
  http.addHeader("Content-Type", "application/json");
  int httpResponseCode = http.POST(httpRequestData);
  //int httpResponseCode = http.POST("{\"api_key\":\"tPmAT5Ab3j7F9\",\"sensor\":\"BME280\",\"value1\":\"24.25\",\"value2\":\"49.54\",\"value3\":\"1005.14\"}");
  
  Serial.print("HTTP Response code: ");
  Serial.println(httpResponseCode);
  http.end();
}
```
