import java.util.*;

public class BFA {
    public static int[] bAlgo(int V, int src, int edges[][]) {
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < V; i++) {
            for (int edge[] : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                    if (i == V - 1) {
                        return new int[] { -1 };
                    }
                    dist[v] = dist[u] + wt;
                }
            }
        }
        return dist;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the no.of vertices :");
        int V = sc.nextInt();
        System.out.println("Enter the no.of edges :");
        int e = sc.nextInt();

        System.out.println("Enter the edges :");
        int edges[][] = new int[e][3];
        for (int i = 0; i < e; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
        }

        System.out.println("Enter the source vertex :");
        int src = sc.nextInt();

        int[] res = bAlgo(V, src, edges);
        if (res.length == 1 && res[0] == -1) {
            System.out.println("Negative Cycle occured");
        } else {
            System.out.println("Shortest Distances :");
            for (int i = 0; i < res.length; i++) {
                if (res[i] == Integer.MAX_VALUE) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(res[i] + "\t");
                }
            }
            System.out.println();
        }
    }
}