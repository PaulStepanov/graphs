package graph;

import domain.Colors;
import org.apache.commons.math3.linear.RealMatrix;
import org.graphstream.graph.Element;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

import static java.util.Comparator.comparing;

/**
 * Parsing incidence matrix and adjacency matrix to graph
 */
public class GraphComputer {
    private GraphParseStrategy graphParseStrategy;

    public GraphComputer() {
    }

    public Graph parseIncidenceMatrixToGraph(RealMatrix realMatrix, boolean isDirected) {
        if (isDirected) {
            this.graphParseStrategy = new DirectedGraphStrategy();
        } else {
            this.graphParseStrategy = new NotDirectedGraphStrategy();
        }
        return this.graphParseStrategy.parseIncidenceMatrixToGraph(realMatrix);
    }

    public RealMatrix convertGraphToAdjacencyMatrix(Graph graph, boolean isDirected) {
        if (isDirected) {
            this.graphParseStrategy = new DirectedGraphStrategy();
        } else {
            this.graphParseStrategy = new NotDirectedGraphStrategy();
        }
        return this.graphParseStrategy.convertGraphToAdjacencyMatrix(graph);
    }


    public ArrayList<Node> findWeightSequence(Graph graph) {
        ArrayList<Node> nodes = new ArrayList<>();

        graph.getNodeSet().forEach(nodes::add);

        nodes.sort(comparing(Node::getDegree));

        return nodes;
    }

    /*
    *
1.	Assign RED color to the source vertex (putting into set U).
2.	Color all the neighbors with BLUE color (putting into set V).
3.	Color all neighbor’s neighbor with RED color (putting into set U).
4.	This way, assign color to all vertices such that it satisfies all the constraints of m way coloring problem where m = 2.
5. While assigning colors, if we find a neighbor which is colored with same color as current vertex, then the graph cannot be colored with 2 vertices (or graph is not Bipartite)
    * */
    boolean isBipartite(Graph graph) {
        Node srcNode = graph.getNode(0);
        //Assign RED color to the source vertex (putting into set U).
        srcNode.setAttribute("color", Colors.RED);
        //Color all the neighbors with BLUE color (putting into set V)
        srcNode.getNeighborNodeIterator().forEachRemaining(node -> {
            node.addAttribute("color",);
            node.getNeighborNodeIterator().forEachRemaining(innerNode -> {
                if (!node.equals(innerNode)) {//ignoring prev node

                }
            });
        });
        return true;
    }



    //Return remoteness of each node in the graph
    public HashMap<Node, HashMap<Node, Integer>> getRemotenessOfAllNodes(Graph graph) {
        HashMap<Node, HashMap<Node, Integer>> resMap = new HashMap<Node, HashMap<Node, Integer>>();

        graph.getNodeIterator().forEachRemaining(node -> {
            clearGraphAttrs(graph);
            //setup 0 distance for root node and mark as visited
            node.setAttribute("distance", 0);
            node.setAttribute("visited", true);

            //recurse setup distance from this node to all others
            itter(node);

            HashMap<Node, Integer> distanceMap = new HashMap<Node, Integer>();

            graph.getNodeIterator().forEachRemaining(inNode -> {
                //putting node and distance to this node in map
                if (!node.equals(inNode))
                    distanceMap.put(inNode, inNode.getAttribute("distance"));
            });

            //putting for current node distance map to others
            resMap.put(node, distanceMap);
        });


        return resMap;
    }

    public Integer findGraphRadius(Graph graph) {
        HashMap<Node, HashMap<Node, Integer>> remotenessOfAllNodes = getRemotenessOfAllNodes(graph);

        ArrayList<Integer>  remotenessArr = new ArrayList<>();

        remotenessOfAllNodes.values()
                .forEach(nodeIntegerHashMap ->
                nodeIntegerHashMap.values()
                        .forEach(remotenessArr::add));

        return remotenessArr.stream().min(Integer::compareTo).get();
    }

    public Integer findGraphDiameter(Graph graph) {
        HashMap<Node, HashMap<Node, Integer>> remotenessOfAllNodes = getRemotenessOfAllNodes(graph);

        ArrayList<Integer>  remotenessArr = new ArrayList<>();

        remotenessOfAllNodes.values()
                .forEach(nodeIntegerHashMap ->
                        nodeIntegerHashMap.values()
                                .forEach(remotenessArr::add));

        return remotenessArr.stream().max(Integer::compareTo).get();
    }

    public ArrayList<Node> findGraphCenter(Graph graph) {
        ArrayList<Node> nodes = new ArrayList<>();
        graph.getNodeIterator().forEachRemaining(node -> {
            if (findEccentricityForNode(graph,node)==findGraphRadius(graph)){
                nodes.add(node);
            }
        });
        return nodes;
    }

    public ArrayList<Node> findPeripheralCenter(Graph graph){
        ArrayList<Node> nodes = new ArrayList<>();
        graph.getNodeIterator().forEachRemaining(node -> {
            if (findEccentricityForNode(graph,node)==findGraphDiameter(graph)){
                nodes.add(node);
            }
        });
        return nodes;
    }

    //@param distance map which contains distances to other nodes
    public Integer findEccentricityForNode(HashMap<Node, Integer> distanceMap) {
        return distanceMap.values().stream().max(comparing(Integer::intValue)).get();
    }
    public Integer findEccentricityForNode(Graph graph, Node node) {
        HashMap<Node, HashMap<Node, Integer>> remotenessOfAllNodes = getRemotenessOfAllNodes(graph);
        return remotenessOfAllNodes.get(node).values().stream().max(comparing(Integer::intValue)).get();
    }
    public Integer findEccentricityForNode(Graph graph, Integer index) {
        Node node = graph.getNode(index);
        HashMap<Node, HashMap<Node, Integer>> remotenessOfAllNodes = getRemotenessOfAllNodes(graph);
        return remotenessOfAllNodes.get(node).values().stream().max(comparing(Integer::intValue)).get();
    }

    //setup distance to all nodes from givven
    private void itter(Node parendtNode) {
        Queue<Node> nodeQueue = new ArrayDeque<>();

        parendtNode.getNeighborNodeIterator().forEachRemaining(node -> {
            if (!node.hasAttribute("visited")) {
                nodeQueue.add(node);
            }
        });

        nodeQueue.forEach(inNode -> {
            inNode.setAttribute("distance", (Integer) parendtNode.getAttribute("distance") + 1);
            inNode.setAttribute("visited", true);

        });

        nodeQueue.forEach(this::itter);

    }

    private void clearGraphAttrs(Graph graph){
        graph.getNodeIterator()
                .forEachRemaining(Element::clearAttributes);

    }


}
