/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import utils.xcommon.util.constant.SecurityConstant;

/**
 * 
 * @author xuxiaoming
 * @version $Id: AESUtil.java, v 0.1 2016年8月1日 下午1:59:00 xuxiaoming Exp $
 */
public class AESUtil {

    public AESUtil()
    {
    }

    public static String encrypt(String sSrc, String sKey)
    {
        if(sKey == null)
            throw new IllegalArgumentException("Key\u4E3A\u7A7Anull");
        if(sKey.length() != 16)
            throw new IllegalArgumentException("Key\u957F\u5EA6\u4E0D\u662F16\u4F4D");
        SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes(), "AES");
        try
        {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(SecurityConstant.AES_IVPARAMETER);
            cipher.init(1, skeySpec, iv);
            byte encrypted[] = cipher.doFinal(sSrc.getBytes("UTF-8"));
            return new String((new Base64()).encode(encrypted));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String sSrc, String sKey)
    {
        if(sKey == null)
            throw new IllegalArgumentException("Key\u4E3A\u7A7Anull");
        if(sKey.length() != 16)
            throw new IllegalArgumentException("Key\u957F\u5EA6\u4E0D\u662F16\u4F4D");
        try
        {
            SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(SecurityConstant.AES_IVPARAMETER);
            cipher.init(2, skeySpec, iv);
            byte encrypted1[] = (new Base64()).decode(sSrc.getBytes("UTF-8"));
            return new String(cipher.doFinal(encrypted1));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
