# Huffman Coding Compressor

A Java implementation of Huffman Coding for text file compression and decompression. Built for CSE 143 at the University of Washington.

---

## Overview

**Huffman coding** is a classic lossless data compression algorithm. It assigns variable-length binary codes to characters based on their frequencies, enabling efficient storage and transfer of text data. This project provides a command-line tool that:

- Reads a text file and computes character frequencies
- Builds a Huffman tree and generates optimal binary codes
- Compresses the text file into a compact binary format
- Decompresses the compressed file back to its original form

---

## Features

- **Automatic frequency analysis** of input files
- **Huffman tree construction** using Java's `PriorityQueue`
- **Custom binary file format** for compressed data and codebook
- **Pre-order traversal** for saving/loading codebooks
- **Efficient decompression** using bitwise input streams
- **Well-documented, modular Java code**

---

## File Types

| Extension   | Description                                           |
|-------------|------------------------------------------------------|
| `.txt`      | Original text file to compress                       |
| `.code`     | Codebook: ASCII value and Huffman code per character |
| `.short`    | Compressed binary file (not human-readable)          |
| `.debug`    | Human-readable compressed output (for debugging)     |
| `.new`      | Decompressed output (should match original `.txt`)   |

---

## How It Works

1. **Frequency Analysis:**  
   Reads the input file and counts the frequency of each character (ASCII 0–255).

2. **Tree Construction:**  
   - Each unique character becomes a leaf node.
   - Nodes are combined by lowest frequency first, forming a binary tree.
   - The tree structure determines each character's binary code (left = 0, right = 1).

3. **Codebook Generation:**  
   - The tree is traversed in pre-order.
   - For each leaf, the ASCII value and binary code are saved to a `.code` file.

4. **Compression:**  
   - The input file is re-encoded using the generated codes.
   - Output is written in binary to a `.short` file.

5. **Decompression:**  
   - The `.code` file reconstructs the Huffman tree.
   - The compressed `.short` file is decoded back to the original text.

---

## Usage

### Running the Program

1. **Compile all Java files:**
   ```
   javac HuffmanCode.java HuffmanMain.java HuffmanCompressor.java
   ```

2. **Start the compressor:**
   ```
   java HuffmanMain
   ```

3. **Follow prompts:**
   - Enter the name of the `.txt` file to compress.
   - The program will generate `.code`, `.short`, and `.new` files.

---

## Example

Suppose `message.txt` contains:

```
bbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
cccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
ddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
```

- **Original size:** 1920 bits (ASCII)
- **Compressed size:** 255 bits (Huffman)

---

## Project Structure

- `HuffmanCode.java`: Core Huffman tree, encoding/decoding logic
- `HuffmanMain.java`: User interface and main entry point
- `HuffmanCompressor.java`: File I/O and orchestration

---

## Key Classes & Methods

| Class                | Responsibility                                          |
|----------------------|--------------------------------------------------------|
| `HuffmanCode`        | Huffman tree construction, codebook save/load, decoding|
| `HuffmanNode`        | Tree node (frequency, character, left/right children)  |
| `HuffmanMain`        | CLI interface and workflow control                     |
| `HuffmanCompressor`  | File handling, frequency analysis, compression logic   |

### Important Methods

- `HuffmanCode(int[] frequencies)`: Build tree from character frequencies
- `HuffmanCode(Scanner input)`: Rebuild tree from codebook
- `void save(PrintStream output)`: Save codebook in standard format
- `void translate(Scanner input, PrintStream output)`: Decode compressed data

---

## Design Highlights

- **Binary Tree Manipulation:**  
  Constructs and traverses binary trees for optimal coding.

- **Priority Queue:**  
  Efficiently selects nodes with lowest frequencies for merging.

- **Extensible & Readable:**  
  Modular code with clear method headers and inline documentation.

- **Robust I/O:**  
  Handles both text and binary streams, with error checking.

---

## Example Workflow

1. Place your text file in the project directory (e.g., `secretmessage.txt`).
2. Run the program and specify the file.
3. The following files are generated:
   - `secretmessage.code` (codebook)
   - `secretmessage.short` (compressed data)
   - `secretmessage.new` (decompressed output)
4. Verify that `secretmessage.new` matches the original.

---

## Reflection

This project demonstrates practical applications of data structures (trees, queues), bitwise I/O, and algorithmic thinking. It highlights the importance of efficient data representation and is directly relevant to real-world compression formats (e.g., JPEG, MP3).

---

## License

This project was completed as coursework for CSE 143. Please do not submit this code as your own in any academic context.

**Author:** Nathan Cannell  
**Contact:** nathanlcannell@gmail.com  
**Course:** CSE 143, University of Washington

*For questions, please open an issue or contact the author.*
