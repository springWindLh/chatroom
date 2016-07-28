package lh.chatroom.repository;

import lh.chatroom.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by lh on 2016/7/4.
 */
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);
}
