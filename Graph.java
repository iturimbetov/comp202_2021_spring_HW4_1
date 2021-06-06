import java.util.ArrayList;

public class Graph {

	public ArrayList<String> vertices;
	public ArrayList<Edge> edges;
	
	public Graph() {
		vertices = new ArrayList<String>();
		edges = new ArrayList<Edge>();
	}
	
	public void addVertex(String v) {
		if (!vertices.contains(v)) vertices.add(v);
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
		if (!vertices.contains(e.src)) {
			vertices.add(e.src);
		}
		if (!vertices.contains(e.dst)) {
			vertices.add(e.dst);
		}
	}
	
	public void removeVertex(String v) {
		vertices.remove(v);
	}
	
	public void removeEdge(Edge e) {
		edges.remove(e);
	}
	
	public void removeVertex(int v_index) {
		vertices.remove(v_index);
	}
	
	public void removeEdge(int e_index) {
		edges.remove(e_index);
	}
	
	// This method will give you an adjacency matrix form
	// give the boolean parameter false to get the costs
	// if it is true you will get latencies instead (this will not be needed in part I)
	public int[][] asArray(boolean latency){
		int size = vertices.size();
		int[][] result = new int[size][size];
		for (Edge e : edges) {
			int value = e.cost;
			if (latency) value = e.latency;
			int src_index = vertices.indexOf(e.src);
			int dst_index = vertices.indexOf(e.dst);
			result[src_index][dst_index] = result[dst_index][src_index] = value;
		}
		return result;
	}
	
	
	
	public ArrayList<Edge> listOfAllEdges(){
		return edges;
	}
	
	public ArrayList<Edge> minSpanningTree(){
		//initialize the spanning tree
		ArrayList<Edge> minSpanningTree = new ArrayList<Edge>();
		//initialize the queue
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(new CustomComparator());
		for(int i=0; i<edges.size(); i++) {
			Edge edge = edges.get(i);
			queue.add(edge);
		}
		//initialize the set
		int[] disjointSet = new int[vertices.size()];
		for(int i=0; i<vertices.size(); i++) {
			disjointSet[i] = -1;
		}
		//calculating the spanning tree
		while(!queue.isEmpty()) {
			Edge edge = queue.remove();
			//finding the end vertices of the edge
			int src = find(vertices.indexOf(edge.src), disjointSet);
			int dst = find(vertices.indexOf(edge.dst), disjointSet);
			//if the vertices are from different partitions join the partitions and add the edge to the spanning tree
			if(src != dst) {
				minSpanningTree.add(edge);
				union(src,dst,disjointSet);
			}
		}
		return minSpanningTree;
	}
	
	public int find(int index, int[] disjointSet) {		
		while(disjointSet[index] >= 0) {
			index = disjointSet[index];
		}
		return index;
	}
	
	public void union(int index1, int index2, int[] disjointSet) {
		if(disjointSet[index1] < disjointSet[index2]) {
			disjointSet[index1] += disjointSet[index2];
			disjointSet[index2] = index1;
		}else {
			disjointSet[index2] += disjointSet[index1];
			disjointSet[index1] = index2;
		}
	}
}

class CustomComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge edge1, Edge edge2) {
        return edge1.cost < edge2.cost ? -1 : 1;
    }
}
