package lh.chatroom.domain.support;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by lh on 2016/6/30.
 */
public abstract class BaseDomain implements Serializable {

    private LocalDateTime createTime = LocalDateTime.now();

    private LocalDateTime modifyTime;

    public abstract String getId();

    public abstract void setId(String id);

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
