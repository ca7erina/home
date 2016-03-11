package algorithm.balancedtrees;


public class AVLTree {
   public static  Node root;

    public static void main(String args[]){
        AVLTree tree = new AVLTree();


        tree.insert(26);
        tree.insert(15);
        tree.insert(54);
       tree.insert(66);
       tree.insert(58);


            inOrder(tree.root);



    }



    public static int getHeight(Node node){
        if(node ==null){
            return -1;
        }
        return 1+Math.max(getHeight(node.leftChild),getHeight(node.rightChild));
    }

    public static void insert(int data){
        Node newNode = new Node(data);
        if(root==null){
            root = newNode;
            return;
        }
        Node current = root;
        Node parent ;     // need a parent pointer, cannot do current = newNode
        while(current!=null){
            parent = current;
            if(data<current.data){
                current = current.leftChild;
                if (current ==null){
                    parent.leftChild = newNode;
                    break;
                }
            }else{
                current = current.rightChild;
                if (current ==null){
                    parent.rightChild = newNode;
                    break;
                }
            }

        }

        updateHight(root);
        Node A = getAFromInOrder(root);
        if(A!=null){
             //rotate:
            System.out.println(A.data);
        }
    }

    /**
     *  Get unbalanced node.
     * @return   nearest ancestor having balance factor >1;
     */
    public static Node getAFromInOrder(Node node){
        if(node!=null){
            getAFromInOrder(node.leftChild);

             if(node.leftChild!=null&&node.rightChild!=null&&Math.abs(getHeight(node.leftChild)-getHeight(node.rightChild))>1){
                                 return node;
             }
            if(node.leftChild==null&&(getHeight(node.rightChild)>1)){
                return node;
            }
            if(node.rightChild==null&&(getHeight(node.leftChild)>1)){
                return node;
            }
            getAFromInOrder(node.rightChild);
        }
        return null;
    }

    public static void updateHight(Node node){
        if(node!=null){
            updateHight(node.leftChild);
            node.hight = getHeight(node);
           // System.out.println(node.data+" ("+node.hight+")");
            updateHight(node.rightChild);
        }
    }

    public static void inOrder(Node node){ //left-root-right
        if(node!=null){
            inOrder(node.leftChild);
            System.out.print(node.data + " hight"+node.hight+" " +
                    "\n");
            inOrder(node.rightChild);
        }
    }


}
class Node{
    int hight;
    int data;
    Node leftChild;
    Node rightChild;

    public Node(int data){
        this.hight=0;
        this.data=data;
        this.leftChild=null;
        this.rightChild=null;
    }


}