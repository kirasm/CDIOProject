/* Create a WiFi access point and provide a web server on it. */

#include <ESP8266mDNS.h>
#include <ArduinoOTA.h>
#include <ESP8266WiFi.h>
#include <WiFiClient.h>

/* Set these to your desired credentials. */


#define _SENSOR_ID "0000000001"
#define _SENSOR_TYPE "2"
#define _FIRMWARE_VERSION "16"

#define LED_BUILTIN D4

#undef MDNS_DEBUG_RX
#undef MDNS_DEBUG_TX


const char* ssid = "SensorAP";
const char* password = "12345678";
//const char* ssid = "KHG-2";
//const char* ssid = "LD_Net";
//const char* password = "amazone10";
const char* fallback_ssid = "Sensor " _SENSOR_ID;


bool OTAupdate = false;
bool IsAP = false;

//char* hostString = "esp";


WiFiClient client;

bool tryConnect(const char* ssid, const char* password)
{
	WiFi.begin(ssid, password);

	for (int i = 0; i < 10; i++)
	{
		if (WiFi.status() != WL_CONNECTED)
		{
			Serial.print(".");

			delay(1000);
		}
		else
		{
			break;
		}
	}
	Serial.println("");

	if (WiFi.status() == WL_CONNECTED)
	{
		return true;
	}
	return false;
}

void blinkLED(int pin, int duration, int n)
{
	for (int i = 0; i < n; i++)
	{
		digitalWrite(pin, HIGH);
		delay(duration);
		digitalWrite(pin, LOW);
		delay(duration);
	}
}

void setup()
{
	pinMode(LED_BUILTIN, OUTPUT);
	digitalWrite(LED_BUILTIN, HIGH);

	Serial.begin(115200);

	Serial.println("Firmware version: " _FIRMWARE_VERSION);

	WiFi.mode(WIFI_OFF);


	if (tryConnect(ssid, password))
	{
		//Connected to AP
		Serial.println("Connected to AP");
		Serial.println("IP address: ");
		Serial.println(WiFi.localIP());
	}
	else
	{
		//AP not found!
		//make our own

		WiFi.softAP(fallback_ssid);
		IPAddress myIP = WiFi.softAPIP();
		IsAP = true;
		delay(1000);
		Serial.print("AP IP address: ");
		Serial.println(myIP);
	}


	//Serial.print("Hostname: ");
	//Serial.println(hostString);
	//WiFi.hostname(hostString);
	//if (!MDNS.begin(hostString))
	//{
	//	Serial.println("Error setting up MDNS responder!");
	//}


	ArduinoOTA.setPassword((const char *)"test");
	ArduinoOTA.onStart([]()
		{
			OTAupdate = true;
			blinkLED(LED_BUILTIN, 400, 2);
			digitalWrite(LED_BUILTIN, HIGH);
			Serial.println("OTA Update Initiated . . .");
		});
	ArduinoOTA.onEnd([]()
		{
			Serial.println("\nOTA Update Ended . . .s");
			//ESP.restart();
		});
	ArduinoOTA.onProgress([](unsigned int progress, unsigned int total)
		{
			digitalWrite(LED_BUILTIN, LOW);
			delay(5);
			digitalWrite(LED_BUILTIN, HIGH);
			Serial.printf("Progress: %u%%\r\n", (progress / (total / 100)));
		});
	ArduinoOTA.onError([](ota_error_t error)
		{
			blinkLED(LED_BUILTIN, 40, 2);
			OTAupdate = false;
			Serial.printf("OTA Error [%u] ", error);
			if (error == OTA_AUTH_ERROR) Serial.println(". . . . . . . . . . . . . . . Auth Failed");
			else if (error == OTA_BEGIN_ERROR) Serial.println(". . . . . . . . . . . . . . . Begin Failed");
			else if (error == OTA_CONNECT_ERROR) Serial.println(". . . . . . . . . . . . . . . Connect Failed");
			else if (error == OTA_RECEIVE_ERROR) Serial.println(". . . . . . . . . . . . . . . Receive Failed");
			else if (error == OTA_END_ERROR) Serial.println(". . . . . . . . . . . . . . . End Failed");
		});


	ArduinoOTA.begin();
}


void loop()
{
	ArduinoOTA.handle();
	//Serial.println("loop");

	if (OTAupdate || IsAP)
	{
		return;
	}

	if (WiFi.status() != WL_CONNECTED)
	{
		tryConnect(ssid, password);
		return;
	}

	if (!client.connected())
	{
		int n = MDNS.queryService("sensorserver", "tcp");
		//Serial.println(n);
		if (n > 0)
		{
			//Serial.println(MDNS.IP(0).toString() + " | " + MDNS.port(0));
			client.connect(MDNS.IP(0), MDNS.port(0));
		}
		else
		{
			Serial.println("No server found!");
			delay(10000);
		}
	}
	if (client.connected())
	{
		//Serial.println("connected");

		digitalWrite(LED_BUILTIN, LOW);
		delay(5);
		digitalWrite(LED_BUILTIN, HIGH);
		float temp = (3.3 * analogRead(A0) * 100.0) / 1024;
		//client.write("a\r\n");
		client.write(("temp|" _SENSOR_ID ":" + String(temp) + "%" _SENSOR_TYPE "\r\n").c_str());
		int sleepTime = client.readString().toInt() * 1000;// * 1000000;
		//Serial.println(String(temp) + "|" + String(sleepTime));


		//ESP.deepSleep(sleepTime,RF_CAL);
		delay(sleepTime);
	}
	//client.stop();
}
