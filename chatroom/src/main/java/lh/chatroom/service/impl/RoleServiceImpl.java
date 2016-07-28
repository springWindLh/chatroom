package lh.chatroom.service.impl;

import lh.chatroom.domain.Role;
import lh.chatroom.repository.RoleRepository;
import lh.chatroom.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/7/8.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getById(String id) {
        return Optional.ofNullable(roleRepository.findOne(id));
    }
}
