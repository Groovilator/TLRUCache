package com.personal.datastructures;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for TLRUCache
 */
public class TLRUCacheTest 
    extends TestCase
{

    TLRUCache<String, String> testCache;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TLRUCacheTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TLRUCacheTest.class );
    }

    // Initalize testCache for each test
    protected void setUp() throws Exception 
    {
        testCache = new TLRUCache<String, String>();
    }

    // Test that pushing adds to cache correctly
    public void testPush()
    {
        testCache.push("string1", "1");
        testCache.push("string2", "2");
        testCache.push("string3", "3");

        Assert.assertEquals(testCache.toString(), "[3][2][1]");
    }

    // Test that exception is thrown if a key is already present in cache when pushed
    public void testPushOfExistingKey()
    {
        try
        {
            testCache.push("string1", "1");
            testCache.push("string2", "2");
            testCache.push("string3", "3");
            testCache.push("string1", "4");

            fail("IllegalArgumentException expected");
        } catch(IllegalArgumentException e) {
            assertEquals("Key already present in cache, try TLRUCache.update()", e.getMessage());
        }
    }

    // Test that updating an entry will bring it to the front of the cache
    public void testUpdate()
    {
        testCache.push("string1", "1");
        testCache.push("string2", "2");
        testCache.push("string3", "3");
        testCache.update("string2", "2");
        
        Assert.assertEquals(testCache.toString(), "[2][3][1]");
    }

    // Test that exception is thrown when attempting to update a key that isn't present in the cache
    public void testUpdateOfNonexistantKey()
    {
        try
        {
            testCache.push("string1", "1");
            testCache.push("string2", "2");
            testCache.push("string3", "3");
            testCache.update("string4", "4");

            fail("IllegalArgumentException expected");
        } catch(IllegalArgumentException e) {
            assertEquals("Key not present in cache, try TLRUCache.push()", e.getMessage());
        }
    }

    // Test that popping removes the eldest entry
    public void testPop()
    {
        testCache.push("string1", "1");
        testCache.push("string2", "2");
        testCache.push("string3", "3");
        testCache.pop();

        Assert.assertEquals(testCache.toString(), "[3][2]");
    }

    // Test that peek returns the key to the oldest entry
    public void testPeek()
    {
        testCache.push("string1", "1");
        testCache.push("string2", "2");
        testCache.push("string3", "3");

        Assert.assertEquals(testCache.peek(), "string1");
    }

    // Test that peek returns null for an empty cache
    public void testPeekEmpty()
    {
        Assert.assertEquals(testCache.peek(), null);
    }

    // Test that get returns the correct value for the given key
    public void testGet()
    {
        testCache.push("string1", "1");
        testCache.push("string2", "2");
        testCache.push("string3", "3");

        Assert.assertEquals(testCache.get("string2"), "2");
    }

    // Test that get returns null for a key that is not in the cache
    public void testGetNonexistant()
    {
        testCache.push("string1", "1");
        testCache.push("string2", "2");
        testCache.push("string3", "3");

        Assert.assertEquals(testCache.get("string4"), null);
    }

    // Test that size returns the correct value for a populated array
    public void testSize()
    {
        testCache.push("string1", "1");
        testCache.push("string2", "2");
        testCache.push("string3", "3");
        testCache.pop();

        Assert.assertEquals(testCache.size(), 2);
    }
}
