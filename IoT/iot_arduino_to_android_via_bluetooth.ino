#include<SoftwareSerial.h>
SoftwareSerial btserial(0,1);
int trigger_pin = 2;

int echo_pin = 3;

int buzzer_pin = 10; 

float time;

float distance; 

float height = 40;




void setup ( ) {


        Serial.begin (9600); 

        btserial.begin(9600);

        pinMode (trigger_pin, OUTPUT); 

        pinMode (echo_pin, INPUT);

        pinMode (buzzer_pin, OUTPUT);




}




void loop ( ) {

    digitalWrite (trigger_pin, HIGH);

    delay(1000);

    digitalWrite (trigger_pin, LOW);

    time = pulseIn (echo_pin, HIGH);

    distance = (time * 0.034) / 2;
if(distance>height)
{
  Serial.println(1000);
}
else
{
Serial.println(((height-distance)/height)*100);
}

  }
