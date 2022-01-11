package pruebasocketstcp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread implements Runnable {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = getReader(socket);
        BufferedWriter bw = getWriter(socket);
        
        System.out.println(recibirMensaje(br));
        enviarMensaje(bw, "Respuesta del server");
        cerrarCanales(br, bw, socket);
    }

    private static BufferedReader getReader(Socket s) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return br;
    }

    private static BufferedWriter getWriter(Socket s) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bw;
    }

    private static void enviarMensaje(BufferedWriter bw, String mensaje) {
        try {
            bw.write(mensaje + "\n");
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String recibirMensaje(BufferedReader br) {

        String mensaje = "";
        try {
            mensaje = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }

    private static void cerrarCanales(BufferedReader br, BufferedWriter bw, Socket s) {
        try {
            br.close();
            bw.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
