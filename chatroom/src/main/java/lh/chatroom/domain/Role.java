package lh.chatroom.domain;

import lh.chatroom.domain.support.BaseDomain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lh on 2016/7/8.
 */
@Document(collection = "role")
public class Role extends BaseDomain {
    @Id
    private String id;
    private String name;
    private String code;

    public Role() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
