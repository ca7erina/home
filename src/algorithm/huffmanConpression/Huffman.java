package algorithm.huffmanConpression;

import java.util.*;

public class Huffman {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your sentence: ");
        String sentence = in.nextLine();

        //get frequency array
        int[] array = getFrequencyArray(sentence);

        //encode using huffmanCompression
        String allCompressedtext =encode(array,sentence);


        //print out all the info
        int total = sentence.length() * 7;
        String compressed =allCompressedtext.replace(" ","");
        long result = Math.round(((double) compressed.length() / (double) total) * 100);
        System.out.println("Compressed size is " + compressed.length()  + "bits / " + total + "bits = " + result + "%");

        //decode with frequency array
        System.out.println("decode with frequency table and tree: " + decode(array, allCompressedtext));
    }

    public static String encode(int[] frequencyArray,String sentence){

        Tree HuffmanTree = buildTree(frequencyArray);

        String allCompressedtext = "";
        int compressLength = 0;
        for(int i = 0; i < sentence.length(); i++) { //go through frequency array
            String afterCompress = HuffmanTree.getCode(HuffmanTree.root, "", sentence.charAt(i));
            allCompressedtext += afterCompress + " ";
            compressLength += afterCompress.length();

        }
        System.out.println(allCompressedtext);
        return allCompressedtext;
    }


    //already have tree and codeThe cats did not sit on the mat
    public static String decode(int[] frequencyArray, String allCompressedtext) {

        Tree HuffmanTree = buildTree(frequencyArray);

        String result = "";
        String codes[] = allCompressedtext.split(" ");
        for(String code : codes) {
            Node current = HuffmanTree.root;
            for(int i = 0; i < code.length(); i++) {
                if(code.charAt(i) == '0') {
                    current = current.leftChild;
                } else {
                    current = current.rightChild;
                }
            }
            result += current.letter;
        }
        return result;
    }


    public static Tree buildTree(int[] frequencyArray) {
        PriorityQueue<Tree> PQ = new PriorityQueue<Tree>();  //make a priority queue to hold the forest of trees

        for(int i = 0; i < frequencyArray.length; i++) { //go through frequency array
            if(frequencyArray[i] > 0) { //print out non-zero frequencies - cast to a char
                System.out.println("'" + (char) i + "' appeared " + frequencyArray[i] + ((frequencyArray[i] == 1) ? " time" : " times"));

                //FILL THIS IN:

                //MAKE THE FOREST OF TREES AND ADD THEM TO THE PQ
                //create a new Tree
                Tree tree = new Tree();

                //set the cumulative frequency of that Tree
                tree.frequency = frequencyArray[i];
                //insert the letter as the root node
                Node node = new Node();
                node.letter = (char) i;
                tree.root = node;
                //add the Tree into the PQ
                PQ.add(tree);

            }
        }


        while(PQ.size() > 1) {             //while there are two or more Trees left in the forest
            //IMPLEMENT THE HUFFMAN ALGORITHM
            Tree tree1 = PQ.poll();
            Tree tree2 = PQ.poll();
            Tree newTree = new Tree();
            Node newNode = new Node();
            newNode.leftChild = tree1.root;
            newNode.rightChild = tree2.root;
            newNode.letter = '\0';
            newTree.root = newNode;
            newTree.frequency = tree1.frequency + tree2.frequency;
            PQ.add(newTree);
            //when you're making the new combined tree, don't forget to assign a default root node (or else you'll get a null pointer exception)
            //if you like, to check if everything is working so far, try printing out the letter of the roots of the two trees you're combining
        }

        Tree HuffmanTree = PQ.poll();   //now there's only one tree left - get its codes
        return HuffmanTree;

    }

    public static int[] getFrequencyArray(String sentence) {
        String binaryString = "";      //this stores the string of binary code


        for(int i = 0; i < sentence.length(); i++) {        //go through the sentence
            int decimalValue = (int) sentence.charAt(i);      //convert to decimal
            String binaryValue = Integer.toBinaryString(decimalValue);      //convert to binary
            for(int j = 7; j > binaryValue.length(); j--) {
                binaryString += "0";           //this loop adds in those pesky leading zeroes
            }
            binaryString += binaryValue + " "; //add to the string of binary
        }
        System.out.println(binaryString);    //print out the binary


        int[] array = new int[256];      //an array to store all the frequencies

        for(int i = 0; i < sentence.length(); i++) {    //go through the sentence
            array[(int) sentence.charAt(i)]++;    //increment the appropriate frequencies

        }
        return array;
    }
}
