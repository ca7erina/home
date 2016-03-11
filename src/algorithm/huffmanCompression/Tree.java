package algorithm.huffmanCompression;

/**
 * Created by chenxiaoxue on 2/23/16.
 */

////////////////////////////////////////////////////////////////
public class Tree implements Comparable<Tree>
{
    public Node root;             // first node of tree
    public int frequency=0;

    // -------------------------------------------------------------
    public Tree()                  // constructor
    {   root = null; }            // no nodes in tree yet
// -------------------------------------------------------------

//the PriorityQueue needs to be able to somehow rank the objects in it
//thus, the objects in the PriorityQueue must implement an interface called Comparable
//the interface requires you to write a compareTo() method so here it is:

    public int compareTo(Tree object){ //
        if(frequency-object.frequency>0){ //compare the cumulative frequencies of the tree
            return 1;
        }else if(frequency-object.frequency<0){
            return -1;   //return 1 or -1 depending on whether these frequencies are bigger or smaller
        }else{
            return 0;   //return 0 if they're the same
        }
    }

// -------------------------------------------------------------

//    String path="error";     //this variable will track the path to the letter we're looking for
//
//    public String getCode(char letter){  //we want the code for this letter
//
//        //FILL THIS IN:
//
//        //How do you get the code for the letter? Maybe try a traversal of the tree
//        //Track the path along the way and store the current path when you arrive at the right letter
//
//
//        inOrder(root,"",letter);
//
//
//
//        return path;     //return the path that results
//
//    }
    String result="";
    public String getCode(Node root,String path,char letter){ //left-root-right
        Node current =root;
        if(current!=null){

            getCode(current.leftChild, path + "0", letter);
            if(current.letter==letter){

               result=path;
                return path;
            }


            getCode(current.rightChild,path+"1",letter);
        }
        return result;
    }

}  // end class Tree
////////////////////////////////////////////////////////////////

