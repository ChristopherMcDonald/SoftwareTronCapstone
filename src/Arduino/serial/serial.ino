String data;        //variable to store incoming data from JAVA

int LEDpin = 12;  //pin number LED is connected to

void setup() {
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.begin(9600);
  Serial.setTimeout(50);
}

void loop() {
  if(Serial.available()>0){ //if data has been written to the Serial stream
    data=Serial.readString();                 // ex. P=45,Y=-123,Z=1
    int comma1 = data.indexOf(',');           // ex. 4
    String val1 = data.substring(0,comma1);   // ex. P=45

    data = data.substring(comma1 + 1);        // Y=-123,Z=1
    int comma2 = data.indexOf(',');           // 6
    String val2 = data.substring(0,comma2);   // Y=-123

    data = data.substring(comma2 + 1);        // Z=1

    double pitch = val1.substring(2).toDouble();
    double yaw = val2.substring(2).toDouble();
    double vel = data.substring(2).toDouble();

    // works for values 45, -123, 1.2344354353534534432234324

    if(pitch == 42) {
      digitalWrite(LED_BUILTIN, HIGH);
    } else {
      digitalWrite(LED_BUILTIN, LOW);
    }
  }
}
