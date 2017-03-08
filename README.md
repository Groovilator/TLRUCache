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
              
## Build/Test
While in the source directory with maven and Java installed on the executing 
machine, run
`mvn compile`
to compile. Alternatively,
`mvn test`
will compile the src and run any available tests.