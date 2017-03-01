package com.company;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Vertex implements Comparable<Vertex> {
    private final String name;
    Edge[] adjacencies;
    double minDistance = Double.POSITIVE_INFINITY;
    Vertex previous;

    Vertex(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }

}


class Edge {
    final Vertex target;
    final double weight;

    Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}

public class ShortestPath {
    private static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);
        while ( !vertexQueue.isEmpty() ) {

            Vertex u = vertexQueue.poll();
            // Last connected vertex
            if ( u.adjacencies == null ) {
                continue;
            }

            // Visit each edge exiting u
            for ( Edge e : u.adjacencies ) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if ( distanceThroughU < v.minDistance ) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    private static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<>();
        for ( Vertex vertex = target; vertex != null; vertex = vertex.previous )
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");

        A.adjacencies = new Edge[] { new Edge(B, 8), new Edge(D, 6) };
        B.adjacencies = new Edge[] { new Edge(C, 11) };
        C.adjacencies = new Edge[] { new Edge(E, 10) };
        D.adjacencies = new Edge[] { new Edge(E, 32) };

        computePaths(A); // run Dijkstra
        System.out.println("Distance to " + E + ": " + E.minDistance);
        List<Vertex> path = getShortestPathTo(E);
        System.out.println("Path: " + path);
    }
}