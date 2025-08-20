import java.util.*;

public class LeakyBucket{
    int bucketSize;
    int outFlow;
    Queue<Integer> bucket;
    public LeakyBucket(int bucketSize,int outFlow){
        this.bucketSize=bucketSize;
        this.outFlow=outFlow;
        this.bucket=new LinkedList<>();
    }
    void addPacket(int packets){
        if(bucket.size()+packets <= bucketSize){
            for(int i=0;i<packets;i++){
                bucket.add(1);
            }
        }
        else{
            System.out.println("Couldn't fit into bucket");
            return;
        }
        System.out.println("Added "+packets+" packets to bucket");
    }
    void transmitPacket(){
        for(int i=0;i<outFlow;i++){
            if(!bucket.isEmpty()){
                bucket.poll();
            }
            else{
                System.out.println("all packets are transmitted");
                System.out.println("No packets to transmit");
                return;
            }
        }
        System.out.println("Transmitted "+outFlow+" packets");
    }
    void checkStatus(){
        System.out.println("Bucket status :"+bucket.size()+"/"+bucketSize);
    }

    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the max bucket size :");
        int bucketsize=sc.nextInt();
        System.out.print("Enter the outflow rate of the bucket :");
        int outflow=sc.nextInt();

        LeakyBucket lb=new LeakyBucket(bucketsize,outflow);

        while(true){
            System.out.println("\n1.Add packets\n2.Transmit packets\n3.Check Status\n4.Exit\n");
            System.out.print("enter any option above :");
            int n=sc.nextInt();

            switch(n){
                case 1:
                    System.out.print("Enter packets to add :");
                    int packets=sc.nextInt();
                    lb.addPacket(packets);
                    break;
                case 2:
                    lb.transmitPacket();
                    break;
                case 3:
                    lb.checkStatus();
                    break;
                case 4:
                    System.out.println("Exiting....");
                    return;
                default:
                    System.out.println("wrong option entered!! try again");
            }
        }
    }
}