package lh.chatroom.domain;

/**
 * Created by lh on 2016/7/11.
 */
public class CurrentUser {
    private User user;

    public User getUser() {
        return user;
    }

    public String getId() {
        return user.getId();
    }

}
