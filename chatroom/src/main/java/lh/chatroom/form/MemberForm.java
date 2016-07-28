package lh.chatroom.form;

import com.google.common.base.Strings;
import lh.chatroom.domain.Member;
import lh.chatroom.domain.Role;
import lh.chatroom.domain.Room;
import lh.chatroom.form.support.BaseForm;

import java.time.LocalDateTime;

/**
 * Created by lh on 2016/7/26.
 */
public class MemberForm extends BaseForm {
    private String nickName;
    private String userId;
    private String roleId;
    private String roomId;

    public MemberForm() {
    }

    public MemberForm(Member member) {
        this.setId(member.getId());
        this.setNickName(member.getNickName());
        this.setUserId(member.getUser().getId());
        this.setRoleId(member.getRole().getId());
        this.setRoomId(member.getRoom().getId());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Member asMember() {
        Member member = new Member();
        if (!Strings.isNullOrEmpty(this.getId())) {
            member.setId(this.getId());
            member.setModifyTime(LocalDateTime.now());
        }
        Role role = new Role();
        role.setId(this.getRoleId());
        Room room = new Room();
        room.setId(this.getRoomId());
        member.setRole(role);
        member.setRoom(room);
        member.setNickName(this.getNickName());
        return member;
    }
}
