package graph;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.graphstream.graph.Graph;
import org.graphstream.stream.sync.SourceTime;
import org.junit.Test;
import scala.util.parsing.combinator.testing.Str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

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

        RealMatrix realMatrix1 = directedGraphStrategy.convertGraphToAdjacencyMatrix(graph);

        graph.getNodeIterator().forEachRemaining(node ->{
            System.out.println("Node:"+node.getId());
            System.out.println("Degree:"+ String.valueOf(node.getDegree()));
        });

        //graphComputer.findWeightSequence(graph).forEach(node -> System.out.println("sequence:"+node.getId()));

//        IntStream.range(0,realMatrix1.getRowDimension()).forEach(value -> {
//            System.out.println(Arrays.toString(realMatrix1.getRow(value)));
//        });
        System.out.println();
    }
}