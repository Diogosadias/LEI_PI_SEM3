package lapr.project.utils.PL;

import lapr.project.model.PortTree;
import oracle.ucp.util.Pair;
import lapr.project.model.City;
import lapr.project.model.Port;

import java.io.IOException;
import java.util.*;

import static lapr.project.model.Ship.dist;

/**
 * @author DEI-ISEP
 */
public class MatrixGraph<V, E> extends CommonGraph<V, E> {

    public static final int INITIAL_CAPACITY = 10;
    public static final float RESIZE_FACTOR = 1.5F;

    Edge<V, E>[][] edgeMatrix;


    @SuppressWarnings("unchecked")
    public MatrixGraph(boolean directed, int initialCapacity) {
        super(directed);
        edgeMatrix = (Edge<V, E>[][]) (new Edge<?, ?>[initialCapacity][initialCapacity]);
    }

    public MatrixGraph(boolean directed) {
        this(directed, INITIAL_CAPACITY);
    }

    public MatrixGraph(Graph<V, E> g) {
        this(g.isDirected(), g.numVertices());
        copy(g, this);
    }

    public MatrixGraph(boolean directed, ArrayList<V> vs, E[][] m) {
        this(directed, vs.size());
        numVerts = vs.size();
        vertices = new ArrayList<>(vs);
        for (int i = 0; i < numVerts; i++)
            for (int j = 0; j < numVerts; j++)
                if (j != i && m[i][j] != null)
                    addEdge(vertices.get(i), vertices.get(j), m[i][j]);
    }

    @Override
    public Collection<V> adjVertices(V vert) {
        int index = key(vert);
        if (index == -1)
            return null;

        ArrayList<V> outVertices = new ArrayList<>();
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[index][i] != null)
                outVertices.add(vertices.get(i));
        return outVertices;
    }

    @Override
    public Collection<Edge<V, E>> edges() {
        Collection<Edge<V, E>> edges = new ArrayList<>();
        for (Edge<V, E>[] e : edgeMatrix) {
            for (Edge<V, E> edge : e) {
                if (edge != null) edges.add(edge);
            }
        }
        return edges;
    }

    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {
        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        if ((vOrigKey < 0) || (vDestKey < 0))
            return null;

        return edgeMatrix[vOrigKey][vDestKey];
    }

    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        if (vOrigKey >= numVerts && vDestKey >= numVerts)
            return null;
        return edgeMatrix[vOrigKey][vDestKey];
    }

    @Override
    public int outDegree(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return -1;

        int edgeCount = 0;
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[vertKey][i] != null)
                edgeCount++;
        return edgeCount;
    }

    @Override
    public int inDegree(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return -1;

        int edgeCount = 0;
        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[i][vertKey] != null)
                edgeCount++;
        return edgeCount;
    }

    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {
        Collection<Edge<V, E>> ce = new ArrayList<>();
        int vertKey = key(vert);
        if (vertKey == -1)
            return ce;

        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[vertKey][i] != null)
                ce.add(edgeMatrix[vertKey][i]);
        return ce;
    }

    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {
        Collection<Edge<V, E>> ce = new ArrayList<>();
        int vertKey = key(vert);
        if (vertKey == -1)
            return ce;

        for (int i = 0; i < numVerts; i++)
            if (edgeMatrix[i][vertKey] != null)
                ce.add(edgeMatrix[i][vertKey]);
        return ce;
    }

    @Override
    public boolean addVertex(V vert) {
        int vertKey = key(vert);
        if (vertKey != -1)
            return false;

        vertices.add(vert);
        numVerts++;
        resizeMatrix();
        return true;
    }

    public boolean addVertices(List<V> list){
        boolean flag = true;
        for (V v:list) {
            if(!addVertex(v)) flag = false;
        }
        return flag;
    }

    /**
     * Resizes the matrix when a new vertex increases the length of ArrayList
     */
    private void resizeMatrix() {
        if (edgeMatrix.length < numVerts) {
            int newSize = (int) (edgeMatrix.length * RESIZE_FACTOR);

            @SuppressWarnings("unchecked")
            Edge<V, E>[][] temp = (Edge<V, E>[][]) new Edge<?, ?>[newSize][newSize];
            for (int i = 0; i < edgeMatrix.length; i++)
                temp[i] = Arrays.copyOf(edgeMatrix[i], newSize);
            edgeMatrix = temp;
        }
    }

    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) {
        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        edgeMatrix[vOrigKey][vDestKey] = new Edge<>(vOrig, vDest, weight);
        numEdges++;
        if (!isDirected) {
            edgeMatrix[vDestKey][vOrigKey] = new Edge<>(vDest, vOrig, weight);
            numEdges++;
        }
        return true;
    }

    @Override
    public boolean removeVertex(V vert) {
        int vertKey = key(vert);
        if (vertKey == -1)
            return false;

        // first let's remove edges from the vertex
        for (int i = 0; i < numVerts; i++)
            removeEdge(vertKey, i);
        if (isDirected) {
            // first let's remove edges to the vertex
            for (int i = 0; i < numVerts; i++)
                removeEdge(i, vertKey);
        }

        // remove shifts left all vertices after the one removed
        // It is necessary to collapse the edge matrix
        for (int i = vertKey; i < numVerts - 1; i++) {
            for (int j = 0; j < numVerts; j++) {
                edgeMatrix[i][j] = edgeMatrix[i + 1][j];
            }
        }
        for (int i = vertKey; i < numVerts - 1; i++) {
            for (int j = 0; j < numVerts; j++) {
                edgeMatrix[j][i] = edgeMatrix[j][i + 1];
            }
        }
        for (int j = 0; j < numVerts; j++) {
            edgeMatrix[j][numVerts - 1] = null;
            edgeMatrix[numVerts - 1][j] = null;
        }

        vertices.remove(vert);
        numVerts--;
        return true;
    }

    private void removeEdge(int vOrigKey, int vDestKey) {
        if (edgeMatrix[vOrigKey][vDestKey] != null) {
            edgeMatrix[vOrigKey][vDestKey] = null;
            numEdges--;
        }
        if (!isDirected && (edgeMatrix[vDestKey][vOrigKey] != null)) {
            edgeMatrix[vDestKey][vOrigKey] = null;
            numEdges--;
        }
    }

    @Override
    public boolean removeEdge(V vOrig, V vDest) {
        int vOrigKey = key(vOrig);
        int vDestKey = key(vDest);

        if ((vOrigKey < 0) || (vDestKey < 0) || (edgeMatrix[vOrigKey][vDestKey] == null))
            return false;

        removeEdge(vOrigKey, vDestKey);
        return true;
    }

    @Override
    public MatrixGraph<V, E> clone() {
        MatrixGraph<V, E> g = new MatrixGraph<>(this.isDirected, this.edgeMatrix.length);

        copy(this, g);

        return g;
    }

    /**
     * Returns a string representation of the graph.
     * Matrix only represents existence of Edge
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Vertices:\n");
        for (int i = 0; i < numVerts; i++)
            sb.append(vertices.get(i) + "\n");

        sb.append("\nMatrix:\n");

        sb.append("  ");
        for (int i = 0; i < numVerts; i++) {
            sb.append(" |  " + i + " ");
        }
        sb.append("\n");

        // aligned only when vertices < 10
        for (int i = 0; i < numVerts; i++) {
            sb.append(" " + i + " ");
            for (int j = 0; j < numVerts; j++)
                if (edgeMatrix[i][j] != null)
                    sb.append("|  X  ");
                else
                    sb.append("|     ");
            sb.append("\n");
        }

        sb.append("\nEdges:\n");

        for (int i = 0; i < numVerts; i++)
            for (int j = 0; j < numVerts; j++)
                if (edgeMatrix[i][j] != null)
                    sb.append("From " + i + " to " + j + "-> " + edgeMatrix[i][j] + "\n");

        sb.append("\n");

        return sb.toString();
    }

    public boolean addVertices(List<V> portList, List<V> cityList) {
        boolean flag = true;
        if(portList==null || cityList==null) return false;
        flag = addVertices(cityList);
        if(!flag) return false;
        flag = addVertices(portList);
        return flag;
    }

    public boolean addBorders(TreeMap<String,List<String>> list) {

        //first check for each Country in List if there is a City/Capital in the graph
        for(Object s : list.keySet()){
            Object city = containsCountry(s);
            if(city!=null){
                //Then if there is check if there is a City for the Country it borders
                for(String t : list.get(s)) {
                    Object border = containsCountry(t);
                    if (border != null) {
                        //If the 2 conditions are true - calculate distancance betweeen cities
                        if (!addEdge((V) city, (V) border,(E) distance((City) city,(City) border))) return false;
                    }
                }
            }
        }

        return true;
    }

    private Double distance(City city, Object border) {
        if(border instanceof City){
            return dist(city.getCoords().x,city.getCoords().y,((City) border).getCoords().x,((City) border).getCoords().y);
        } else if (border instanceof Port){
            return dist(city.getCoords().x,city.getCoords().y,((Port) border).getCoords().x,((Port) border).getCoords().y);

        }
        return null;
    }

    private Object containsCountry(Object s) {
        for(V v : vertices){
            if(v instanceof City){
                if(((City) v).getCountry().equals(s)) return v;
            }
        }
        return null;
    }


    public boolean capitalPort() throws IOException {
        for(V v : vertices){
            if(v instanceof City){
                List<Port> list = portContains(((City) v).getCountry());
                if(list.size()!=0){
                    Double mindist = distance((City) v,list.get(0));
                    int index = 0;
                    for(Port p : list){
                        if(distance((City) v,p)<mindist){
                            mindist = distance((City) v,p);
                            index = list.indexOf(p);
                        }
                    }
                    addEdge((V) list.get(index),v,(E) mindist);
                }
            }
        }

        return true;
    }

    private List<Port> portContains(String country) {
        List<Port> list = new ArrayList<>();
        for(V v : vertices){
            if(v instanceof Port){
                if(((Port) v).getCountry().equals(country)) list.add((Port) v);
            }
        }
        return list;
    }

    public boolean portsConnection(TreeMap<String, List<Pair<String,Double>>> seadist) {

        for(V v : vertices){
            if(v instanceof Port){
                List<Port> list = portContains(((Port) v).getCountry());
                if(list.size()!=0){
                    for(Port p : list){
                        if(!v.equals(p)) addEdge((V) v,(V) p,(E) getdist(seadist,v,p));
                    }
                }
            }
        }

        return true;
    }

    private Double getdist(TreeMap<String, List<Pair<String, Double>>> seadist, V v, Port p) {
        for(String s : seadist.keySet()){
            if(s.equals(((Port) v).getLocation())){
                for(int i  = 0; i<seadist.get(s).size();i++){
                    if(seadist.get(s).get(i).get1st().equals(((Port) p).getLocation())) return seadist.get(s).get(i).get2nd();
                }
            }
        }
        return null;
    }

    public boolean nportsConnect(int i, TreeMap<String, List<Pair<String,Double>>> seadist) { 

        List<Port> portList = new ArrayList<>();
        for(V v : vertices){
            if(v instanceof Port){
                portList.add((Port) v);
            }
        }
        MatrixGraph matrixAux = floydWarshall(portList,seadist);

        if(matrixAux!=null) {
            List<Integer> edgescount = new ArrayList<>();
            List<Integer> edgesmax = new ArrayList<>();

            for (Port p : portList) {
                edgescount.add(outDegree((V) p));
                edgesmax.add(outDegree((V) p) + i);
            }

            for (Port p : portList) {
                while (edgescount.get(portList.indexOf(p)) < edgesmax.get(portList.indexOf(p))) {
                    //Get List of Adjacency and get closest
                    if(matrixAux.incomingEdges((V) p).size()>0) {
                        List lista = (List) matrixAux.incomingEdges((V) p);
                        Edge edge = minEdge(lista);//Calculate Minimu

                        //Add Edge to matrix and upcount of nVertices

                        addEdge((V) edge.getVOrig(), (V) edge.getVDest(), (E) edge.getWeight());
                        int ec = portList.indexOf(edge.getVOrig());
                        edgescount.set(ec,edgescount.get(ec) + 1);
                        ec = portList.indexOf(edge.getVDest());
                        edgescount.set(ec,edgescount.get(ec) + 1);

                        //Remove Edge from auxiliary Matrix
                        matrixAux.removeEdge((V) edge.getVOrig(), (V) edge.getVDest());
                    } else {
                        break;
                    }
                }
            }
        }

        return true;
    }

    private Edge minEdge(List<Edge> lista) {
        Double mindist = (Double) lista.get(0).getWeight();//inicializa mininmo
        int index = 0;
        for(Edge e : lista){
            if((Double) e.getWeight()<mindist) { //verifica dist minima
                mindist = (Double) e.getWeight(); //guarda para comparação
                index = lista.indexOf(e); //guarda indice para retornar menor
            }
        }
        return lista.get(index);
    }

    private MatrixGraph floydWarshall(List<Port> portList,TreeMap<String, List<Pair<String,Double>>> seadist){
        int numverts = portList.size();
        if(numverts==0) return null;
        @SuppressWarnings("unchecked")
        E[][] matrix = (E[][]) new Object[numverts][numverts];

        for(int i=0;i<numverts;i++){
            for(int j=0;j<numverts;j++){
                if(!edges().contains(edge((V) portList.get(i),(V) portList.get(j)))) matrix[i][j] = (E) getdist(seadist,(V) portList.get(i),portList.get(j)); //Não adiciona as arestas já existentes
            }
        }

        return new MatrixGraph<V, E>(false, (ArrayList<V>) portList,matrix);

    }
}
