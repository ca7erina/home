package graph.path;

import java.io.*;

/**
 * Created by chenxiaoxue on 4/26/16.
 */
public class GraphLongestPath {

    public static void main(String[] args){

        FileIO io = new FileIO();
        String[] original = io.load("src" + File.separator + "graph" + File.separator +"path" + File.separator + "Graph.txt");
        int size=original.length-1;
        int[][] array = new int[size][size];

        for(int i=1;i<=size;i++){ //load up the data into array
            for(int j=1;j<=size;j++){
                array[i-1][j-1]=Integer.parseInt(original[i].split("\t")[j]);
            }
        }


        int furthestdistance=0; //keep track of the longest path found so far
        String furthestroute="";
//       for(int i=0;i<size;i++){ //loop through all possible starting positions: start from A
        int i=1;
        int visited=0; //how many vertices have been visited so far?
        Vertex[] vertices = new Vertex[size]; //initiate the vertex objects
        for(int j=0;j<size;j++){
            vertices[j]=new Vertex(original[0].split("\t")[j]);
        }
        vertices[i].visited=true; //we visit the starting position
        visited++; //we've visited one vertex so far
        vertices[i].distance=0; //set this vertex as starting point

        for(int j=0;j<size;j++){ //update distances from starting point
            if(array[j][i]>0){ //only update those that can be reached from here
                vertices[j].distance=array[j][i];
                System.out.println(j+" "+i+" "+array[j][i]);
            }
        }

        //print vertices
        for(int a=0;a<vertices.length;a++){
            System.out.println("vertice"+a+" "+vertices[a].label+ " route"+vertices[a].route+" distance"+vertices[a].distance);
        }


        int t=i;//i =0;
        while(visited<size) { //while not all vertices visited …fill this in


            for(int j=0;j<size;j++){//0-7
                System.out.println("j:"+j);
                System.out.println("t:"+t);
                System.out.println(array[j][t]);
                System.out.println(vertices[j].visited);



                    if((array[j][t] > 0) && (vertices[j].visited == false)) { //get all vertrices connect to B , except those already from already visited vertices.
                        System.out.println("singledis " + vertices[j].distance);
                        int distance = array[j][t] + vertices[t].distance;
                        System.out.println("totaldis " + distance);
                        if(distance > vertices[j].distance) {   //update this vertices to hold the longgest distance and route;
                            vertices[j].distance = array[j][t] + vertices[t].distance;
                            vertices[j].route = t;
                            System.out.println("set vertices" + j + " route:" + t + " singledistance:" + array[j][t]);
                        }
                    }



            }
            vertices[t].visited=true;
            visited++;
            t++;
            if(t>7){
                break;
            }
            System.out.println("---");
            System.out.println(visited<size);



        }



        //print vertices
        for(int a=0;a<vertices.length;a++){
            System.out.println("vertice"+a+" "+vertices[a].label+ " route"+vertices[a].route+" distance"+vertices[a].distance);
        }

        //A0,B1,C2,D4,E6,F3,G7,H5

        // vertices[0].label ="A";
        // vertices[0].route =-1;
        // vertices[0].distance=0;

        // vertices[1].label ="B";
        //  vertices[1].route =-1;
        // vertices[1].distance=3;
        // vertices[2].label ="C";
        // vertices[2].route =-1;
        // vertices[2].distance=6;

//        vertices[3].label ="D";
//        vertices[3].route =2;
//        vertices[3].distance=11;
//        vertices[4].label ="E";
//        vertices[4].route =3;
//        vertices[4].distance=17;
//        vertices[5].label ="F";
//        vertices[5].route =2;
//        vertices[5].distance=8;
//        vertices[6].label ="G";
//        vertices[6].route =4;
//        vertices[6].distance=21;
//        vertices[7].label ="H";
//        vertices[7].route =5;
//        vertices[7].distance=17;








        for(int x=0;x<size;x++){ //check for a new record distance, size: number of vertices
            if(vertices[x].distance>furthestdistance){ //go through all distances in the graph
                furthestdistance=vertices[x].distance;
                int visiting=x;
                String solution=""; //build up the path taken to get this particular record beating distance
                while(visiting>=0){ //the starting position has route of -1
                    solution=vertices[visiting].label+""+solution;
                    visiting=vertices[visiting].route; //step back along the route taken to generate this distance
                }
                solution=vertices[i].label+""+solution; //this is the starting position
                furthestroute=solution; //keep track of this record path
            }
        }
           System.out.println(furthestdistance); //print out the results
           System.out.println(furthestroute);
           furthestdistance=-1;
           furthestroute="";
//        }
//        System.out.println(furthestdistance); //print out the results
//        System.out.println(furthestroute);
    }

}