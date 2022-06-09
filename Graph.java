/**
 * Graph.java
 * @author	Jenny Deng
 * @author	Yupeng Ni
 * CIS 22C, Lab 6
 */

import java.util.ArrayList;

public class Graph {
	private int vertices;
	private int edges;
	private ArrayList<LinkedList<Integer>> adj;
	private ArrayList<Character> color;
	private ArrayList<Integer> distance;
	private ArrayList<Integer> parent;
	private ArrayList<Integer> discoverTime;
	private ArrayList<Integer> finishTime;
	private static int time = 0;

	/** Constructors and Destructors */

	/**
	 * initializes an empty graph, with n vertices and 0 edges
	 * 
	 * @param n the number of vertices in the graph
	 * @precondition n > 0
	 * @throws IllegalArgumentException when n <= 0
	 */
	public Graph(int n) throws IllegalArgumentException {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}

		vertices = n;
		edges = 0;

		adj = new ArrayList<LinkedList<Integer>>();
		color = new ArrayList<Character>();
		distance = new ArrayList<Integer>();
		parent = new ArrayList<Integer>();
		discoverTime = new ArrayList<Integer>();
		finishTime = new ArrayList<Integer>();

		for (int i = 0; i <= n; i++) {
			adj.add(i, new LinkedList<Integer>());
			color.add('W');
			distance.add(-1);
			parent.add(0);
			discoverTime.add(-1);
			finishTime.add(-1);
		}

	}

	/*** Accessors ***/

	/**
	 * Returns the number of edges in the graph
	 * 
	 * @return the number of edges
	 */
	public int getNumEdges() {
		return edges;
	}

	/**
	 * Returns the number of vertices in the graph
	 * 
	 * @return the number of vertices
	 */
	public int getNumVertices() {
		return vertices;
	}

	/**
	 * returns whether the graph is empty (no edges)
	 * 
	 * @return whether the graph is empty
	 */
	public boolean isEmpty() {
		return edges == 0;
	}

	/**
	 * Returns the value of the distance[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the distance of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Integer getDistance(Integer v) throws IndexOutOfBoundsException {

		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException();
		}

		return distance.get(v);
	}

	/**
	 * Returns the value of the parent[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the parent of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Integer getParent(Integer v) throws IndexOutOfBoundsException {

		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException();
		}

		return parent.get(v);
	}

	/**
	 * Returns the value of the color[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the color of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Character getColor(Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException();
		}

		return color.get(v);
	}

	/**
	 * Returns the value of the discoverTime[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the discover time of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Integer getDiscoverTime(Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException();
		}

		return discoverTime.get(v);
	}

	/**
	 * Returns the value of the finishTime[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the finish time of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Integer getFinishTime(Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException();
		}

		return finishTime.get(v);
	}

	/**
	 * Returns the LinkedList stored at index v
	 * 
	 * @param v a vertex in the graph
	 * @return the adjacency LinkedList a v
	 * @precondition 0 < v <= vertices
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public LinkedList<Integer> getAdjacencyList(Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices) {
			throw new IndexOutOfBoundsException();
		}

		return adj.get(v);
	}

	/*** Manipulation Procedures ***/

	/**
	 * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into the
	 * list at index u) @precondition, 0 < u, v <= vertices
	 * 
	 * @throws IndexOutOfBounds exception when u or v is out of bounds
	 */
	public void addDirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices || u <= 0 || u > vertices) {
			throw new IndexOutOfBoundsException();
		}

		adj.get(u).addLast(v);
		edges++;
	}

	/**
	 * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into the
	 * list at index u) and inserts u into the adjacent vertex list of
	 * v @precondition, 0 < u, v <= vertices
	 * 
	 * @throws IndexOutOfBoundsException when u or v is out of bounds
	 */
	public void addUndirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
		if (v <= 0 || v > vertices || u <= 0 || u > vertices) {
			throw new IndexOutOfBoundsException();
		}

		adj.get(u).addLast(v);
		adj.get(v).addLast(u);
		edges++;
	}

	/*** Additional Operations ***/

	/**
	 * Creates a String representation of the Graph Prints the adjacency list of
	 * each vertex in the graph, vertex: <space separated list of adjacent vertices>
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= vertices; i++) {
			sb.append(i).append(": ").append(adj.get(i).toString());
		}
		return sb.toString();
	}

	/**
	 * Performs breath first search on this Graph give a source vertex
	 * 
	 * @param source the starting vertex
	 * @precondition source is a vertex in the graph
	 * @throws IndexOutOfBoundsException when the source vertex is out of bounds of
	 *                                   the graph
	 */

	public void BFS(Integer source) throws IndexOutOfBoundsException {

		if (source <= 0 || source > vertices) {
			throw new IndexOutOfBoundsException();
		}

		color.set(source, 'G');
		distance.set(source, 0);

		LinkedList<Integer> q = new LinkedList<Integer>();
		q.addLast(source);
		while (!q.isEmpty()) {

			int x = q.getFirst();
			q.removeFirst();

			LinkedList<Integer> adjList = adj.get(x);
			adjList.positionIterator();
			
			while(!adjList.offEnd()) {
				int y = adjList.getIterator();
				if (color.get(y).equals('W')) {
					color.set(y, 'G');
					distance.set(y, distance.get(x) + 1);
					parent.set(y, x);
					q.addLast(y);
				}
				adjList.advanceIterator();
			}

			color.set(x, 'B');

		}

	}

	/**
	 * Performs depth first search on this Graph in order of vertex lists
	 */
	public void DFS() {
		time = 0;

		for (int i = 1; i < vertices; i++) {
			if (color.get(i).equals('W')) {
				visit(i);
			}
		}

	}

	/**
	 * Private recursive helper method for DFS
	 * 
	 * @param vertex the vertex to visit
	 */
	private void visit(int vertex) {
		color.set(vertex, 'G');
		discoverTime.set(vertex, ++time);

		LinkedList<Integer> adjList = adj.get(vertex);

		adjList.positionIterator();
		while (!adjList.offEnd()) {
    		Integer y = adjList.getIterator();
			if (color.get(y) == 'W') {
				parent.set(y, vertex);
				visit(y);
			}
			adjList.advanceIterator();
    	}
		
		color.set(vertex, 'B');
		finishTime.set(vertex, ++time);

	}

}