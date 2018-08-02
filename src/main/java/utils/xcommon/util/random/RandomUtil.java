/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.random;

import java.util.Random;

/**
 * 
 * @author xuxiaoming
 * @version $Id: RandomUtil.java, v 0.1 2016年8月1日 下午2:10:41 xuxiaoming Exp $
 */
public class RandomUtil {
    private static String randamStr = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static String getRandomString(int length)
    {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++)
        {
            int number = random.nextInt(randamStr.length());
            sb.append(randamStr.charAt(number));
        }

        return sb.toString();
    }
}
