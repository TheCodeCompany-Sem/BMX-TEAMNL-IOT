#include <DHT.h>
#include <ESP8266WiFi.h>
#include <DNSServer.h>
#include <ESP8266WebServer.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>

#define INTERVAL 30                                                 // Intervall of sending in seconds
// DHT
#define DHTPIN 4                                                    // DHT pin
#define DHTTYPE DHT22
//info config
//weather sensors

const byte   interruptPin = D8; // Or other pins that support an interrupt
unsigned int Debounce_Timer, Current_Event_Time, Last_Event_Time, Event_Counter;
float        WindSpeed;

const byte windSpeedPin = D8;
const byte windDirPin = A0;
// Initialize DHT sensor.
DHT dht(DHTPIN, DHTTYPE);

int status = WL_IDLE_STATUS;
unsigned int windcnt = 0;
unsigned int raincnt = 0;
unsigned long lastSend;

const char* ssid = "OnePlus 8";
const char* password = "r5gc6x4d";

//////////////// Functions //////////////////////////////////////////

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


void httpPOSTRequest(const char* serverName, char* httpRequestData){
  WiFiClient client;
  HTTPClient http;
  http.useHTTP10(true);
  http.begin(client, serverName);
    // Specify content-type header
  //http.addHeader("Content-Type", "application/x-www-form-urlencoded");
  // Data to send with HTTP POST
  http.addHeader("Content-Type", "application/json");
  http.addHeader("Content-Length", "1024");
  int httpResponseCode = http.POST(httpRequestData);
  
  Serial.print("HTTP Response code: ");
  Serial.println(httpResponseCode);
  http.end();
}

void getAndSendTemperatureAndHumidityData()
{
  Serial.println("Collecting Weather data.");

  // Reading temperature or humidity takes about 250 milliseconds!
  int h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  int t = dht.readTemperature();

  // Check if any reads failed and exit early (to try again).
  if (false) {
    Serial.println("Failed to read from DHT sensor!");
    delay(1000);
    lastSend = millis() - INTERVAL * 1000;
    return;
  }

  //Calculate Wind Speed (klicks/interval * 2,4 kmh)
  int ws = WindSpeed;
  windcnt = 0;
  //Calculate Rain
  int r = (raincnt / 2) * 0.2794;
  raincnt = 0;
  // get wind direction
  float dirpin = analogRead(windDirPin) * (3.3 / 1023.0);
  String wd = "other";

  if (dirpin > 2.60 &&  dirpin < 2.70 ) {
    wd = "N";
  }
  if (dirpin > 1.60 &&  dirpin < 1.70 ) {
    wd = "NE";
  }
  if (dirpin > 0.30 &&  dirpin < 0.40 ) {
    wd = "E";
  }
  if (dirpin > 0.60 &&  dirpin < 0.70 ) {
    wd = "SE";
  }
  if (dirpin > 0.96 &&  dirpin < 1.06 ) {
    wd = "S";
  }
  if (dirpin > 2.10 &&  dirpin < 2.20 ) {
    wd = "SW";
  }
  if (dirpin > 3.15 &&  dirpin < 3.25 ) {
    wd = "W";
  }
  if (dirpin > 2.95 &&  dirpin < 3.05 ) {
    wd = "NW";
  }

  Serial.print("Humidity: ");
  Serial.print(h);
  Serial.print(" %\t");
  Serial.print("Temperature: ");
  Serial.print(t);
  Serial.print(" *C ");
  Serial.print("Windspeed: ");
  Serial.print(ws);
  Serial.print(" km/h ");
  Serial.print("Wind Direction: ");
  Serial.print(wd);
  Serial.print(" ");
  Serial.print("Rain: ");
  Serial.print(r);
  Serial.print(" mm ");


  String temperature = String(t);
  String humidity = String(h);
  String windspeed = String(ws);
  String winddir = String(wd);
  String rain = String(r);

  // // Just debug messages
  // Serial.print( "Sending Data : [" );
  // Serial.print( temperature ); Serial.print( "," );
  // Serial.print( humidity ); Serial.print( "," );
  // Serial.print( windspeed ); Serial.print( "," );
  // Serial.print( winddir ); Serial.print( "," );
  // Serial.print( rain );
  // Serial.print( "]   -> " );

  // Prepare a JSON payload string
  String payload = "{\"recordedTime\": 0999-12-31T23:00:00.000:00,";

  payload += "\"temperature\":"; payload += temperature; payload += ",";
  payload += "\"humidity\":"; payload += humidity; payload += ",";
  payload += "\"windSpeed\":"; payload += windspeed; payload += ",";
  payload += "\"windDirection\":"; payload += winddir;
  payload += "}";

  // Send payload
  char attributes[100];
  payload.toCharArray( attributes, 1000 );
  Serial.println( attributes );
  //  client.publish( "v1/devices/me/telemetry", attributes );
  //  Serial.println( attributes );
  httpPOSTRequest("https://bmx-nl-app-be-staging.herokuapp.com/TrackTimeRecord/measurement/1", attributes);
  lastSend = millis();
}



ICACHE_RAM_ATTR void  cntWindSpeed(void) {
  if (!(millis() - Debounce_Timer) < 5) {
    Debounce_Timer = millis();                                        // Set debouncer to prevent false triggering
    Event_Counter++;
  }
}
void Timer_ISR (void) {                                                       // Timer reached zero, now re-load it to repeat
  timer0_write(ESP.getCycleCount() + 80000000L);                              // Reset the timer, do this first for timing accuracy
  WindSpeed = Event_Counter * 2.5 / 2;
  Event_Counter = 0;
}


void cntRain() {
  raincnt++;
}


//////////////// SETUP //////////////////////////////////////////////
void setup() {
  Serial.begin(115200);
  setup_wifi();
  // pin for Wind speed
  pinMode(windSpeedPin, INPUT_PULLUP);
  noInterrupts();
  attachInterrupt(digitalPinToInterrupt(windSpeedPin), cntWindSpeed, RISING);
  timer0_isr_init();                             // Initialise Timer-0
  timer0_attachInterrupt(Timer_ISR);             // Goto the Timer_ISR function when an interrupt occurs
  timer0_write(ESP.getCycleCount() + 80000000L);
  pinMode(windDirPin, INPUT);
  interrupts();

  dht.begin();
  delay(10);

}

//////////////// LOOP //////////////////////////////////////////////
void loop() {
  getAndSendTemperatureAndHumidityData();
  delay(1000);
}