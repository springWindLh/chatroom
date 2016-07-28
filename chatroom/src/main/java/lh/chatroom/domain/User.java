package lh.chatroom.domain;

import lh.chatroom.domain.support.BaseDomain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lh on 2016/6/30.
 */
@Document(collection = "user")
public class User extends BaseDomain {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String password;
    private Sex sex;
    private String avatar;
    private String email;

    public User() {
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public static enum Sex {
        MAN("男"), WOMAN("女");
        private String value;

        private Sex(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
