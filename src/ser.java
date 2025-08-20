import java.util.*;
import java.io.*;
import java.net.*;
public class ser{
	public static void main(String [] args) throws Exception{
		PrintWriter out = new PrintWriter(new ServerSocket(4000).accept().getOutputStream(),true);
		BufferedReader in = new BufferedReader(new FileReader("demo.txt"));
		String line; while((line=in.readLine())!=null) out.println(line);
	}
}
