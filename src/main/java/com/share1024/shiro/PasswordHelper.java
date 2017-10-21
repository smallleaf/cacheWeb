package com.share1024.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.share1024.model.User;

/**
 *
 * @author small leaf
 * Date:   2017年1月16日 下午6:44:18
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {

        if(StringUtils.isBlank(user.getSalt())){
            user.setSalt(randomNumberGenerator.nextBytes().toHex());
        }
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }


    public static void main(String[] args) {
        PasswordHelper ph = new PasswordHelper();
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setSalt("aaa");
        ph.encryptPassword(user);
        System.out.println(user.getPassword());
        System.out.println(user.getSalt());
    }
}
