#include <AutomaticRoll.h>
#include <AutomaticPanning.h>

AutomaticRoll* myautoroll;
AutomaticPanning* myautopan;

void setup() 
{
  // put your setup code here, to run once:
  myautoroll = new AutomaticRoll();
  myautopan = new AutomaticPanning();
  Serial.begin(9600);
  myautoroll->home_assembly("CW");
}

void loop() 
{
  // put your main code here, to run repeatedly:
  if(!(myautoroll->move_to_location(15)))
  {
    Serial.println("Unable to Move to Location 45 deg");
  }

  if(!(myautoroll->move_to_location(10)))
  {
    Serial.println("Unable to Move to Location 90 deg");
  }
  
  if(!(myautoroll->move_to_location(20)))
  {
    Serial.println("Unable to Move to Location 35 deg");
  }

  Serial.print("\nCurrent Location = ");
  Serial.println(myautoroll->get_current_location());
}
