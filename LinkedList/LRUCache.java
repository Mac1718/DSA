/*
Problem:
Design an LRU (Least Recently Used) cache. It supports get(key) and
put(key, value). Both must run in O(1). When the cache is full and we add
a new item, the least recently used item is removed.

Approach:
Keep a HashMap from key to the node holding its value. Keep all nodes in a
doubly linked list ordered by recent use: most recent at the head, least
recent near the tail. On get or put, move the node to the head. On put when
full, drop the node right before the tail.

Why this works:
The HashMap gives O(1) lookup, and the doubly linked list with dummy head
and tail sentinels lets us add and remove nodes from anywhere in O(1)
without dealing with null edges. Recently used items are always near the
head, so the tail is always the one to evict.

Time Complexity:
O(1) for both get and put.

Space Complexity:
O(capacity) for the map and the list.
*/

import java.util.HashMap;

class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);

        if (node != null) {
            node.value = value;
            moveToHead(node);
            return;
        }

        Node newNode = new Node(key, value);
        map.put(key, newNode);
        addToHead(newNode);

        if (map.size() > capacity) {
            Node removed = tail.prev;
            removeNode(removed);
            map.remove(removed.key);
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
}
