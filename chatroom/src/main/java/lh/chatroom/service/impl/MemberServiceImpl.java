package lh.chatroom.service.impl;

import com.google.common.base.Strings;
import lh.chatroom.domain.Member;
import lh.chatroom.domain.Role;
import lh.chatroom.domain.Room;
import lh.chatroom.domain.User;
import lh.chatroom.repository.MemberRepository;
import lh.chatroom.service.IMemberService;
import lh.chatroom.service.IRoleService;
import lh.chatroom.service.IRoomService;
import lh.chatroom.service.IUserService;
import lh.chatroom.service.error.ErrorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by lh on 2016/7/16.
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoomService roomService;

    @Override
    public Optional<Member> getByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    @Override
    public Member create(Member member) {
        User user = member.getUser();
        Optional<User> userOptional = userService.getById(user.getId());
        if (Strings.isNullOrEmpty(user.getId()) || !userOptional.isPresent()) {
            throw new IllegalArgumentException(String.format(ErrorInfo.ASSOCIATED_OBJECT_NOT_FOUND, "member", "user(id:" + user.getId() + ")"));
        }
        Role role = member.getRole();
        Optional<Role> roleOptional = roleService.getById(role.getId());
        if (Strings.isNullOrEmpty(role.getId()) || !roleOptional.isPresent()) {
            throw new IllegalArgumentException(String.format(ErrorInfo.ASSOCIATED_OBJECT_NOT_FOUND, "member", "role(id:" + role.getId() + ")"));
        }
        Room room = member.getRoom();
        Optional<Room> roomOptional = roomService.getById(room.getId());
        if (Strings.isNullOrEmpty(room.getId()) || !roomOptional.isPresent()) {
            throw new IllegalArgumentException(String.format(ErrorInfo.ASSOCIATED_OBJECT_NOT_FOUND, "member", "room(id:" + room.getId() + ")"));
        }
        member.setUser(userOptional.get());
        member.setRoom(roomOptional.get());
        member.setRole(roleOptional.get());
        member.setNickName(Strings.isNullOrEmpty(member.getNickName()) ? userOptional.get().getName() : member.getNickName());
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> getById(String id) {
        return Optional.ofNullable(memberRepository.findOne(id));
    }

    @Override
    public void updateNickName(Member member) {
        member.getRoom().getMembers().stream().forEach(item -> {
            if (item.getNickName().equals(member.getNickName()) && !item.getId().equals(member.getId())) {
                throw new RuntimeException("房间内已存在此昵称，请更换其它昵称");
            }
        });
    }

    @Override
    public void delete(Member member) {
        member.getRoom().getMembers().remove(member);
        roomService.update(member.getRoom());
        memberRepository.delete(member);
    }
}
