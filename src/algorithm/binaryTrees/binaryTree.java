package algorithm.binaryTrees;


import org.junit.Test;

import javax.sound.midi.SysexMessage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by chenxiaoxue on 2/3/16.
 */
public class binaryTree {

    public static void main(String args[]) {
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
        root56.leftChild = n26;
        root56.rightChild = n200;
        n26.leftChild = n18;
        n26.rightChild = n28;
        n18.leftChild = n12;
        n18.rightChild = n24;
        n28.leftChild = n27;
        n28.rightChild = n29;
        n200.leftChild = n190;
        n200.rightChild = n213;

        System.out.println(find(213, root56).data);
        insert(10, root56);
        System.out.println(find(10, root56).data);
        inOrder(root56);
        System.out.println();
        preOrder(root56);
        System.out.println();
        postOrder(root56);
        System.out.println();
        System.out.println(minimum(root56).data);
        delete(26, root56);
        inOrder(root56);
        System.out.println();
        inOrder(n26);

    }

    public static Node find(int key, Node root) {
        Node current = root;
        while(current.data != key) {
            if(key > current.data) {
                current = current.rightChild;
            } else {
                current = current.leftChild;
            }
            if(current == null) {
                return null; // didn't find
            }
        }
        return current;
    }

    public static void insert(int num, Node root) {
        Node newNode = new Node(num);
        if(root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node parent;     // need a parent pointer, cannot do current = newNode
        while(current != null) {
            parent = current;
            if(current.data > num) {
                current = current.leftChild;
                if(current == null) {
                    parent.leftChild = newNode;
                    return;
                }
            } else {
                current = current.rightChild;
                if(current == null) {
                    parent.rightChild = newNode;
                    return;
                }
            }

        }
    }

    public static void inOrder(Node root) { //left-root-right
        if(root != null) {
            inOrder(root.leftChild);
            System.out.print(root.data + " ");
            inOrder(root.rightChild);
        }
    }


    public static void preOrder(Node root) { //root-left-right
        if(root != null) {
            System.out.print(root.data + " ");
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    public static void postOrder(Node root) { //left-right-root
        if(root != null) {
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            System.out.print(root.data + " ");
        }
    }

    @Test
    public void TestBreadth() {

        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        root.leftChild = n2;
        root.rightChild = n3;
        n2.leftChild = n4;
        n2.rightChild = n5;
        n3.leftChild = n6;
        n3.rightChild = n7;
        byDepth(root);

    }

    public static void byBreadth(Node root) {
        Queue<Node> queue = new LinkedList();
        Node current = root;
        while(current != null) {
            System.out.println(current.data);
            if(current.leftChild != null) {
                queue.add(current.leftChild);
            }
            if(current.rightChild != null) {
                queue.add(current.rightChild);
            }
            if(!queue.isEmpty()) {
                current = queue.remove();
            } else {
                current = null;
            }
        }
    }

    public static void byDepth(Node root) {
        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        stack.push(current);
        while(current != null) {
            while(current != null) { //add all depth node left
                System.out.println(current.data);
                current = current.leftChild;
                stack.push(current);
            }
            current = stack.pop();
            current = stack.pop();

            while(current.rightChild == null) { // pop to find right
                if(stack.empty()) {
                    break;
                }
                current = stack.pop();
            }
            current = current.rightChild;

        }
    }

    public static Node minimum(Node root) {
        Node current, last;
        current = root;
        last = current;
        while(current != null) {
            last = current;
            current = current.leftChild;
        }
        return last;
    }


    public static boolean delete(int key, Node root) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while(current.data != key) {
            parent = current;
            if(key < current.data) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if(current == null) {
                return false; //didn't find
            }
        }

        //found node
        //is leaf, sumply delete it
        if(current.leftChild == null & current.rightChild == null) {
            if(current == root) {
                root = null;
            } else if(isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
            // node has one child: left child
        } else if(current.rightChild == null) {
            if(current == root) {
                root = current.leftChild;
            } else if(isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }

            // node has one child: right child
        } else if(current.leftChild == null) {
            if(current == root) {
                root = current.rightChild;
            } else if(isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
            //node has two children
        } else {
            Node successor = getSuccessor(current);
            if(current == root) {
                root = successor; //????
            } else if(isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }
        return true;

    }

    //find the number bigger than deleted node but smaller than rest node.(go right, then go left left left)
    private static Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while(current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        if(successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;

        }
        return successor;
    }

}
