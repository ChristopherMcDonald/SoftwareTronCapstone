#include <AutomaticPanning.h>

AutomaticPanning* myautopan = new AutomaticPanning();

void setup() 
{
  // put your setup code here, to run once:
  
  Serial.begin(9600);
  myautopan->home_assembly("CW");
}

void loop() 
{
  // put your main code here, to run repeatedly:
  if(!(myautopan->move_to_location(45)))
  {
    Serial.println("Unable to Move to Location 45 deg");
  }

  if(!(myautopan->move_to_location(90)))
  {
    Serial.println("Unable to Move to Location 90 deg");
  }
  
  if(!(myautopan->move_to_location(35)))
  {
    Serial.println("Unable to Move to Location 35 deg");
  }

  Serial.print("\nCurrent Location = ");
  Serial.println(myautopan->get_current_location());
}