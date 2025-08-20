import java.util.*;
import java.net.*;
import java.io.*;
public class cli{
	public static void main(String [] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(new Socket("localhost",4000).getInputStream()));
		String line; while((line=in.readLine())!=null) System.out.println(line);
	}
}
