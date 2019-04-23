package utils.xcommon;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Test
{
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
    
	public static void main(String[] args) throws Exception {
		Class<?> cls = Class.forName("java.lang.Integer");
		Method method = cls.getDeclaredMethod("hashCode", null);
		Field field = cls.getDeclaredField("MAX_VALUE");
	}
}
