package utils.xcommon;

import java.util.HashMap;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	@VisibleForTesting
    public void testApp()
    {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, "3232");
		map.put("dfdsdwwvwe", "32dfdfwfe32");
		System.out.println(map.get(null).hashCode());
		
		System.out.println(indexFor(map.get("dfdsdwwvwe").hashCode(), 32));

		
//		String str="3232";
//		System.out.println(str.hashCode());
//		int hash =  sun.misc.Hashing.stringHash32("3232");
//		System.out.println(hash);
//		System.out.println("22222");
        assertTrue( true );
    }
	
    static int indexFor(int h, int length) {
        return h & (length-1);
    }
}
