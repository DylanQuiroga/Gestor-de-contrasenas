package com.example.gestorcontraseas;
import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    char character;
    int frequency;
    HuffmanNode leftChild;
    HuffmanNode rightChild;

    public HuffmanNode(char character, int frequency, HuffmanNode leftChild, HuffmanNode rightChild) {
        this.character = character;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}

public class Huffman {
    private static Map<Character, String> charToCode = new HashMap<>();
    private static Map<String, Character> codeToChar = new HashMap<>();

    public static String compress(String message) {
        Map<Character, Integer> frequencyMap = buildFrequencyMap(message);
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        buildCodeMaps(root, "");

        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            encodedMessage.append(charToCode.get(c));
        }

        return encodedMessage.toString();
    }

    public static String decompress(String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        StringBuilder code = new StringBuilder();

        for (char c : encodedMessage.toCharArray()) {
            code.append(c);
            if (codeToChar.containsKey(code.toString())) {
                decodedMessage.append(codeToChar.get(code.toString()));
                code.setLength(0);
            }
        }

        return decodedMessage.toString();
    }

    private static Map<Character, Integer> buildFrequencyMap(String message) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : message.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;
    }

    private static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        for (char c : frequencyMap.keySet()) {
            pq.offer(new HuffmanNode(c, frequencyMap.get(c), null, null));
        }

        while (pq.size() > 1) {
            HuffmanNode leftChild = pq.poll();
            HuffmanNode rightChild = pq.poll();

            HuffmanNode parent = new HuffmanNode('\0', leftChild.frequency + rightChild.frequency, leftChild, rightChild);
            pq.offer(parent);
        }

        return pq.poll();
    }

    private static void buildCodeMaps(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            charToCode.put(node.character, code);
            codeToChar.put(code, node.character);
            return;
        }

        buildCodeMaps(node.leftChild, code + "0");
        buildCodeMaps(node.rightChild, code + "1");
    }
}
