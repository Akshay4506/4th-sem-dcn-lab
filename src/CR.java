import java.util.*;

public class CR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message bits: ");
        String message = sc.nextLine();

        System.out.print("Enter generator: ");
        String generator = sc.nextLine();

        String appended = message + "0".repeat(generator.length() - 1);
        String remainder = divide(appended, generator);

        String codeword = message + remainder;
        System.out.println("Checksum code: " + codeword);

        System.out.print("\nEnter received code: ");
        String received = sc.nextLine();

        System.out.print("Enter generator again: ");
        String genCheck = sc.nextLine();

        String result = divide(received, genCheck);

        if (result.contains("1"))
            System.out.println("Data stream has errors.");
        else
            System.out.println("Data stream is valid.");
    }

    private static String divide(String dividend, String divisor) {
        char[] rem = dividend.toCharArray();
        int len = divisor.length();

        for(int i=0;i<rem.length-len;i++)
        {
            if(rem[i]=='1')
            {
                for(int j=0;j<len;j++)
                {
                    if(rem[i+j]==divisor.charAt(j))
                    {
                        rem[i+j]='0';
                    }
                    else
                    {
                        rem[i+j]='1';
                    }
                }
            }
    }
        return new String(rem).substring(rem.length-len+1);

}
}
