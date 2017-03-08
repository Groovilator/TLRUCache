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

    // Test that updating an entry will bring it to the front of the cache
    public void testUpdate()
    {
        testCache.push("string1", "1");
        testCache.push("string2", "2");
        testCache.push("string3", "3");
        testCache.update("string2", "2");
        
        Assert.assertEquals(testCache.toString(), "[2][3][1]");
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
}
