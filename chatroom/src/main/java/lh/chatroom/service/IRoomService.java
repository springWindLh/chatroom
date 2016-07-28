package lh.chatroom.service;

import lh.chatroom.domain.Room;
import lh.chatroom.repository.surrport.QueryItem;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/7/16.
 */
public interface IRoomService {
    Room create(Room room);

    Optional<Room> getByName(String name);

    Optional<Room> getById(String id);

    PagedListHolder<Room> findByQueryItems(List<QueryItem> queryItems, int page, int size, String sortField, Sort.Direction direction);

    void delete(String id);

    void update(Room room);
}
