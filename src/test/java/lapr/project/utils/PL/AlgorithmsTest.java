package lapr.project.utils.PL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author diasd
 */
public class AlgorithmsTest {
  

final Graph<String,Integer> completeMap = new MatrixGraph<>(false);
    Graph<String,Integer> incompleteMap = new MatrixGraph<>(false);

    public AlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() {

        completeMap.addVertex("Porto");
        completeMap.addVertex("Braga");
        completeMap.addVertex("Vila Real");
        completeMap.addVertex("Aveiro");
        completeMap.addVertex("Coimbra");
        completeMap.addVertex("Leiria");

        completeMap.addVertex("Viseu");
        completeMap.addVertex("Guarda");
        completeMap.addVertex("Castelo Branco");
        completeMap.addVertex("Lisboa");
        completeMap.addVertex("Faro");

        completeMap.addEdge("Porto", "Aveiro", 75);
        completeMap.addEdge("Porto", "Braga", 60);
        completeMap.addEdge("Porto", "Vila Real", 100);
        completeMap.addEdge("Viseu", "Guarda", 75);
        completeMap.addEdge("Guarda", "Castelo Branco", 100);
        completeMap.addEdge("Aveiro", "Coimbra", 60);
        completeMap.addEdge("Coimbra", "Lisboa", 200);
        completeMap.addEdge("Coimbra", "Leiria", 80);
        completeMap.addEdge("Aveiro", "Leiria", 120);
        completeMap.addEdge("Leiria", "Lisboa", 150);

        incompleteMap = completeMap.clone();

        completeMap.addEdge("Aveiro", "Viseu", 85);
        completeMap.addEdge("Leiria", "Castelo Branco", 170);
        completeMap.addEdge("Lisboa", "Faro", 280);
    }
    
        private void checkContentEquals(List<String> l1, List<String> l2, String msg) {
        Collections.sort(l1);
        Collections.sort(l2);
        assertEquals(l1, l2, msg);
    }

        /**
     * Test of BreadthFirstSearch method, of class Algorithms.
     */
    @Test
    public void testBreadthFirstSearch() {
        System.out.println("Test BreadthFirstSearch");

        Assertions.assertNull(Algorithms.BreadthFirstSearch(completeMap, "LX"), "Should be null if vertex does not exist");

        LinkedList<String> path = Algorithms.BreadthFirstSearch(incompleteMap, "Faro");

        assertEquals(1, path.size(), "Should be just one");

        assertEquals("Faro", path.peekFirst());

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Porto");
        assertEquals(7, path.size(), "Should give seven vertices");

        assertEquals("Porto", path.removeFirst(), "BreathFirst Porto");

        LinkedList<String> expected = new LinkedList<>(Arrays.asList("Aveiro", "Braga", "Vila Real"));
        checkContentEquals(expected, path.subList(0, 3), "BreathFirst Porto");

        expected = new LinkedList<>(Arrays.asList("Coimbra", "Leiria"));
        checkContentEquals(expected, path.subList(3, 5), "BreathFirst Porto");

        expected = new LinkedList<>(Arrays.asList("Lisboa"));
        checkContentEquals(expected, path.subList(5, 6), "BreathFirst Porto");

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Viseu");
        expected = new LinkedList<>(Arrays.asList("Viseu", "Guarda", "Castelo Branco"));
        assertEquals(expected, path, "BreathFirst Viseu");
    }

    /**
     * Test of shortestPath method, of class Algorithms.
     */
    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");

        LinkedList<String> shortPath = new LinkedList<>();

        Integer lenPath = Algorithms.shortestPath(completeMap, "Porto", "LX", Integer::compare, Integer::sum, 0, shortPath);
        assertNull(lenPath, "Length path should be null if vertex does not exist");
        assertEquals(0, shortPath.size(), "Shortest Path does not exist");

        lenPath = Algorithms.shortestPath(incompleteMap, "Porto", "Faro", Integer::compare, Integer::sum, 0, shortPath);
        assertNull(lenPath, "Length path should be null if vertex does not exist");
        assertEquals(0, shortPath.size(), "Shortest Path does not exist");

        lenPath = Algorithms.shortestPath(completeMap, "Porto", "Porto", Integer::compare, Integer::sum, 0, shortPath);
        assertEquals(0, lenPath, "Length path should be 0 if vertices are the same");
        assertEquals(Arrays.asList("Porto"), shortPath, "Shortest Path only contains Porto");
        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco
        //should change shortest path between Porto and Castelo Branco

        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.addEdge("Leiria", "Castelo Branco", 170);

     
    }

    /**
     * Test of shortestPaths method, of class Algorithms.
     */
    @Test
    public void testShortestPaths() {
        System.out.println("Test of shortest path");

        ArrayList<LinkedList<String>> paths = new ArrayList<>();
        ArrayList<Integer> dists = new ArrayList<>();

        Algorithms.shortestPaths(completeMap, "Porto", Integer::compare, Integer::sum, 0, paths, dists);

        assertEquals(paths.size(), dists.size(), "There should be as many paths as sizes");
        assertEquals(completeMap.numVertices(), paths.size(), "There should be a path to every vertex");
        assertEquals(Arrays.asList("Porto"), paths.get(completeMap.key("Porto")), "Number of nodes should be 1 if source and vertex are the same");
  

        //Changing Edge: Aveiro-Viseu with Edge: Leiria-C.Branco
        //should change shortest path between Porto and Castelo Branco
        completeMap.removeEdge("Aveiro", "Viseu");
        completeMap.addEdge("Leiria", "Castelo Branco", 170);
        Algorithms.shortestPaths(completeMap, "Porto", Integer::compare, Integer::sum, 0, paths, dists);
       

        Algorithms.shortestPaths(incompleteMap, "Porto", Integer::compare, Integer::sum, 0, paths, dists);
        assertNull(dists.get(completeMap.key("Faro")), "Length path should be null if there is no path");
      

        Algorithms.shortestPaths(incompleteMap, "Braga", Integer::compare, Integer::sum, 0, paths, dists);
    }


}