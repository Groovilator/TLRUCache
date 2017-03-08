package com.personal.datastructures;

import java.util.HashMap;

/**
 */
public class TLRUCache<K, V>
{
	private class Node<K, V>
	{
		public K key;
		public V value;
		public Node previous;
		public Node next;

		public Node(K k, V v, Node p, Node n)
		{
			this.key = k;
			this.value = v;
			this.previous = p;
			this.next = n;
		}
	}

	private Node head;
	private Node tail;
	private HashMap<K, Node> cache;

	public TLRUCache()
	{
		this.head = null;
		this.tail = null;
		this.cache = new HashMap<K, Node>();
	}

	/** Create new node, add to cache, and link as head
	*/
    public void push(K key, V value)
    {
        Node<K, V> newNode = new Node<K, V>(key, value, null, null);

        // Empty cache, set new node as head/tail
   		if(this.head == null)
   		{
   			this.head = newNode;
   			this.tail = newNode;
   		}
   		// Else - set as head and link
   		else
   		{
   			this.head.previous = newNode;
   			newNode.next = this.head;
   			this.head = newNode;
   		}

   		// Added key/node to the cache
   		this.cache.put(key, newNode);
    }

    /** Update HashMap and move Node to head
	*/
    public void update(K key, V value)
    {
    	return;
    }

    /** Remove tail (eldest) node from cache and de-link
	*/
    public void pop()
    {
    	return;
    }

    /** toString method to aid in testing
    *   Return string in the form: "[head.value]...[tail.value]"
	*/
    public String toString()
    {
    	Node currentNode = this.head;

    	String returnStr = "";

		while (currentNode != null) 
		{
			returnStr += "[" + currentNode.value.toString() + "]";
			currentNode = currentNode.next;
		}

		return returnStr;
    }
}
