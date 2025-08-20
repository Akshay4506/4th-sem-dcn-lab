import java.io.*;
import java.net.*;

public class Server{
    public static void main(String args[]){
        int port=8080;
        try(ServerSocket serverSocket=new ServerSocket(port)){
            System.out.println("[+]Socket created successfully");
            System.out.println("[+]Binding successful");
            System.out.println("[+]Listening on port :"+port);

            while(true){
                Socket socket=serverSocket.accept();
                System.out.println("[+]Client connected successfully");

                BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out=new PrintWriter(socket.getOutputStream(),true);

                String filename=in.readLine();
                System.out.println("client requested file :"+filename);

                File file=new File(filename);
                if(file.exists() && !file.isDirectory()){
                    BufferedReader fileReader=new BufferedReader(new FileReader(file));
                    String line;
                    while((line=fileReader.readLine())!=null){
                        out.println(line);
                    }
                    fileReader.close();
                }
                else{
                    System.out.println("File not found");
                }
                socket.close();
            }
        }
        catch(IOException e){
            System.out.println("[-]Error occured :"+e.getMessage());
        }
    }
}