package lh.chatroom.form;

import com.google.common.base.Strings;
import lh.chatroom.domain.User;
import lh.chatroom.form.support.BaseForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

/**
 * Created by lh on 2016/7/7.
 */
public class UserForm extends BaseForm {
    private String name;
    private String password;
    private String oldPassword;
    private String sex;
    private String email;
    private String repeatPwd;

    public UserForm() {
    }

    public UserForm(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setSex(user.getSex().name());
        this.setPassword(user.getPassword());
        this.setOldPassword(user.getPassword());
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRepeatPwd() {
        return repeatPwd;
    }

    public void setRepeatPwd(String repeatPwd) {
        this.repeatPwd = repeatPwd;
    }

    public User asUser() {
        User user = new User();
        if (!Strings.isNullOrEmpty(this.getId())) {
            user.setId(this.getId());
            if (!this.getPassword().equals(this.getOldPassword())) {
                user.setPassword(new BCryptPasswordEncoder().encode(this.getPassword()));
            } else {
                user.setPassword(this.getPassword());
            }
            user.setModifyTime(LocalDateTime.now());
        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(this.getPassword()));
        }
        user.setName(this.getName());
        user.setSex(User.Sex.valueOf(this.getSex()));
        user.setEmail(this.getEmail());
        return user;
    }
}
