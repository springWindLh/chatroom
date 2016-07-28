package lh.chatroom.service.impl;

import lh.chatroom.domain.User;
import lh.chatroom.repository.UserRepository;
import lh.chatroom.service.IUserService;
import lh.chatroom.service.error.ErrorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * Created by lh on 2016/7/4.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        Assert.notNull(user.getName(), "user.name must not be null");
        Optional<User> userOptional = this.getByName(user.getName());
        if (userOptional.isPresent()) {
            throw new RuntimeException(ErrorInfo.USER_NAME_IS_EXIST);
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }
}
