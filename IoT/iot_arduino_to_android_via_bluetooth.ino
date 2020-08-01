#include<SoftwareSerial.h>
SoftwareSerial BTserial(0,1);
const int trigPin=12;
const int echoPin=11;

void setup() {
  pinMode(trigPin,OUTPUT);
  pinMode(echoPin,INPUT);
  pinMode(13,OUTPUT);
  BTserial.begin(9600);
 /* while(BTserial.available()==0)
 {BTserial.print(5);
 delay(2000);}*/
 
}

void loop() {

  float duration,distance;
  digitalWrite(trigPin,LOW);
  delayMicroseconds(100);
  digitalWrite(trigPin,LOW);
  digitalWrite(trigPin,HIGH);
  delayMicroseconds(5000);
  digitalWrite(trigPin,LOW);
  duration=pulseIn(echoPin,HIGH);
  distance=duration/29/2;
  
  Serial.println(distance);
 
}
