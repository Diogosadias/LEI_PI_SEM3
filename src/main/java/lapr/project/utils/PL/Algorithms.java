package lapr.project.utils.PL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.BinaryOperator;

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
                if (visited[vkeyAdj]) {
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
   /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
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

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g Graph instance
     * @param vOrig vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs return LinkedList with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        int vOrigKey = g.key(vOrig);
        if (visited[vOrigKey]) {
            return;
        }
        qdfs.push(vOrig);
        visited[vOrigKey] = true;

        for (V vAdj : g.adjVertices(vOrig)) {
            DepthFirstSearch(g, vAdj, visited, qdfs);
            return;
        }
    }

    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {
        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();
        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }
        return pathrev;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param vDest Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path stack with vertices of the current path (the path is in
     * reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
            LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
        path.push(vOrig);
        int vKey = g.key(vOrig);
        visited[vKey] = true;
        for (V vAdj : g.adjVertices(vOrig)) {
            if (vAdj.equals(vDest)) {
                path.push(vAdj);
                LinkedList<V> revpath = revPath(path);
                paths.add(new LinkedList(revpath));
                path.pop();
            } else {
                vKey = g.key(vAdj);
                if (!visited[vKey]) {
                    allPaths(g, vAdj, vDest, visited, path, paths);
                }

            }
        }
        V vElem = path.pop();
        vKey = g.key(vElem);
        visited[vKey] = false;
    }

    /**
     * @param g Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from voInf to vdInf
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {
        if (g.validVertex(vOrig) && g.validVertex(vDest)) {
            LinkedList<V> path = new LinkedList();
            ArrayList<LinkedList<V>> paths = new ArrayList();
            boolean[] visited = new boolean[g.numVertices()];
            allPaths(g, vOrig, vDest, visited, path, paths);
            return paths;
        }
        return null;
    }
}
