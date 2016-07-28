package lh.chatroom.form;

/**
 * Created by lh on 2016/7/27.
 */
public class SingleMessage {
    private String roomId;
    private String content;

    public SingleMessage() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
