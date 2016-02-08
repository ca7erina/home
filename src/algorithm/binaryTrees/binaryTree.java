package algorithm.binaryTrees;

/**
 * Created by chenxiaoxue on 2/3/16.
 */
public class binaryTree {

    public static void main(String args[]){
        Node root56 = new Node(56);
        Node n26 = new Node(26);
        Node n18 = new Node(18);
        Node n12 = new Node(12);
        Node n24 = new Node(24);
        Node n28 = new Node(28);
        Node n27 = new Node(27);
        Node n29 = new Node(29);
        Node n200 = new Node(200);
        Node n190 = new Node(190);
        Node n213 = new Node(213);
        root56.leftChild=n26;
        root56.rightChild=n200;
        n26.leftChild=n18;
        n26.rightChild=n28;
        n18.leftChild = n12;
        n18.rightChild = n24;
        n28.leftChild = n27;
        n28.rightChild = n29;
        n200.leftChild =n190;
        n200.rightChild = n213;

        System.out.println(find(213,root56).data);
        insert(10, root56);
        System.out.println(find(10, root56).data);
        inOrder(root56);
        System.out.println();
        preOrder(root56);
        System.out.println();
        postOrder(root56);
        System.out.println();
        System.out.println(minimum(root56).data);
        delete(26,root56);
        inOrder(root56);
    }

    public static Node find(int key,Node root){
        Node current = root;
        while(current.data!=key){
            if(key>current.data){
                current = current.rightChild;
            }else{
                current = current.leftChild;
            }
            if(current == null){
                return null; // didn't find
            }
        }
        return current;
    }

    public static void insert(int num, Node root){
        Node newNode = new Node(num);
        if(root==null){
            root = newNode;
            return;
        }
        Node current = root;
        Node parent ;     // need a parent pointer, cannot do current = newNode
        while(current!=null){
            parent = current;
            if(current.data>num){
                current = current.leftChild;
                if (current ==null){
                    parent.leftChild = newNode;
                    return;
                }
            }else{
                current = current.rightChild;
                if (current ==null){
                    parent.rightChild = newNode;
                    return;
                }
            }

        }
    }

    public static void inOrder(Node root){ //left-root-right
        if(root!=null){
            inOrder(root.leftChild);
            System.out.print(root.data + " ");
            inOrder(root.rightChild);
        }
    }


    public static void preOrder(Node root){ //root-left-right
        if(root!=null){
            System.out.print(root.data + " ");
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    public static void postOrder(Node root){ //left-right-root
        if(root!=null){
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            System.out.print(root.data + " ");
        }
    }

    public static Node minimum(Node root){
        Node current,last;
        current = root;
        last = current;
        while(current!=null){
            last = current;
            current=current.leftChild;
        }
        return last;
    }


    public static boolean delete(int key,Node root){
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while(current.data!=key){
            parent = current;
            if(key<current.data){
                isLeftChild=true;
                current=current.leftChild;
            }else{
                isLeftChild=false;
                current = current.rightChild;
            }
            if(current == null){
                return false; //didn't find
            }
        }

        //found node
        //is leaf, sumply delete it
        if(current.leftChild==null&current.rightChild==null){
            if(current == root){
                root =null;
            } else if(isLeftChild){
                parent.leftChild = null;
            }else{
                parent.rightChild = null;
            }
        // node has one child: left child
        }else if(current.rightChild==null){
            if(current == root){
                root = current.leftChild;
            }else if(isLeftChild){
                parent.leftChild=current.leftChild;
            }else{
                parent.rightChild = current.leftChild;
            }

        // node has one child: right child
        }else if(current.leftChild==null) {
            if(current == root){
                root = current.rightChild;
            }else if(isLeftChild){
                parent.leftChild=current.rightChild;
            }else{
                parent.rightChild = current.rightChild;
            }
        //node has two children
        }else{

            Node successor = getSuccessor(current);

            if(current == root){
                root = successor; //????
            }else if(isLeftChild){
                parent.leftChild = successor;
            }else{
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;

        }
        return true;

    }

    //find the number bigger than deleted node but smaller than rest node.
    private static Node getSuccessor(Node delNode){
        Node successorParent = delNode;
        Node successor = delNode;
        Node current =delNode.rightChild;
        while(current!=null){
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if(successor!=delNode.rightChild){
            successorParent.leftChild = successor.rightChild;
            successor.rightChild=delNode.rightChild;

        }
        return successor;
    }

}
