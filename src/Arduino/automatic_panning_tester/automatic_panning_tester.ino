#include <AutomaticPanning.h>

AutomaticPanning* myautopan = new AutomaticPanning();

void setup() {
  // put your setup code here, to run once:
  
  Serial.begin(9600);
  myautopan->home_assembly("CW");
}

void loop() {
  // put your main code here, to run repeatedly:
  myautopan->move_to_location(45);
  Serial.print("\nCurrent Location = ");
  Serial.println(myautopan->get_current_location());
  myautopan->move_to_location(90);
  Serial.print("\nCurrent Location = ");
  Serial.println(myautopan->get_current_location());
  myautopan->move_to_location(35);
  Serial.print("\nCurrent Location = ");
  Serial.println(myautopan->get_current_location());
}
