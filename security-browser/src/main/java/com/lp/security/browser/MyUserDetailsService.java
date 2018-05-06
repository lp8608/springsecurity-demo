package com.lp.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author LIPENGAK
 * @Description: 类描述
 * @date 2018-05-05 22:12
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user by username


        //return new User(username,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //实际应用中password应该直接从数据库中读取，此处为了测试直接编码
        String password = passwordEncoder.encode("123456");
        logger.info("登陆用户名：{} , 密码：{}",username,password);
        return new User(username,password,true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
