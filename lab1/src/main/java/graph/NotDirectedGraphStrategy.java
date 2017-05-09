package graph;
import converters.ArrayConverter;
import org.apache.commons.math3.geometry.spherical.twod.Edge;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

/**
 * Parsing incidence matrix and adjacency matrix to graph for not directed graph
 * */
public class NotDirectedGraphStrategy implements GraphParseStrategy {

    @Override
    public Graph parseIncidenceMatrixToGraph(RealMatrix realMatrix) {
        Graph graph = new SingleGraph("FIX THAT!!!LMaooo");
        //find out how many node to we have in incidence matrix
        int nodeAmount = realMatrix.getRowDimension();
        //adding node to the graph
        IntStream.range(0,nodeAmount).forEach(value -> graph.addNode(String.valueOf(value)));

        IntStream.range(0,realMatrix.getColumnDimension()).forEach(columnIndex -> {
            ArrayList<Integer> edgeCords = new ArrayList<Integer>();
            ArrayConverter.convertDoublePlainArrayToList(realMatrix.getColumn(columnIndex))
                    .stream()
                    .reduce(0,//Itterating throw edge column if it's 1 add to edge creation
                    (accum,columnVal) -> {
                        if (columnVal == 1)
                            edgeCords.add(accum);
                        return accum+1;
            }, (integer, integer2) -> integer2);
            graph.addEdge("edge:"+String.valueOf(columnIndex),String.valueOf(edgeCords.get(0)),String.valueOf(edgeCords.get(1)));//TODO add check if matrix wrong
        });
        return graph;
    }

    @Override
    public RealMatrix convertGraphToAdjacencyMatrix(Graph graph) {
        RealMatrix matrix = new BlockRealMatrix(graph.getNodeCount(),graph.getNodeCount());

        graph.getEdgeIterator().forEachRemaining(edge -> {
            matrix.setEntry(edge.getNode0().getIndex(),edge.getNode1().getIndex(),1);
            matrix.setEntry(edge.getNode1().getIndex(),edge.getNode0().getIndex(),1);
        });
        return matrix;
    }


}
