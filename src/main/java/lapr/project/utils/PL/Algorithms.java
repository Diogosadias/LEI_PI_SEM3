package lapr.project.utils.PL;

import lapr.project.model.City;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.BinaryOperator;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;

/**
 *
 * @author diasd
 */
public class Algorithms {

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights This implementation
     * uses Dijkstra's algorithm
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig,
            Comparator<E> ce, BinaryOperator<E> sum, E zero,
            boolean[] visited, V[] pathKeys, E[] dist) {

        int vkey = g.key(vOrig);
        dist[vkey] = zero;
        pathKeys[vkey] = vOrig;
        while (vOrig != null) {
            vkey = g.key(vOrig);
            visited[vkey] = true;
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
                int vkeyAdj = g.key(edge.getVDest());
                if (!visited[vkeyAdj]) {
                    E s = sum.apply(dist[vkey], edge.getWeight());
                    if (dist[vkeyAdj] == null || ce.compare(dist[vkeyAdj], s) > 0) {
                        dist[vkeyAdj] = s;
                        pathKeys[vkeyAdj] = vOrig;
                    }
                }
            }
            E minDist = null;
            vOrig = null;
            for (V vert : g.vertices()) {
                int i = g.key(vert);
                if (!visited[i] && dist[i] != null && (minDist == null || ce.compare(dist[i], minDist) < 0)) {
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }

    }

    /**
     * Shortest-path between two vertices
     *
     * @param g graph
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @param zero neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false
     * otherwise
     */
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest,
            Comparator<E> ce, BinaryOperator<E> sum, E zero,
            LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return null;
        }

        shortPath.clear();
        int numVerts = g.numVertices();
        boolean[] visited = new boolean[numVerts];
        @SuppressWarnings("unchecked")
        V[] pathKeys = (V[]) new Object[numVerts];
        @SuppressWarnings("unchecked")
        E[] dist = (E[]) new Object[numVerts];
        for (int i = 0; i < numVerts; i++) {
            dist[i] = null;
            pathKeys[i] = null;
        }
        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);
        E lengthPath = dist[g.key(vDest)];
        if (lengthPath == null) {
            return null;
        }

        getPath(g, vOrig, vDest, pathKeys, shortPath);
        return lengthPath;
    }

    /**
     * Shortest-path between a vertex and all other vertices
     *
     * @param g graph
     * @param vOrig start vertex
     * @param ce comparator between elements of type E
     * @param sum sum two elements of type E
     * @param zero neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
            Comparator<E> ce, BinaryOperator<E> sum, E zero,
            ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {

        if (!g.validVertex(vOrig)) {
            return false;
        }

        int numVerts = g.numVertices();
        boolean[] visited = new boolean[numVerts];
        @SuppressWarnings("unchecked")
        V[] pathKeys = (V[]) new Object[numVerts];
        @SuppressWarnings("unchecked")
        E[] dist = (E[]) new Object[numVerts];
        for (int i = 0; i < numVerts; i++) {
            dist[i] = null;
            pathKeys[i] = null;
        }
        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);
        dists.clear();
        paths.clear();
        for (int i = 0; i < numVerts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (V vDst : g.vertices()) {
            int i = g.key(vDst);
            if (dist[i] != null) {
                LinkedList<V> shortPath = new LinkedList<>();
                getPath(g, vOrig, vDst, pathKeys, shortPath);
                paths.set(i, shortPath);
                dists.set(i, dist[i]);
            }
        }
        return true;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */

    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V [] pathKeys, LinkedList<V> path) {
        if (vOrig.equals(vDest)) {
            path.push(vOrig);
        } else {
            path.push(vDest);
            int vKey = g.key(vDest);
            vDest = pathKeys[vKey];
            getPath(g, vOrig, vDest, pathKeys, path);
        }
    }

    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (g.adjVertices(vert) == null) {
            return null;
        }
        int numVertices = g.numVertices();
        boolean[] visited = new boolean[numVertices];
        LinkedList<V> qaux = new LinkedList<>();
        LinkedList<V> qbfs = new LinkedList<>();
        qaux.add(vert);
        qbfs.add(vert);
        visited[g.key(vert)] = true;

        while (!qaux.isEmpty()) {
            vert = qaux.poll();

            for (V vAdj : g.adjVertices(vert)) {
                int adjKey = g.key(vAdj);
                if (!visited[adjKey]) {
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                    visited[adjKey] = true;
                }

            }
        }
        return qbfs;

    }


    public static void findCircuit(Object land, MatrixGraph matrixGraph, ArrayList<Object> list, int[] color) {
        ArrayList<Object> adjList = (ArrayList<Object>) matrixGraph.adjVertices(land);
        boolean allVisited = check(adjList,color,matrixGraph);
        adjList = getClosestAdj(land,matrixGraph,adjList); //get Closest


        if(!allVisited) {
            //Cicle Foward
            for (int i = 0; i < adjList.size(); i++) {
                Object v = adjList.get(i);
                if (color[matrixGraph.key(v)] == 0) {
                    list.add(v);
                    color[matrixGraph.key(v)] = 1;
                    findCircuit(v, matrixGraph, list, color);
                    return;
                } else if (color[matrixGraph.key(v)] == 1) {

                }

            }
        } else if(adjList.contains(list.get(0))){
            list.add(list.get(0));
            return;
        } else{
            list.remove(list.size()-1);
            return;
        }
    }

    public static ArrayList<Object> getClosestAdj(Object land, MatrixGraph matrixGraph, ArrayList<Object> adjList) {
        ArrayList<Object> list = new ArrayList<>();

        //get Min add
        for(int j = 0; j<adjList.size();j++) {
            Double min = MAX_VALUE;
            int index = 0;
            for (int i = 0; i < adjList.size(); i++) {
                if (adjList.get(i) == null ) {

                } else {
                    Edge e = matrixGraph.edge(land, adjList.get(i));

                    Double lol = (Double) e.getWeight();
                    if (lol < min) {
                        min = (Double) matrixGraph.edge(land, adjList.get(i)).getWeight();
                        index = i;
                    }
                }
            }
            list.add(adjList.get(index));
            adjList.set(index, null);
        }



        return list;
    }

    private static boolean check(ArrayList<Object> adjList, int[] color, MatrixGraph matrixGraph) {
        for(Object v : adjList){
            if(color[matrixGraph.key(v)]==0) return false;
        }
        return true;
    }
}
