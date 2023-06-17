package com.example.gestorcontraseas;
import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char data;
    HuffmanNode left, right;

    public HuffmanNode(int frequency, char data, HuffmanNode left, HuffmanNode right) {
        this.frequency = frequency;
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public class Huffman {
    private static final int ASCII_SIZE = 128;

    public static String encode(String text) {
        int[] frequencies = getFrequencies(text);
        HuffmanNode root = buildHuffmanTree(frequencies);
        Map<Character, String> codes = generateCodes(root);

        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(codes.get(c));
        }
        return encodedText.toString();
    }

    public static String decode(String encodedText, HuffmanNode root) {
        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;
        for (char c : encodedText.toCharArray()) {
            if (c == '0') {
                current = current.left;
            } else if (c == '1') {
                current = current.right;
            }

            if (current.isLeaf()) {
                decodedText.append(current.data);
                current = root;
            }
        }
        return decodedText.toString();
    }

    public static int[] getFrequencies(String text) {
        int[] frequencies = new int[ASCII_SIZE];
        for (char c : text.toCharArray()) {
            frequencies[c]++;
        }
        return frequencies;
    }

    public static HuffmanNode buildHuffmanTree(int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for (char c = 0; c < ASCII_SIZE; c++) {
            if (frequencies[c] > 0) {
                queue.offer(new HuffmanNode(frequencies[c], c, null, null));
            }
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, '\0', left, right);
            queue.offer(parent);
        }

        return queue.peek();
    }

    private static Map<Character, String> generateCodes(HuffmanNode root) {
        Map<Character, String> codes = new HashMap<>();
        generateCodesHelper(root, "", codes);
        return codes;
    }

    private static void generateCodesHelper(HuffmanNode node, String code, Map<Character, String> codes) {
        if (node.isLeaf()) {
            codes.put(node.data, code);
        } else {
            generateCodesHelper(node.left, code + '0', codes);
            generateCodesHelper(node.right, code + '1', codes);
        }
    }
}