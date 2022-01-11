
package pruebasocketstcp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        ServerSocket sSocket = generarSocket();
        Socket s;
        while (true){
            s = aceptSocket(sSocket);
            Thread hilo = new Thread(new ServerThread(s));
            hilo.start();
        }
        
    }
    
    private static ServerSocket generarSocket(){
        ServerSocket sSocket = null;        
        try {
            sSocket = new ServerSocket(20001);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sSocket;
    } 
    
    private static Socket aceptSocket(ServerSocket ss){
        Socket s = null;
        try {
            s = ss.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
}
