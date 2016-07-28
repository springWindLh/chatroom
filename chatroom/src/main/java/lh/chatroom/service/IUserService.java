package lh.chatroom.service;

import lh.chatroom.domain.User;

import java.util.Optional;

/**
 * Created by lh on 2016/7/4.
 */
public interface IUserService {
    User create(User user);

    Optional<User> getById(String id);

    Optional<User> getByName(String name);
}
