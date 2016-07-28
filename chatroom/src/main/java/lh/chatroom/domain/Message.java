package lh.chatroom.domain;

import lh.chatroom.domain.support.BaseDomain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lh on 2016/7/6.
 */
@Document(collection = "message")
public class Message extends BaseDomain {
    @Id
    private String id;
    private String content;
    @DBRef
    private User sender;
    @DBRef
    private User accepter;
    @DBRef
    private Room room;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getAccepter() {
        return accepter;
    }

    public void setAccepter(User accepter) {
        this.accepter = accepter;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
