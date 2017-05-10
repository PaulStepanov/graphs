package graph;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.Test;

import java.util.HashMap;

public class GraphComputerTest {
    @Test
    public void test1() throws Exception {
        GraphComputer graphComputer = new GraphComputer();

        double[][] matrix = new double[][]{
                {-1,1,-1,0,-1,0},
                {1,0,0,-1,0,0},
                {0,-1,0,0,0,1},
                {0,0,1,0,0,-1},
                {0,0,0,1,1,0}};

        RealMatrix realMatrix = new BlockRealMatrix(matrix);

        Graph graph =  graphComputer.parseIncidenceMatrixToGraph(realMatrix,true);

        graphComputer.findWeightSequence(graph).forEach(System.out::println);

    }

    @Test
    public void test2() throws Exception {
        GraphComputer graphComputer = new GraphComputer();

        double[][] matrix = new double[][]{
                {-1,1,-1,0,-1,0},
                {1,0,0,-1,0,0},
                {0,-1,0,0,0,1},
                {0,0,1,0,0,-1},
                {0,0,0,1,1,0}};

        RealMatrix realMatrix = new BlockRealMatrix(matrix);

        Graph graph =  graphComputer.parseIncidenceMatrixToGraph(realMatrix,true);

        HashMap<Node, HashMap<Node, Integer>> distanse = graphComputer.getRemotenessOfAllNodes(graph);

        distanse.forEach((node, nodeIntegerHashMap) -> {
            System.out.println(node.getId()+":");
            nodeIntegerHashMap.forEach((node1, integer) -> {
                System.out.println("  "+node1+":"+integer);
            });
        });


        System.out.println("radius:"+graphComputer.findGraphRadius(graph));
        System.out.println("diameter:"+graphComputer.findGraphDiameter(graph));

        System.out.println("Center:");
        System.out.println(graphComputer.findGraphCenter(graph));

        System.out.println("Peripheral center:");
        System.out.println(graphComputer.findPeripheralCenter(graph));

        System.out.println("isBipartite:");
        System.out.println(graphComputer.isBipartite(graph));


    }


}