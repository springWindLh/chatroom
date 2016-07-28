package lh.chatroom.form;

import com.google.common.base.Strings;
import lh.chatroom.domain.Room;
import lh.chatroom.form.support.BaseForm;

import java.time.LocalDateTime;

/**
 * Created by lh on 2016/7/25.
 */
public class RoomForm extends BaseForm {
    private String name;
    private String introduction;
    private String announcement;

    public RoomForm() {
    }

    public RoomForm(Room room) {
        this.setId(room.getId());
        this.setName(room.getName());
        this.setIntroduction(room.getIntroduction());
        this.setAnnouncement(room.getAnnouncement());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Room asRoom() {
        Room room = new Room();
        if (!Strings.isNullOrEmpty(this.getId())) {
            room.setId(this.getId());
            room.setModifyTime(LocalDateTime.now());
        }
        room.setName(this.getName());
        room.setIntroduction(this.getIntroduction());
        room.setAnnouncement(this.getAnnouncement());
        return room;
    }

}
