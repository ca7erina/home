package graph;


public class DFS {


}




class Vertex {
    public char label;        // label (e.g. 'A')
    public boolean visited;

    public Vertex(char lab)
    {
        label = lab;
        visited = false;
    }

}

class Edge {
    Vertex start;
    Vertex end;


    Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;

    }
}