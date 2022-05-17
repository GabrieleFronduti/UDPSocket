/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpsocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabbra
 */
public class Server {
    int port = 2000;
    Date d;
    DatagramPacket inPacket;
    DatagramPacket outPacket;
    DatagramSocket dSocket;
    byte[] buffer;
    String messaggioIn, messaggioOut;
    
    public Server(int port){
        try {
            dSocket = new DatagramSocket(this.port);
            System.out.println("Server in ascolto");
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void leggi(){
        buffer = new byte[256];
        inPacket = new DatagramPacket(buffer, buffer.length);
        try {
            dSocket.receive(inPacket);
            InetAddress clientAddress = inPacket.getAddress();
            int clientPort = inPacket.getPort();
            messaggioIn = new String(inPacket.getData(),0, inPacket.getLength());
            System.out.println("Sono il client" + clientAddress + ":" + clientPort + ">" + messaggioIn);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scrivi(){
        d = new Date();
        try {
            messaggioOut = d.toString();
            InetAddress clientAddress = inPacket.getAddress();
            int clientPort = inPacket.getPort();
            outPacket = new DatagramPacket(messaggioOut.getBytes(), messaggioOut.length(), clientAddress, clientPort);
            dSocket.send(outPacket);
            System.out.println("Risposta inviata!");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chiusura(){
        dSocket.close();
        System.out.println("Chiusura");
    }
}
