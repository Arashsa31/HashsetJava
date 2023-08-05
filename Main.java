package LabProgram1412;

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println(countConnectedComponents("src/LabProgram1412/text.txt"));
	}

	public static int countConnectedComponents(String fileName) {
		ArrayList<String> edges = new ArrayList<>();

		try {
			Scanner scn = new Scanner(new File(fileName));

			while (scn.hasNext()) {
				edges.add(scn.nextLine().trim());
			}
			scn.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		ArrayList<HashSet<Integer>> graph = new ArrayList<>();

		// Loop through each edge
		for (String edge : edges) {
			String[] vertices = edge.split(" ");
			Integer v1 = Integer.parseInt(vertices[0]);
			Integer v2 = Integer.parseInt(vertices[vertices.length - 1]);

			HashSet<Integer> component1 = null, component2 = null;
			for (HashSet<Integer> component : graph) {
				if (component.contains(v1)) {
					component1 = component;
				}
				if (component.contains(v2)) {
					component2 = component;
				}
			}

			if (component1 != null && component2 != null) {
				if (component1 != component2) {
					component1.addAll(component2);
					graph.remove(component2);
				}
			} else if (component1 != null) {
				component1.add(v2);
			} else if (component2 != null) {
				component2.add(v1);
			} else {
				HashSet<Integer> newComponent = new HashSet<>();
				newComponent.add(v1);
				newComponent.add(v2);
				graph.add(newComponent);
			}
		}

		return graph.size();
	}
}
//alternative solution
/*
 * public static int countConnectedComponents(String fileName) {
 * 
 * ArrayList<String> edges = new ArrayList<String>();
 * 
 * try { Scanner fs = new Scanner(new File(fileName));
 * 
 * while (fs.hasNext()) edges.add(fs.nextLine().trim()); fs.close(); } catch
 * (IOException e) {
 * 
 * } System.out.println(edges);
 * 
 * ArrayList<Component> graph = new ArrayList<Component>(); /* For each edge
 * compare the edge with each component (of multiple edge) check if any vertex
 * of an edge is in the component if there is, add the edge into the component
 * and continue
 * 
 * add the edge to the components as a new component
 */

// Loop through each edge
/*
 * for (String edge : edges) { String[] vertices = edge.split(" "); Integer v1 =
 * Integer.parseInt(vertices[0]); Integer v2 =
 * Integer.parseInt(vertices[vertices.length - 1]);
 * 
 * // Check if the edge is in any existing component boolean added = false; for
 * (Component component : graph) { if (component.has(v1) || component.has(v2)) {
 * component.add(v1, v2); added = true; break; } }
 * 
 * // If the edge is not in any existing component, create a new component if
 * (!added) { graph.add(new Component(v1, v2)); } }
 * 
 * return graph.size(); }
 * 
 * public static class Component { Set<Integer> vertices = new
 * HashSet<Integer>();
 * 
 * public Component(Integer v1, Integer v2) { add(v1, v2); }
 * 
 * public void add(Integer v1, Integer v2) { vertices.add(v1); vertices.add(v2);
 * }
 * 
 * public boolean has(Integer vertex) { return vertices.contains(vertex); } } }
 */
