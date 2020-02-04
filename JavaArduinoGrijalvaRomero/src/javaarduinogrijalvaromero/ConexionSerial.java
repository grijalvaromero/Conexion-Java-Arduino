/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduinogrijalvaromero;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
/**
 *
 * @author grijalvaromero
 */
public class ConexionSerial implements SerialPortEventListener {

    CommPortIdentifier portId;//guarda el Id del Puerto
    Enumeration cuantosPuertos;//guarda la lista de los puertos Disponibles
    InputStream input=null;//Canalito por donde llegaran los datos    
    OutputStream output=null;//Escribe en el puero SERIAL
    HashMap portMap=new HashMap();//Guarda la info del arduino
    SerialPort serialPort=null;//el puerto al que me conectaré
    boolean conectado=false;//SI ESTA CONECTADO
    public static String todo="",dato="";
   
    //EL CONSTRUCTOR DE LA CLASE que se tiene que llamar
    //IGUAL QUE LA CLAAAAASEEEEEEEEEEEEEEEEEEE
   
    
    public void initListener(){
        try{
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            
        }catch(Exception E){
            System.out.println("Error al leer");
            System.out.println(E);
        }
       
    }
    public String getTodo(){
        return dato;
        
    }
    public void serialEvent(SerialPortEvent spe) {
       
        if(spe.getEventType() == SerialPortEvent.DATA_AVAILABLE){
            try{
                byte datoSimple=(byte) input.read();
                if(datoSimple != 10){
                    String texto= new String(new byte[]{datoSimple} );
                    todo+=texto;
                }else{
                   System.out.println(todo);
                   // Principal.txtTemperatura.setText(Principal.txtTemperatura.getText()+"\n"+Principal.objeto.getTodo());
                   dato=todo;
                    todo="";
                }
            }catch(Exception e){
                System.out.println("ERROR lectura: "+e.getMessage());
            }//llave catch
        }//llave if
        else{
            
        }
    }
    //Función para listar los puertos disponibles
    //funcion del tipo no recibe; si regresa
    public String obtenerLista(){
        //nos trae la cantidad de puertos disponibles 
        cuantosPuertos=CommPortIdentifier.getPortIdentifiers();
       String todo="";
        //recorremos la lista de los puertos siponibles
        while(cuantosPuertos.hasMoreElements()){
            //el puerto solito
            CommPortIdentifier solito=(CommPortIdentifier)cuantosPuertos.nextElement();
            if(solito.getPortType()==CommPortIdentifier.PORT_SERIAL){
                portMap.put(solito.getName(),solito);
                todo+=solito.getName()+",";
                //com1,com2,com3,
                
                System.out.println("Puerto encontrado: "+solito.getName());
            }
        }//LLAVE WHILE
        return todo;
    }//LLAVE OBTENER LISTa 
    public void conectar(String cualPuerto){
        portId=(CommPortIdentifier) portMap.get(cualPuerto);
        CommPort  commport=null;
        try{
            commport=portId.open("TigerControlPanel",1000);
            //cast
            serialPort=(SerialPort) commport;
           serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            setConectado(true);
            System.out.println("CONECTADOTE");
            
        }catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }//llave conectar
    public void setConectado(boolean estado){
        this.conectado=estado;
    }//llave setConectado
    public boolean getConectado(){
        return this.conectado;
    }//llave getConectado
    public void desconectar(){
        try{
            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();
            System.out.println("DESCONECTADO");
            setConectado(false);
            
        }catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }//llave desconectar
    public boolean iniciarIO(){
        boolean exito=false;
         try{
             input=serialPort.getInputStream();
             output=serialPort.getOutputStream();
             exito=true;
             return true;
         }catch(Exception yea){
             System.out.println("ERROR AL INICIAR IO "+yea.toString());
             exito=false;
         }//llave catch
        return exito;
    }//llave io
    
    public void escribir(int _texto){
        try{
            output.write( (_texto+"").getBytes());
            output.flush();
        }catch(Exception uuuuu){
            System.out.println(uuuuu.getMessage()+"1111");
        }
    }//llave escribir
}