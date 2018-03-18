#include <AutomaticRoll.h>

AutomaticRoll* myautoroll = new AutomaticRoll();

void setup() 
{
  // put your setup code here, to run once:
  
  Serial.begin(9600);
  myautoroll->home_assembly("CW");
}

void loop() 
{
  // put your main code here, to run repeatedly:
  if(!(myautoroll->move_to_location(45)))
  {
    Serial.println("Unable to Move to Location 45 deg");
  }

  if(!(myautoroll->move_to_location(90)))
  {
    Serial.println("Unable to Move to Location 90 deg");
  }
  
  if(!(myautoroll->move_to_location(35)))
  {
    Serial.println("Unable to Move to Location 35 deg");
  }

  Serial.print("\nCurrent Location = ");
  Serial.println(myautoroll->get_current_location());
}