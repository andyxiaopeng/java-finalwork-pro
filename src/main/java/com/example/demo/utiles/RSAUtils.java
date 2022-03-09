package com.example.demo.utiles;



import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;


public class RSAUtils {

    public static final String ENCRYPT_TYPE = "RSA";

    public static final String PUBLIC_KEY = "publicKey";
    public static final String PRIVATE_KEY = "privateKey";

    public static HashMap<String,Object> RSAkeyMap;

    /**
     * 初始化RSA类
     * 获取公钥和私钥
     */
    public void iniRSA(){
        RSA rsa = new RSA();


        KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE);
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();
        // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
        byte[] pubEncBytes = publicKey.getEncoded();
        byte[] priEncBytes = privateKey.getEncoded();

        // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
        String pubEncBase64 = new Base64().encode(pubEncBytes);
        String priEncBase64 = new Base64().encode(priEncBytes);


        RSAkeyMap = new HashMap<String,Object>();
        RSAkeyMap.put(PUBLIC_KEY,pubEncBase64);
        RSAkeyMap.put(PRIVATE_KEY,priEncBase64);
        return;
    }

    /**
     * 公钥加密
     * @param key
     * @return
     */
    public String publicKeyEncrypt(String key){
        return null;
    }

    /**
     * 私钥加密
     * @param key
     * @return
     */
    public String privateKeyEncrypt(String key){
        return null;
    }

    /**
     * 公钥解密
     * @param content
     * @return
     */
    public String publicKeyDecrypt(String content){
        RSA rsa = new RSA((String) RSAkeyMap.get(PUBLIC_KEY),null);
        return rsa.decryptStr(content, KeyType.PublicKey);
    }

    /**
     * 私钥解密
     * @param content
     * @return
     */
    public String privateKeyDecrypt(String content){
//        System.out.println(content);
        RSA rsa = new RSA((String) RSAkeyMap.get(PRIVATE_KEY),null);
        return rsa.decryptStr(content, KeyType.PrivateKey);
    }

}
