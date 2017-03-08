package com.personal.datastructures;

import java.util.HashMap;

/** LRU Cache
 */
public class TLRUCache<K, V>
{
	/** Doubly-linked node
	*/
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

	private int size;
	private Node head;
	private Node tail;
	private HashMap<K, Node> cache;

	public TLRUCache()
	{
		this.size = 0;
		this.head = null;
		this.tail = null;
		this.cache = new HashMap<K, Node>();
	}

	/** Create new node, add to cache, and link as head
	*/
    public void push(K key, V value)
    {
    	// Key already present in cache, throw exception
    	if(cache.containsKey(key))
    	{
    		throw new java.lang.IllegalArgumentException("Key already present in cache, try TLRUCache.update()");
    	}

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
   		this.size++;
    }

    /** Update HashMap and move Node to head
	*/
    public void update(K key, V value)
    {
    	// Key not present in cache, throw exception
    	if(!cache.containsKey(key))
    	{
    		throw new java.lang.IllegalArgumentException("Key not present in cache, try TLRUCache.push()");
    	}

		Node node = cache.get(key);

		// Remove element from current position
		Node prevNode = node.previous;
		Node nextNode = node.next;
		prevNode.next = nextNode;
		nextNode.previous = prevNode;

		// Move element to head
		this.head.previous = node;
		node.next = this.head;
		node.previous = null;
		this.head = node;

		// Update cache with updated node
		this.cache.put(key, node);
    }

    /** Remove tail (eldest) node from cache and de-link
	*/
    public void pop()
    {
    	// Empty cache
    	if(this.tail == null)
    	{
    		return;
    	}

    	// Remove from cache
    	this.cache.remove(this.tail.key);

    	// Cache with only one element, de-link tail
    	if(this.tail.previous == null)
    	{
    		this.head = null;
    		this.tail = null;
    	}
    	// De-link tail and set new tail
    	else
    	{
	    	Node prevNode = this.tail.previous;
	    	prevNode.next = null;
	    	this.tail = prevNode;
    	}

    	this.size--;
    }

    /** Get the oldest key without affecting cache state
	*/
    public <K> K peek()
    {
    	// Empty cache
    	if(this.tail == null)
    	{
    		return null;
    	}

    	Node node = this.tail;
    	return (K)node.key;
    }

    /** Way to access cache by key
    */
    public <V> V get(K key)
    {
    	if (!cache.containsKey(key))
    	{
    		return null;
    	}
    	Node node = this.cache.get(key);
    	return (V)node.value;
    }

    /** Utility function to retrieve cache size
	*/
    public int size()
    {
    	return this.size;
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
