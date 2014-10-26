import java.util.HashMap;
import java.util.Map;

public class LRU {

	public static class ListNode {
		Integer pageNumber;
		ListNode next;
		ListNode prev;
	}

	ListNode head, tail;

	Map<Integer, ListNode> lruCache;

	public LRU() {
		lruCache = new HashMap<>();
		head = tail = null;
	}

	public void printCache() {
		printCache(head);
		System.out.println();
	}

	private void printCache(ListNode node) {
		if (node != null) {
			System.out.print(node.pageNumber + " ");
			printCache(node.next);
		}
	}

	public void referPage(Integer pageNumber) {
		ListNode node = null;
		
		if (lruCache.containsKey(pageNumber)) {
			node = lruCache.get(pageNumber);

			if (head != node) {
				ListNode prevNode = node.prev;
				ListNode nextNode = node.next;
				if (prevNode != null) {
					prevNode.next = nextNode;
					if (prevNode.next == null)
						tail = node;
				}
				if (nextNode != null) {
					nextNode.prev = prevNode;
				}
			}
		} else {
			node = new ListNode();
			node.pageNumber = pageNumber;
			lruCache.put(pageNumber, node);
		}

		if (head != node) {
			node.next = head;
			if (head != null)
				head.prev = node;

			node.prev = null;

			head = node;

			if (node.next == null)
				tail = node;

			if (lruCache.size() > 3) { // drop tail
				lruCache.remove(tail.pageNumber);

				ListNode tailButOne = tail.prev;
				tailButOne.next = null;
				tail = tailButOne;
			}
		}

		printCache();
	}

	public static void main(String[] args) {
		LRU lru = new LRU();
		lru.referPage(5);
		lru.referPage(4);
		lru.referPage(3);
		lru.referPage(2);
		lru.referPage(1);
		lru.referPage(1);
		lru.referPage(5);
	}

}
