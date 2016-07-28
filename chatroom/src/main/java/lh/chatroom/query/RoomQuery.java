package lh.chatroom.query;

import com.google.common.base.Strings;
import lh.chatroom.repository.surrport.QueryItem;
import org.springframework.beans.support.PagedListHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/7/19.
 */
public class RoomQuery extends PagedListHolder {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QueryItem> asQueryItems() {
        List<QueryItem> queryItems = new ArrayList<>();
        if (!Strings.isNullOrEmpty(this.getName())) {
            QueryItem item = new QueryItem("name", this.getName());
            queryItems.add(item);
        }
        return queryItems;
    }
}
