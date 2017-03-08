# Tyler's LRU Cache

Simple LRU Cache implemented in Java via a doubly linked list and HashMap. 

## TLRUCache methods:
**.push(K key, V value)** - Add entry to cache. Raises 
java.lang.IllegalArgumentException if "key" is already present in the cache.
      
**.update(K key, V value)** - Update entry by "key" with the given "value". 
Raises java.lang.IllegalArgumentException if "key" is not present in the cache.

**.pop()** - Remove the eldest entry from the cache.

**.peek()** - Return the eldest key in the cache. Return null if cache is 
empty.

**.get(K key)** - Return the value associated with the given "key" in the 
cache. Returns null if "key" is not present in cache.

**.size()** - Return the current size of the cache.

**.toString()** - Return a string representation of the current cache in the
form "[cache_head_value]...[cache_tail_value]" where "cache_head_value" is the
value of the last modified entry and "cache_tail_value" is the value of the 
eldest entry.

## Example Usage

```java
TLRUCache<String, String> cache = new TLRUCache<String, String>();

cache.push("string1", "1");
cache.push("string2", "2");
cache.push("string3", "3");

// prints "[3][2][1]"
System.out.println(cache.toString());

cache.update("string3", "3");

// prints "[2][1][3]"
System.out.println(cache.toString());

// prints "string3"
System.out.println(cache.peek());

// prints "3"
System.out.println(cache.get("string3"));

cache.pop();

// prints "[2][1]"
System.out.println(cache.toString());

// Raises IllegalArgumentException
cache.update("string3", "3");
```
              
## Build/Test
While in the source directory with Maven and Java installed on the executing 
machine, run
`mvn compile`
to compile. Alternatively,
`mvn test`
will compile the src and run available tests.