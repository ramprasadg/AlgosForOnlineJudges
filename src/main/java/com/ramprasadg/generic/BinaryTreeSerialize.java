package src.main.java.com.ramprasadg.generic;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeSerialize {

	private final Node root;

	public BinaryTreeSerialize(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public static class Node {

		private int data;
		private Node left = null;
		private Node right = null;

		public Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node n) {
			left = n;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node n) {
			right = n;
		}

		public int getData() {
			return data;
		}

		public void setData(int d) {
			data = d;
		}
	}

	public String serialize() {
		Queue<Node> q[] = new LinkedList[2];

		for (int i = 0; i < 2; i++) {
			q[i] = new LinkedList<Node>();
		}

		int level = 0;
		int nextLevel = 1;

		if (getRoot() == null)
			return "";
		else
			q[level].add(getRoot());

		StringBuffer buffer = new StringBuffer();

		while (q[level].size() > 0) {
			Node node = q[level].poll();

			if (node == null) {
				buffer.append(" #");
			} else {
				buffer.append(" " + node.getData());
				Node left = node.getLeft();
				Node right = node.getRight();
				q[nextLevel].add(left);
				q[nextLevel].add(right);
			}

			if (q[level].size() <= 0 && q[nextLevel].size() > 0) {
				level = nextLevel;
				nextLevel = (level + 1) % 2;
				buffer.append("\n");
			}
		}

		return buffer.toString();
	}

	public static Node deSerialize(String serializedString) {
		String[] levels = serializedString.split("\n");

		if (levels.length < 1)
			return null;

		Node root = null;

		Queue<Node> q[] = new LinkedList[2];

		for (int i = 0; i < 2; i++) {
			q[i] = new LinkedList<Node>();
		}

		for (int i = 0; i < levels.length; i++) {
			String[] level = levels[i].split(" ");
			int currentLevel = i % 2;
			
			for (int j = 1; j < level.length; j += 2) {
				String leftNodeValue = level[j];
				String rightNodeValue = "#";
				if(j + 1 < level.length)
					rightNodeValue = level[j + 1];

				Node leftNode = null, rightNode = null;

				if (! leftNodeValue.equals("#")) {
					leftNode = new Node(Integer.parseInt(leftNodeValue), null, null);
					q[currentLevel].add(leftNode);
				}

				if (! rightNodeValue.equals("#")) {
					rightNode = new Node(Integer.parseInt(rightNodeValue), null, null);
					q[currentLevel].add(rightNode);
				}

				if (root == null) {
					root = leftNode;
				} else {
					int previousLevel = (i - 1) % 2;
					Node parent = q[previousLevel].poll();
					parent.setLeft(leftNode);
					parent.setRight(rightNode);
				}
			}
		}

		return root;
	}

	public static void main(String args[]) {
		Node node4 = new Node(4, null, null);
		Node node3 = new Node(3, null, null);
		Node node2 = new Node(2, null, node4);
		Node node1 = new Node(1, node2, node3);

		BinaryTreeSerialize bts = new BinaryTreeSerialize(node1);
		String serializedString = bts.serialize();
		System.out.println(serializedString);

		BinaryTreeSerialize bts2 = new BinaryTreeSerialize(BinaryTreeSerialize.deSerialize(serializedString));
		System.out.println(bts2.serialize());
	}
}
