package lh.chatroom.service.impl;

import lh.chatroom.domain.CurrentUser;
import lh.chatroom.domain.User;
import lh.chatroom.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by lh on 2016/7/8.
 */
@Service
public class CurrentUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.getByName(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("没有找到名称为" + username + "的用户");
        } else {
            return new CurrentUser(userOptional.get(), new ArrayList<SimpleGrantedAuthority>());
        }
    }
}
