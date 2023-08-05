package LabProgram1412;

import java.util.*;
import java.io.*;

public class HashIntSet {
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