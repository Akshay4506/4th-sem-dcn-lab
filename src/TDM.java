import java.util.Scanner;

public class TDM {
    public static void main(String args[]) {
        int n, qt, count = 0, sq = 0;
        int[] bt = new int[10];       // Processing time (burst time) for each station
        int[] wt = new int[10];       // Waiting time
        int[] tat = new int[10];      // Turnaround time
        int[] rem_bt = new int[10];   // Remaining burst time (for Round Robin)
        float awt = 0, atat = 0;      // Average waiting and turnaround times

        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of stations (maximum 10): ");
        n = s.nextInt();

        // Input processing time for each station
        System.out.println("Enter the processing time for each station:");
        for (int i = 0; i < n; i++) {
            System.out.print("S" + (i + 1) + " = ");
            bt[i] = s.nextInt();
            rem_bt[i] = bt[i];  // Copy initial burst time to remaining burst time
        }

        // Input frame size (time quantum for each station)
        System.out.print("Enter the frame size: ");
        qt = s.nextInt();

        // Round Robin simulation
        while (true) {
            count = 0;
            for (int i = 0; i < n; i++) {
                int temp = qt;

                if (rem_bt[i] == 0) {
                    count++;
                    continue;  // Skip completed stations
                }
                if (rem_bt[i] > qt) {
                    rem_bt[i] -= qt;
                    sq += qt;
                } else {
                    sq += rem_bt[i];
                    rem_bt[i] = 0;
                }
                tat[i] = sq;  // Set turnaround time
            }
            if (count == n)
                break;  // Exit when all stations are done
        }

        System.out.println("Station\tbt\ttat\twt");
        for (int i = 0; i < n; i++) {
            wt[i] = tat[i] - bt[i];  // Waiting time = TAT - burst time
            awt += wt[i];            // Add to total waiting time
            atat += tat[i];          // Add to total turnaround time
            System.out.println("S" + (i + 1) + "\t\t" + bt[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.printf("Average Waiting Time = %.2f\n", awt / n);
        System.out.printf("Average Turnaround Time = %.2f\n", atat / n);
    }
}