package graph;

import converters.ArrayConverter;
import domain.Pair;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class DirectedGraphStrategy implements GraphParseStrategy {
    @Override
    public Graph parseIncidenceMatrixToGraph(RealMatrix realMatrix) {
        Graph graph = new SingleGraph("FIX THAT!!!LMaooo");

        int nodeAmount = realMatrix.getRowDimension();
        //adding node to the graph
        IntStream.range(0,nodeAmount).forEach(value -> graph.addNode(String.valueOf(value)));

        IntStream.range(0,realMatrix.getColumnDimension()).forEach(columnIndex -> {
            Pair<Integer,Integer> edgeCords = new Pair<Integer, Integer>();
            ArrayConverter.convertDoublePlainArrayToList(realMatrix.getColumn(columnIndex))
                    .stream()
                    .reduce(0,//Itterating throw edge column if it's 1 add to edge creation
                            (accum,columnVal) -> {
                                if (columnVal == 1.0) {
                                    edgeCords.setLeft(accum);
                                }

                                if (columnVal == -1.0){
                                    edgeCords.setRight(accum);
                                }

                                return accum+1;
                            }, (integer, integer2) -> integer2);
            graph.addEdge(String.valueOf(columnIndex),String.valueOf(edgeCords.getLeft()),String.valueOf(edgeCords.getRight()),true);//TODO add check if matrix wrong
        });


        return graph;
    }

    @Override
    public RealMatrix convertGraphToAdjacencyMatrix(Graph graph) {
        RealMatrix matrix = new BlockRealMatrix(graph.getNodeCount(),graph.getNodeCount());

        graph.getEdgeIterator().forEachRemaining(edge -> {
            matrix.setEntry(edge.getNode0().getIndex(),edge.getNode1().getIndex(),1);
        });

        return matrix;
    }


}
