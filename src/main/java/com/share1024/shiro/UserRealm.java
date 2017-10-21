package com.share1024.shiro;

import com.share1024.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by yesheng on 2017/10/16.
 */
public class UserRealm extends AuthorizingRealm{


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        if(StringUtils.isBlank(username)&&!"admin".equalsIgnoreCase(username)){
            throw new UnknownAccountException();
        }
        User user = new User();
        user.setUsername("admin");
        user.setPassword("9be1fd17d31ec355f52862e56263c528");
        user.setSalt("aaa");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                getName());
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
