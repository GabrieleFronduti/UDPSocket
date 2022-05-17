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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Gabbra
 */
public class Client {
    int port = 2000;
    InetAddress serverAddress ;
    DatagramPacket inPacket;
    DatagramPacket outPacket;
    DatagramSocket dSocket;
    byte[] buffer;
    String messaggio = "Richiesta data e ora";
    String response;
    
    public Client(int port){
        try {
            dSocket=new DatagramSocket(this.port);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void scrivi() throws UnknownHostException{
        serverAddress = InetAddress.getLocalHost();		
        System.out.println("Indirizzo del server trovato!");
        outPacket = new DatagramPacket(messaggio.getBytes(), messaggio.length(), serverAddress, port);
        try {
            dSocket.send(outPacket);
            System.out.println("Messaggio inviato"); 
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void risposta(){
        buffer = new byte[256];
        inPacket = new DatagramPacket(buffer, buffer.length);
        try {
            dSocket.receive(inPacket);
            response = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("Data e ora del server: " + response);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void chiusura(){
        dSocket.close();
        System.out.println("Chiusura connessione");
    }
}
