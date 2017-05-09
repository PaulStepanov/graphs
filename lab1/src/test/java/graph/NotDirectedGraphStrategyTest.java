package graph;

import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.graphstream.graph.Graph;
import org.junit.Test;

public class NotDirectedGraphStrategyTest {
    @Test
    public void convertGraphToMatrix() throws Exception {
        GraphComputer graphComputer = new GraphComputer();

        double[][] matrix = new double[][]{
                {0,1},
                {1,1},
                {1,0}};

        RealMatrix realMatrix = new BlockRealMatrix(matrix);

        Graph graph1 = graphComputer.parseIncidenceMatrixToGraph(realMatrix, false);

        NotDirectedGraphStrategy notDirectedGraphStrategy = new NotDirectedGraphStrategy();
        System.out.println(notDirectedGraphStrategy.convertGraphToAdjacencyMatrix(graph1));;
    }

}