 
#include <Stepper.h>;
#include <Servo.h>;
Stepper motor(32, 8, 10, 9, 11);
Servo servo1;
Servo servo2;


String grados;
boolean paso=true;
int pasosaDar;
int ubcservo1;
int ubcservo2;
char d;
char p;

void setup() {

  Serial.begin(9600);

  motor.setSpeed(240);
  servo1.attach(3);
  servo2.attach(5);
  ubcservo1=0;
  ubcservo2=0;
  
}

void loop() {
      servo1.write(ubcservo1);
      servo2.write(ubcservo2);
  if(Serial.available()>0){
    //pasosaDar=Serial.parseInt();
    while(Serial.available()){
      char c=Serial.read();
      delay(5);
      if(c=='i'||c=='u'||c=='y'){
        d=c;
      }else {
      grados+=c;
      }
    }
  }
    if(grados.length()>0){
      pasosaDar= grados.toInt();
      Serial.println(pasosaDar);
      if(d=='i'){
      motor.step(pasosaDar*4);
      //pasosaDar*5.68888888888888888888888889
      apagado();
      grados="";
      }
      else {
        if(d=='u'){
        servo1.write(pasosaDar);
        delay(15);
        ubcservo1=pasosaDar;
        grados="";
      }
      else if(d=='y'){
        servo2.write(pasosaDar);
        delay(15);
        ubcservo2=pasosaDar;
        grados="";
      }
      }
    }
  }
void apagado(){
    digitalWrite(8, LOW);
    digitalWrite(9, LOW);
    digitalWrite(10,LOW);
    digitalWrite(11, LOW);
}
  
  void unpaso(){
    digitalWrite(8, HIGH);
    digitalWrite(9, LOW);
    digitalWrite(10,LOW);
    digitalWrite(11, LOW);
    delay(5);
    digitalWrite(8, LOW);
    digitalWrite(9, HIGH);
    digitalWrite(10,LOW);
    digitalWrite(11, LOW);
    delay(5);
    digitalWrite(8, LOW);
    digitalWrite(9, LOW);
    digitalWrite(10,HIGH);
    digitalWrite(11, LOW);
    delay(5);
    digitalWrite(8, LOW);
    digitalWrite(9, LOW);
    digitalWrite(10,LOW);
    digitalWrite(11, HIGH);
    delay(5);
  }
  void unpasoinverso(){
    digitalWrite(8, LOW);
    digitalWrite(9, LOW);
    digitalWrite(10,LOW);
    digitalWrite(11, HIGH);
    delay(5);
    digitalWrite(8, LOW);
    digitalWrite(9, LOW);
    digitalWrite(10,HIGH);
    digitalWrite(11, LOW);
    delay(5);
    digitalWrite(8, LOW);
    digitalWrite(9, HIGH);
    digitalWrite(10,LOW);
    digitalWrite(11, LOW);
    delay(5);
    digitalWrite(8, HIGH);
    digitalWrite(9, LOW);
    digitalWrite(10,LOW);
    digitalWrite(11, LOW);
    delay(5);
  }



  
 /* Motor Paso a Paso ajustado a grados
 by: www.elprofegarcia.com
   
Gira los grados que se le indiquen por el monitor serial o bluetooth

Arduino    Driver ULN200
  8          IN1
  9          IN2
  10         IN3
  11         IN4
  
Tienda en Linea: http://dinastiatecnologica.com/
*/
/*

int retardo=5;          // Tiempo de retardo en milisegundos (Velocidad del Motor)
int dato_rx;            // valor recibido en grados
int numero_pasos = 0;   // Valor en grados donde se encuentra el motor
String leeCadena;       // Almacena la cadena de datos recibida

void setup() {                
Serial.begin(9600);     // inicializamos el puerto serie a 9600 baudios
pinMode(11, OUTPUT);    // Pin 11 conectar a IN4
pinMode(10, OUTPUT);    // Pin 10 conectar a IN3
pinMode(9, OUTPUT);     // Pin 9 conectar a IN2
pinMode(8, OUTPUT);     // Pin 8 conectar a IN1
}

void loop() {
 /* while (Serial.available()) {    // Leer el valor enviado por el Puerto serial
    delay(retardo);
    char c  = Serial.read();     // Lee los caracteres
    leeCadena += c;              // Convierte Caracteres a cadena de caracteres
  }  
  if (leeCadena.length()>0){       
        dato_rx = leeCadena.toInt();   // Convierte Cadena de caracteres a Enteros
         Serial.print(dato_rx);         // Envia valor en Grados 
         Serial.println(" Grados");
        delay(retardo);
        dato_rx = (dato_rx * 1.4222222222); // Ajuste de 512 vueltas a los 360 grados
  }  

   while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados*/
       //paso_izq();
       //paso_der();
      /* numero_pasos = numero_pasos + 1;
   }
   while (dato_rx<numero_pasos){   // Giro hacia la derecha en grados
        paso_der();
        numero_pasos = numero_pasos -1;
   }
  leeCadena = "";   // Inicializamos la cadena de caracteres recibidos 
  
  apagado();         // Apagado del Motor para que no se caliente
  
}  ///////////////////// Fin del Loop ///////////////////////////

void paso_der(){         // Pasos a la derecha
 digitalWrite(11, LOW); 
 digitalWrite(10, LOW);  
 digitalWrite(9, HIGH);  
 digitalWrite(8, HIGH);  
   delay(retardo); 
 digitalWrite(11, LOW); 
 digitalWrite(10, HIGH);  
 digitalWrite(9, HIGH);  
 digitalWrite(8, LOW);  
   delay(retardo); 
 digitalWrite(11, HIGH); 
 digitalWrite(10, HIGH);  
 digitalWrite(9, LOW);  
 digitalWrite(8, LOW);  
  delay(retardo); 
 digitalWrite(11, HIGH); 
 digitalWrite(10, LOW);  
 digitalWrite(9, LOW);  
 digitalWrite(8, HIGH);  
  delay(retardo);  
}

void paso_izq() {        // Pasos a la izquierda
 digitalWrite(11, HIGH); 
 digitalWrite(10, HIGH);  
 digitalWrite(9, LOW);  
 digitalWrite(8, LOW);  
  delay(retardo); 
 digitalWrite(11, LOW); 
 digitalWrite(10, HIGH);  
 digitalWrite(9, HIGH);  
 digitalWrite(8, LOW);  
  delay(retardo); 
 digitalWrite(11, LOW); 
 digitalWrite(10, LOW);  
 digitalWrite(9, HIGH);  
 digitalWrite(8, HIGH);  
  delay(retardo); 
 digitalWrite(11, HIGH); 
 digitalWrite(10, LOW);  
 digitalWrite(9, LOW);  
 digitalWrite(8, HIGH);  
  delay(retardo); 
}
        
void apagado() {         // Apagado del Motor
 digitalWrite(11, LOW); 
 digitalWrite(10, LOW);  
 digitalWrite(9, LOW);  
 digitalWrite(8, LOW);  
 }
 */
