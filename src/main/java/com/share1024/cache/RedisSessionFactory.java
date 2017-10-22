package com.share1024.cache;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/10/22
 */
public class RedisSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext initData) {
        if (initData != null) {
            String host = initData.getHost();
            if (host != null) {
                return new RedisShiroSession(host);
            }
        }
        return new RedisShiroSession();
    }
}
