package graph;

import org.apache.commons.math3.linear.RealMatrix;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface GraphParseStrategy {

    public Graph parseIncidenceMatrixToGraph(RealMatrix realMatrix);

    public RealMatrix convertGraphToAdjacencyMatrix(Graph graph);


}
