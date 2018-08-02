/**
 * Meilele.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package utils.xcommon.util.security;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import utils.xcommon.util.base64.Base64Encoder;


/**
 * 
 * @author xuxiaoming
 * @version $Id: EncryptUtil.java, v 0.1 2016年8月1日 下午2:02:04 xuxiaoming Exp $
 */
public class EncryptUtil {

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA1";
    public static final String HmacMD5 = "HmacMD5";
    public static final String HmacSHA1 = "HmacSHA1";
    public static final String DES = "DES";
    public static final String AES = "AES";
    private static final Base64Encoder base64Encoder = new Base64Encoder();
    public String charset;
    public int keysizeDES;
    public int keysizeAES;
    public static EncryptUtil me;

    private EncryptUtil()
    {
        charset = "utf-8";
        keysizeDES = 0;
        keysizeAES = 128;
    }

    public static EncryptUtil getInstance()
    {
        if(me == null)
            me = new EncryptUtil();
        return me;
    }

    private String messageDigest(String res, String algorithm)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte resBytes[] = charset != null ? res.getBytes(charset) : res.getBytes();
            return base64(md.digest(resBytes));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String keyGeneratorMac(String res, String algorithm, String key)
    {
        try
        {
            SecretKey sk = null;
            if(key == null)
            {
                KeyGenerator kg = KeyGenerator.getInstance(algorithm);
                sk = kg.generateKey();
            } else
            {
                byte keyBytes[] = charset != null ? key.getBytes(charset) : key.getBytes();
                sk = new SecretKeySpec(keyBytes, algorithm);
            }
            Mac mac = Mac.getInstance(algorithm);
            mac.init(sk);
            byte result[] = mac.doFinal(res.getBytes());
            return base64(result);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String keyGeneratorES(String res, String algorithm, String key, int keysize, boolean isEncode)
    {
        try
        {
        SecretKeySpec sks;
        Cipher cipher;
        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
        if(keysize == 0)
        {
            byte keyBytes[] = charset != null ? key.getBytes(charset) : key.getBytes();
            kg.init(new SecureRandom(keyBytes));
        } else
        if(key == null)
        {
            kg.init(keysize);
        } else
        {
            byte keyBytes[] = charset != null ? key.getBytes(charset) : key.getBytes();
            kg.init(keysize, new SecureRandom(keyBytes));
        }
        SecretKey sk = kg.generateKey();
        sks = new SecretKeySpec(sk.getEncoded(), algorithm);
        cipher = Cipher.getInstance(algorithm);
        if(isEncode)
        {
            cipher.init(1, sks);
            byte resBytes[] = charset != null ? res.getBytes(charset) : res.getBytes();
            return parseByte2HexStr(cipher.doFinal(resBytes));
        }

            cipher.init(2, sks);
            return new String(cipher.doFinal(parseHexStr2Byte(res)));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String base64(byte res[])
    {
        return base64Encoder.encode(res);
    }

    public static String parseByte2HexStr(byte buf[])
    {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < buf.length; i++)
        {
            String hex = Integer.toHexString(buf[i] & 255);
            if(hex.length() == 1)
                hex = (new StringBuilder()).append('0').append(hex).toString();
            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr)
    {
        if(hexStr.length() < 1)
            return null;
        byte result[] = new byte[hexStr.length() / 2];
        for(int i = 0; i < hexStr.length() / 2; i++)
        {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte)(high * 16 + low);
        }

        return result;
    }

    public String MD5(String res)
    {
        return messageDigest(res, "MD5");
    }

    public String MD5(String res, String key)
    {
        return keyGeneratorMac(res, "HmacMD5", key);
    }

    public String SHA1(String res)
    {
        return messageDigest(res, "SHA1");
    }

    public String SHA1(String res, String key)
    {
        return keyGeneratorMac(res, "HmacSHA1", key);
    }

    public String DESencode(String res, String key)
    {
        return keyGeneratorES(res, "DES", key, keysizeDES, true);
    }

    public String DESdecode(String res, String key)
    {
        return keyGeneratorES(res, "DES", key, keysizeDES, false);
    }

    public String AESencode(String res, String key)
    {
        return keyGeneratorES(res, "AES", key, keysizeAES, true);
    }

    public String AESdecode(String res, String key)
    {
        return keyGeneratorES(res, "AES", key, keysizeAES, false);
    }

    public String XORencode(String res, String key)
    {
        byte bs[] = res.getBytes();
        for(int i = 0; i < bs.length; i++)
            bs[i] = (byte)(bs[i] ^ key.hashCode());

        return parseByte2HexStr(bs);
    }

    public String XORdecode(String res, String key)
    {
        byte bs[] = parseHexStr2Byte(res);
        for(int i = 0; i < bs.length; i++)
            bs[i] = (byte)(bs[i] ^ key.hashCode());

        return new String(bs);
    }

    public int XOR(int res, String key)
    {
        return res ^ key.hashCode();
    }


}
