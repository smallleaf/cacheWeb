package com.share1024.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/10/22
 */
public class SerializeUtil {

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            baos.close();
            oos.close();
            return bytes;
        } catch (Exception e) {

        }
        return null;
    }

    public static Object unserialize( byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            bais.close();
            ois.close();
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }
}
