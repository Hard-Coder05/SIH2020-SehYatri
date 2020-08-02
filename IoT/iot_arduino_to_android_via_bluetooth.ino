#include<SoftwareSerial.h>
SoftwareSerial btserial(0,1);
int trigger_pin = 2; //ultrasonic sensor

int echo_pin = 3; //ultrasonic sensor

int time;

int distance; 


//assuming the tank to be cylindrical

float height = 40; //height of the tank

//float radius = 10 //radius of the tank

void setup ( ) {


        Serial.begin (9600); 

        btserial.begin(9600);

        pinMode (trigger_pin, OUTPUT); 

        pinMode (echo_pin, INPUT);

        pinMode (10, OUTPUT);




}




void loop ( ) {

    digitalWrite (trigger_pin, HIGH);

    delay(1000);

    digitalWrite (trigger_pin, LOW);

    time = pulseIn (echo_pin, HIGH);

    distance = (time * 0.034) / 2;
if(distance>height)
{
  Serial.println(100); //fuel in percentage
  //Serial.println()
}
else
{
Serial.println(((height-distance)/height)*100); //fuell in percentage
 //Serial.prtintln()
}

  }
