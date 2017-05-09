import graph.GraphComputer;
import org.apache.commons.math3.linear.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


public class Main {
    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        test1();
        test2();


    }

    static void test1(){
        GraphComputer graphComputer = new GraphComputer();

        double[][] matrix = new double[][]{
                {0,1},
                {1,1},
                {1,0}};

        RealMatrix realMatrix = new BlockRealMatrix(matrix);

        Graph graph1 = graphComputer.parseIncidenceMatrixToGraph(realMatrix, false);

        graph1.display();
    }

    static void test2(){
        GraphComputer graphComputer = new GraphComputer();

        double[][] matrix = new double[][]{
                {-1,1,-1,0,-1,0},
                {1,0,0,-1,0,0},
                {0,-1,0,0,0,1},
                {0,0,1,0,0,-1},
                {0,0,0,1,1,0}};

        RealMatrix realMatrix = new BlockRealMatrix(matrix);

        Graph graph1 = graphComputer.parseIncidenceMatrixToGraph(realMatrix, true);
        graph1.display();

    }
}
