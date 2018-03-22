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
  if(!(mypitch->move_to_location(50)))
  {
    Serial.println("Unable to Move to Location 60 deg");
  }
  Serial.print("\nCurrent Location = ");
  Serial.println(mypitch->get_current_location());
} 

