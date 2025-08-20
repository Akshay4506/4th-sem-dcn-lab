import java.io.*;
import java.net.*;

public class Client{
    public static void main(String args[]){
        int port=8080;
        String serverAddress="127.0.0.1";

        try(Socket socket=new Socket(serverAddress,port)){
            System.out.println("[+]Server connected successfully");

            BufferedReader userInput=new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);

            System.out.println("enter the filename :");
            String file=userInput.readLine();
            out.println(file);

            String serverResponse;
            while((serverResponse=in.readLine())!=null){
                System.out.println(serverResponse);
            }
            socket.close();
        }
        catch(IOException e){
            System.out.println("[-]Error occured :"+e.getMessage());
        }
    }
}