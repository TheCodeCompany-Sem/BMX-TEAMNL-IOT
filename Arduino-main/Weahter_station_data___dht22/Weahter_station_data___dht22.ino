#include "DHT.h"
#include <PubSubClient.h>
#include <ESP8266WiFi.h>


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

//////////////// SETUP //////////////////////////////////////////////
void setup() {
  Serial.begin(115200);
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

  // send device attributes

  // Prepare a JSON payload string
  String payload = "{";
  //     payload += "\"Device\":"; payload += device_model; payload += ",";
  //     payload += "\"Firmware\":"; payload += software_version; payload += ",";
  //     payload += "\"Sensors\":"; payload += "DHT22 - Wind Speed";
  //     payload += "}";

}

//////////////// LOOP //////////////////////////////////////////////
void loop() {
  getAndSendTemperatureAndHumidityData();
  delay(1000);
}

//////////////// Functions //////////////////////////////////////////

void getAndSendTemperatureAndHumidityData()
{
  Serial.println("Collecting Weather data.");

  // Reading temperature or humidity takes about 250 milliseconds!
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();

  // Check if any reads failed and exit early (to try again).
  if (false) {
    Serial.println("Failed to read from DHT sensor!");
    delay(1000);
    lastSend = millis() - INTERVAL * 1000;
    return;
  }

  //Calculate Wind Speed (klicks/interval * 2,4 kmh)
  float ws = WindSpeed;
  windcnt = 0;
  //Calculate Rain
  float r = (raincnt / 2) * 0.2794;
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

  // Just debug messages
  Serial.print( "Sending Data : [" );
  Serial.print( temperature ); Serial.print( "," );
  Serial.print( humidity ); Serial.print( "," );
  Serial.print( windspeed ); Serial.print( "," );
  Serial.print( winddir ); Serial.print( "," );
  Serial.print( rain );
  Serial.print( "]   -> " );

  // Prepare a JSON payload string
  String payload = "{";
  payload += "\"temperature\":"; payload += temperature; payload += ",";
  payload += "\"humidity\":"; payload += humidity; payload += ",";
  payload += "\"windspeed\":"; payload += windspeed; payload += ",";
  payload += "\"winddirection\":"; payload += winddir; payload += ",";
  payload += "}";

  // Send payload
  char attributes[100];
  payload.toCharArray( attributes, 100 );
  //  client.publish( "v1/devices/me/telemetry", attributes );
  //  Serial.println( attributes );

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
