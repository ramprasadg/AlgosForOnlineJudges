package generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class GraphQuantcast {
    static class Node {
        public char val;
        public List<Node> neighbours = new ArrayList<Node>();

        public Node(char val) {
            this.val = val;
        }
    }

    List<List<Node>> modifiedInput = new ArrayList<List<Node>>();

    public void createGraph(List<String> input) {
        for (int layer = 0; layer < input.size(); layer++) {
            modifiedInput.add(new ArrayList<Node>());
            String layerCharacterList = input.get(layer);
            List<Node> layerNodeList = modifiedInput.get(layer);
            for (int i = 0; i < layerCharacterList.length(); i++) {
                Node n = new Node(layerCharacterList.charAt(i));
                layerNodeList.add(n);
            }
        }

        if (input.size() == 1) {
            return;
        }

        Node coreNode = modifiedInput.get(0).get(0);
        List<Node> layer1 = modifiedInput.get(1);
        for (Node layer1Node : layer1) {
            link(coreNode, layer1Node);
        }

        for (int layer = 1; layer < modifiedInput.size(); layer++) {
            List<Node> layerList = modifiedInput.get(layer);
            List<Node> nextLayerList = null;
            int j = -1;
            if (layer < modifiedInput.size() - 1) {
                nextLayerList = modifiedInput.get(layer + 1);
                j = nextLayerList.size() - 1;
            }

            for (int i = 0; i < layerList.size(); i++) {
                Node currentNode = layerList.get(i);
                Node prevNode = null;
                if (i == 0) {
                    prevNode = layerList.get(layerList.size() - 1);
                } else {
                    prevNode = layerList.get(i - 1);
                }

                link(prevNode, currentNode);

                if (j != -1) {
                    int numberOfNeighbours = (i % layer == 0) ? 3 : 2;
                    while (numberOfNeighbours > 0) {
                        numberOfNeighbours--;
                        Node neighbour = nextLayerList.get(j);
                        j = (j + 1) % nextLayerList.size();
                        link(neighbour, currentNode);
                    }
                    j = j - 1;
                }
            }
        }
    }

    void printAdjList() {
        for (List<Node> layer : modifiedInput) {
            for (Node n : layer) {
                System.out.print(n.val + "-> ");
                for (Node neighbour : n.neighbours) {
                    System.out.print(neighbour.val + ", ");
                }
                System.out.println();
            }
        }
    }

    private void link(Node node1, Node node2) {
        node1.neighbours.add(node2);
        node2.neighbours.add(node1);
    }

    Set<Node> visitedNode = new HashSet<Node>();

    public Boolean dfs(Node node, String searchString) {
        if (visitedNode.contains(node)) {
            return false;
        }

        if (searchString.isEmpty()) {
            return true;
        }

        if (node.val != searchString.charAt(0)) {
            return false;
        }

        visitedNode.add(node);
        boolean result = false;
        for (int i = 0; i < node.neighbours.size(); i++) {
            result = dfs(node.neighbours.get(i), searchString.substring(1));
            if (result) {
                break;
            }
        }
        visitedNode.remove(node);
        return result;
    }

    public static void main(String[] args) {
        GraphQuantcast g = new GraphQuantcast();
        List<String> input = new ArrayList<String>();

        input.add("A");
        input.add("BCDEFG");
        input.add("UANTCASTYSWQ");
        input.add("EORNOTOBEKANGARTOB");
        input.add("LUYAGIMMXVRHPJITSOOTHEPZ");

        g.createGraph(input);
        // g.printAdjList();

        List<String> dictionary = new ArrayList<String>();
        dictionary.add("STING");
        dictionary.add("STIG");
        dictionary.add("X");
        dictionary.add("A");

        List<String> outputDictionary = new ArrayList<String>();
        for (String string : dictionary) {
            outer: for (List<Node> layerList : g.modifiedInput) {
                for (Node n : layerList) {
                    boolean endResult = g.dfs(n, string);
                    if (endResult) {

                        outputDictionary.add(string);
                        break outer;
                    }
                }
            }
        }
        outputDictionary.sort(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String s : outputDictionary) {
            System.out.println(s);
        }
    }
}
