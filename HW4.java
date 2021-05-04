import java.util.Arrays;
import java.util.Scanner;

public class HW4 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Graph graph = new Graph();
				
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] parts = line.split(" ");
			if (parts[0].equals("end")) break;
			String src = parts[0];
			String dst = parts[1];
			int cost = Integer.parseInt(parts[2]);
			int latency = Integer.parseInt(parts[3]);
			
			graph.addVertex(src);
			graph.addVertex(dst);
			Edge edge = new Edge(src, dst, cost, latency);
			graph.addEdge(edge);
		}
		
		//System.out.println(Arrays.deepToString(graph.asArray(false)));
		//System.out.println(Arrays.deepToString(graph.asArray(true)));
		
        HW4 hw4 = new HW4();
		scan.close();
		
        System.out.println(hw4.totalLinkCost(graph));
        System.out.println(hw4.cheapestNetwork(graph.asArray(false)));
        System.out.println(hw4.savedAmount(graph));
	
	}
	
	// You can add any methods you need, both to this file and Graph.java file

	// The method for task 1 
	int totalLinkCost(Graph graph) {
		// TODO Auto-generated method stub
        return 0;
	}

	// The method for task 2 
	int cheapestNetwork(int[][] array_graph) {
		// TODO Auto-generated method stub
		return 0;
	}

	// The method for task 3 
	int savedAmount(Graph graph) {
		// TODO Auto-generated method stub
		return 0;
	}
}
