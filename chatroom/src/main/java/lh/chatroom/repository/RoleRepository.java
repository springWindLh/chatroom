package lh.chatroom.repository;

import lh.chatroom.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by lh on 2016/7/8.
 */
public interface RoleRepository extends MongoRepository<Role, String> {
}
