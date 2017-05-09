package graph;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.graphstream.graph.Graph;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectedGraphStrategyTest {
    @Test
    public void convertGraphToAdjacencyMatrix() throws Exception {
        GraphComputer graphComputer = new GraphComputer();

        double[][] matrix = new double[][]{
                {-1,1,-1,0,-1,0},
                {1,0,0,-1,0,0},
                {0,-1,0,0,0,1},
                {0,0,1,0,0,-1},
                {0,0,0,1,1,0}};

        RealMatrix realMatrix = new BlockRealMatrix(matrix);

        Graph graph =  graphComputer.parseIncidenceMatrixToGraph(realMatrix,true);

        DirectedGraphStrategy directedGraphStrategy = new DirectedGraphStrategy();
        System.out.println(directedGraphStrategy.convertGraphToAdjacencyMatrix(graph));
    }
}