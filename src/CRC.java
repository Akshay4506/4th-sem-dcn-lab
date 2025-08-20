import java.util.*;

public class CRC{
    static String xor(String a,String b){
        StringBuilder result=new StringBuilder();
        for(int i=1;i<b.length();i++){
            if(a.charAt(i)==b.charAt(i))
                result.append('0');
            else
                result.append('1');
        }
        return result.toString();
    }
    static String mod2div(String dividend,String divisor){
        int pick=divisor.length();
        String tmp=dividend.substring(0,pick);

        while(pick<dividend.length()){
            if(tmp.charAt(0)=='1')
                tmp=xor(divisor,tmp)+dividend.charAt(pick);
            else
                tmp=xor("0".repeat(pick),tmp)+dividend.charAt(pick);
            pick+=1;
        }
        if(tmp.charAt(0)=='1')
            tmp=xor(divisor,tmp);
        else
            tmp=xor("0".repeat(pick),tmp);

        return tmp;
    }
    static String getKey(int[] a, int n) {
        StringBuilder key = new StringBuilder();
        for (int i = a[0]; i >= 0; i--) {
            int x = 0;
            for (int j = 0; j < n; j++) {
                if (i == a[j]) {
                    x = 1;
                    break;
                }
            }
            key.append(x);
        }
        return key.toString();
    }
    static String encodedData(String data,String key){
        int keyLen=key.length();
        String appendData=data+"0".repeat(keyLen-1);
        String remainder=mod2div(appendData,key);
        return data+remainder;
    }
    static void checkedData(String codeword,String key){
        String remainder=mod2div(codeword,key);
        if(remainder.contains("1"))
            System.out.println("Data is corrupted");
        else
            System.out.println("No errors dectected in the data");
    }

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the data :");
        String data=sc.nextLine();

        System.out.print("Enter number of polynomial terms: ");
        int n = sc.nextInt();

        int[] powers = new int[n];
        System.out.print("Enter powers (e.g., for x^4 + x + 1 enter: 4 1 0): ");
        for (int i = 0; i < n; i++) {
            powers[i] = sc.nextInt();
        }

        String key = getKey(powers, n);
        System.out.println("Generated Binary Key: " + key);

        String encoded=encodedData(data,key);
        System.out.print("\nData :"+data);
        System.out.print("\nKey :"+key);
        System.out.println("\nEncoded Codeword :"+encoded);

        System.out.println("\nRecieved Codeword :"+encoded);
        checkedData(encoded,key);

        char lastBit=encoded.charAt(encoded.length()-1);
        char flipped;
        if(lastBit=='1')
            flipped='0';
        else
            flipped='1';

        String modified=encoded.substring(0,encoded.length()-1)+flipped;
        System.out.println("Recieved corrupted codeword :"+modified);
        checkedData(modified,key);
    }
}
