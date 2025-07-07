// Nathan Cannell
// Student ID: 2170463
// 6.2.2022
// Huffman Coding
// This program takes a given number of frequencies or input and
// compresses the data into a simplified binary format to make it
// easy to transfer across platforms, it also translates it back
// into the original state 
import java.util.*;
import java.io.*;

public class HuffmanCode {

    private HuffmanNode overallRoot;

    // creates a new HuffmanCode object that takes the int array
    // frequencies puts it into a builds a new pre orderorganized 
    // binary there initializes HuffmanNode overallRoot
    public HuffmanCode(int[] frequencies) {
        Queue<HuffmanNode> priority = new PriorityQueue<>();

        for (int i = 0; i < frequencies.length; i++) {
            if(frequencies[i] != 0) {
                priority.add(new HuffmanNode(frequencies[i], (char)i));
            }
        }
        while(priority.size() > 1) {
            HuffmanNode temp1 = priority.remove();
            HuffmanNode temp2 = priority.remove();
            overallRoot = new HuffmanNode(temp1.freq + temp2.freq, temp1, temp2);
            priority.add(overallRoot);
        }
    }
    
    // builds a new HuffmanCode object by traversing the given
    // scanner input in a preorder fashion
    public HuffmanCode(Scanner input) {
        while (input.hasNextLine()) {
            int asciiValue = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            overallRoot = add(code, overallRoot, asciiValue, 0);
        }
        
    }

    // Helper method for HuffmanCode(Scanner input)
    // String code is the binary value for each letter
    // HuffmanRoot root is the current root
    // int asciiValue is the ascii value used to find the character
    private HuffmanNode add(String code, HuffmanNode root, int asciiValue, int outputLength) {
        if (outputLength == code.length()) {
            root = new HuffmanNode((char)asciiValue);
        } else {
            if(root == null) {
                root = new HuffmanNode();
            }
            if (code.charAt(outputLength) == '0') {
                root.left = add(code, root.left, asciiValue, outputLength + 1);
            } else if (code.charAt(outputLength) == '1') {
                root.right = add(code, root.right, asciiValue, outputLength + 1);
            }
        }
        return root;
    }

    // prints the the overallRoot into the
    // PrintStream output file in a preorder fashion
    public void save(PrintStream output) {
        save(output, overallRoot, "");
    } 

    // PrintStream output is the PrintStream related to the output file
    // HuffmanNode root is the current root used to traverse overallRoot
    // String code is the binary code lineked to the character
    private void save(PrintStream output, HuffmanNode root, String code) {
        if (root != null) {
            if (root.right == null && root.left == null) {
                output.println((int)root.letter);
                output.println(code);
            }
            if (root.left != null) {
                save(output, root.left, code + "0");
            }
            if (root.right != null) {
                save(output, root.right, code + "1");
            } 
        }
    }

    // turns the given scanner input which should be in the simplified binary format
    // into the letters that it corresponds to and prints it into the output
    // traverses in pre order
    public void translate(Scanner input, PrintStream output) {
        HuffmanNode root = overallRoot;
        if (root != null) {
            while (input.hasNext()) {
                if (root.left == null && root.right == null) {
                    output.write((int)root.letter);
                    root = overallRoot;
                }
                char code = input.next().charAt(0);
                if(code == '0') {
                    root = root.left;
                } else if (code == '1') {
                    root = root.right;
                }                
            }
            output.write((int)root.letter);
        }
    }

    // the HuffmanNode class implements comparable for HuffmanNodes
    // the HuffmanNode class is used to create binary trees with the 
    // frequency of words, the character thats assosciated with it,
    // and the left and right HuffmanNode child nodes.
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        public final int freq;
        public final char letter;
        public HuffmanNode left;
        public HuffmanNode right;
        
        // constructs a HuffmanNode where 
        // freq is the frequency and letter is the 
        // given character and 
        // left and right are set to null
        public HuffmanNode(int freq, char letter) {
            this.freq = freq;
            this.letter = letter;
            this.left = null;
            this.right = null;
        }
        
        // constructs an empty HuffmanNode where 
        // freq and letter are set to zero and 
        // left and right are set to null
        public HuffmanNode() {
            this.freq = 0;
            this.letter = 0;
            this.left = null;
            this.right = null;
        }

        // Constructs a new HuffmanNode where int freq is the frequency and
        // left and right are the nodes for the children of the parent node
        // and letter is set to zero
        public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
            this.freq = freq;
            this.letter = 0;
            this.left = left;
            this.right = right;
        }

        // constructs a new HuffmanNOde where char letter is the given
        // letter and freq is set to zero and left and right are null
        public HuffmanNode(char letter) {
            this.freq = 0;
            this.letter = letter;
            this.left = null;
            this.right = null;
        }

        // implements comparable to compare two HuffmanNodes by frequency
        // compares the field HuffmanNode with the other
        // returns an int greater than zero if other is less than the 
        // current HuffmanNode and returns a negative int if other is greater 
        // than the current HuffmanNode and returns zero if the nodes are equal
        public int compareTo(HuffmanNode other) {
            int cmp = Double.compare(Math.abs(this.freq), Math.abs(other.freq));
            return cmp;
        }
    }
}
