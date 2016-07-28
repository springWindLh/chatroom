package lh.chatroom.service;

import lh.chatroom.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/7/8.
 */
public interface IRoleService {
    Role create(Role role);

    List<Role> getAllRoles();

    Optional<Role> getById(String id);
}
