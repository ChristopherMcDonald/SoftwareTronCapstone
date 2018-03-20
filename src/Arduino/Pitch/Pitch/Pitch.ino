#include <Pitch.h>

Pitch* mypitch;

void setup()
{
    mypitch = new Pitch();
    mypitch->home_assembly();
    Serial.begin(9600);
}

void loop()
{
  if(!(mypitch->move_to_location(75)))
  {
    Serial.println("Unable to Move to Location 45 deg");
  }
  Serial.print("\nCurrent Location = ");
  Serial.println(mypitch->get_current_location());
} 

