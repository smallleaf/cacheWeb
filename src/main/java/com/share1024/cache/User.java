package com.share1024.cache;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/10/22
 */
public class User implements Serializable {

    private String username;

    private String password;

    private transient  List<String> address ;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                '}';
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("yesheng");
        user.setPassword("hahaha2");

        List<String> list = new ArrayList<>();
        list.add("hahaha2");

        user.setAddress(list);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("user",user);

        User o2 = (User) map.get("user");


        RedisUtil.getInstance().set("user", JSON.toJSONString(user));

        User o21 =  JSON.parseObject(RedisUtil.getInstance().get("user"),User.class);

        System.out.println("====");
        if(o2.getAddress() == null){
            o2.setAddress(list);
        }
        o2.getAddress().add("sd2322323");
        byte[] t =SerializeUtils.serialize(user);
        User o = (User) SerializeUtils.deserialize(t);


        RedisUtil.getInstance().set("user", JSON.toJSONString(user));



        User o3=  JSON.parseObject(RedisUtil.getInstance().get("user"),User.class);


        System.out.println("==");

    }
}
