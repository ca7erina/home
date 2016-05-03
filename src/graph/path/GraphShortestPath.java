package graph.path;

import java.io.*;

/**
 * Calculate the shortest path of any 2 point ( Dijkstra)
 * let any point be the start point and get the longest path from that table.
 * <p/>
 * Created by chenxiaoxue on 4/26/16.
 */
public class GraphShortestPath {

    public static void main(String[] args) {
        FileIO io = new FileIO();
        String[] original = io.load("src" + File.separator + "graph" + File.separator + "path" + File.separator + "test.txt");
        int size = original.length - 1;
        int[][] array = new int[size][size];

        for(int i = 1; i <= size; i++) { //load up the data into array
            for(int j = 1; j <= size; j++) {
                array[i - 1][j - 1] = Integer.parseInt(original[i].split("\t")[j]);
            }
        }

        int furthestdistance = 0; //keep track of the longest path found so far
        String furthestroute = "";
        for(int i = 0; i < size; i++) { //loop through all possible starting positions: start from A

            int visited = 0; //how many vertices have been visited so far?
            Vertex[] vertices = new Vertex[size]; //initiate the vertex objects
            for(int j = 0; j < size; j++) {
                vertices[j] = new Vertex(original[0].split("\t")[j]);
            }

            System.out.println("------");
            System.out.println("Start point :" +vertices[i].label);

            vertices[i].visited = true; //we visit the starting position
            visited++; //we've visited one vertex so far
            vertices[i].distance = 0; //set this vertex as starting point

            for(int j = 0; j < size; j++) { //update distances from starting point
                if(array[j][i] > 0) { //only update those that can be reached from here
                    vertices[j].distance = array[j][i];
                }
            }


            int t = i;// root column
            while(visited < size) { //while not all vertices visited …fill this in
                int min = Integer.MAX_VALUE;
                int minVertexIndex = 0;
                for(int j = 0; j < vertices.length; j++) { // all vertices
                    if((vertices[j].distance > 0) && (vertices[j].distance < Integer.MAX_VALUE) && vertices[j].visited == false) { // next level has distance but not setted
//                    System.out.println("vetex"+j+" "+vertices[j].distance+ " "+vertices[j].route+" "+vertices[j].visited);
                        if(vertices[j].distance < min) {
                            min = vertices[j].distance;
                            minVertexIndex = j;
                        }
                    }
                }
                vertices[minVertexIndex].visited = true;
                visited++;
//                System.out.println("set vertices" + minV + " visited, route "+vertices[minV].route+" ,total "+vertices[minV].distance);   //debug
                setDistance(vertices, minVertexIndex, array); // set next level distance of chosen vertex
                t = minVertexIndex;//t store up level

            }

                //print vertices

            for(int a=0;a<vertices.length;a++){
                System.out.println("vertice"+a+" "+vertices[a].label+ " route"+vertices[a].route+" distance"+vertices[a].distance);
            }

            System.out.println("------");
            for(int x = 0; x < size; x++) { //check for a new record distance, size: number of vertices
                if(vertices[x].distance > furthestdistance) { //go through all distances in the graph
                    furthestdistance = vertices[x].distance;
                    int visiting = x;
                    String solution = ""; //build up the path taken to get this particular record beating distance
                    while(visiting >= 0) { //the starting position has route of -1
                        solution = vertices[visiting].label + "" + solution;
                        visiting = vertices[visiting].route; //step back along the route taken to generate this distance
                    }
                    solution = vertices[i].label + "" + solution; //this is the starting position
                    furthestroute = solution; //keep track of this record path
                }
            }
            //here show all the tables
              System.out.println(furthestdistance); //print out the results
              System.out.println(furthestroute);
              furthestdistance=-1;
              furthestroute="";
        }
//        System.out.println(furthestdistance); //print out the results
//        System.out.println(furthestroute);
    }

    /**
     * set distance of vertex that the index vertex connected to
     *
     * @param vertices
     * @param index
     * @param array
     */
    public static void setDistance(Vertex vertices[], int index, int[][] array) {
        for(int j = 0; j < vertices.length; j++) {
            if(array[j][index] > 0 && vertices[j].visited == false) {

                int distance = array[j][index] + vertices[index].distance;
                if(distance < vertices[j].distance) {
                    vertices[j].distance = distance;
                    vertices[j].route = index;
                    //   System.out.println("set next level vertex"+j+" route"+ index+" "+vertices[j].distance);
                }


            }
        }
    }


}

class Vertex { //everything you need for a vertex
    public boolean visited = false; //has it been visited?
    int distance = Integer.MAX_VALUE; //the shortest route from starting position to this vertex
    int route = -1; //keep track of the last step taken to reach this vertex for the shortest path - what vertex did it come from?
    String label; //name of the vertex

    public Vertex(String labelin) { //give the vertex a name when instantiated
        label = labelin;
    }
}

class FileIO {

    public String[] load(String file) {
        File aFile = new File(file);
        StringBuilder contents = new StringBuilder();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(aFile));
            String line ;
            int i = 0;
            while((line = input.readLine()) != null) {
                contents.append(line);
                i=i+1;
                contents.append(System.getProperty("line.separator"));
            }
        } catch(FileNotFoundException ex) {
            System.out.println("Can't find the file - are you sure the file is in this location: " + file);
            ex.printStackTrace();
        } catch(IOException ex) {
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        } finally {
            try {
                if(input != null) {
                    input.close();
                }
            } catch(IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
        return array;
    }

    public void save(String file, String[] array) throws IOException {
        File aFile = new File(file);
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(aFile));

            for(String s:array){
                output.write(s);
                output.write(System.getProperty("line.separator"));
            }
        } finally {
            if(output != null) output.close();
        }
    }


}
