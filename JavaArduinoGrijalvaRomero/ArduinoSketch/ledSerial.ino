int incomingByte = 0;
String dato ="12";
float tempC;
int pinLM35 = 0; 
void setup() {
  // put your setup code here, to run once:
  pinMode(10, OUTPUT);
   Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
   tempC = analogRead(pinLM35); 
   tempC = (5.0 * tempC * 100.0)/1024.0; 
   Serial.println(tempC);
   if (Serial.available() > 0) {
    
    dato = Serial.readString();
    dato.trim();
    if(dato == "1"){
      digitalWrite(10,HIGH);
      Serial.println("prendido");
    }else if(dato == "2"){
      digitalWrite(10,LOW);  
    }else{
      //digitalWrite(10,HIGH);  
    }
    
    //Serial.println(dato);
  }
  delay(1000);
  
}
