package lh.chatroom.service.impl;

import lh.chatroom.domain.Room;
import lh.chatroom.repository.RoomRepository;
import lh.chatroom.repository.surrport.QueryItem;
import lh.chatroom.repository.surrport.RepositoryUtil;
import lh.chatroom.service.IRoomService;
import lh.chatroom.service.error.ErrorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/7/16.
 */
@Service
public class RoomServiceImpl implements IRoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Room create(Room room) {
        Assert.notNull(room.getName(), "room.name must not be null");
        Optional<Room> roomOptional = this.getByName(room.getName());
        if (roomOptional.isPresent()) {
            throw new RuntimeException(ErrorInfo.ROOM_NAME_IS_EXIST);
        }
        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getByName(String name) {
        return roomRepository.findByName(name);
    }

    @Override
    public Optional<Room> getById(String id) {
        return Optional.ofNullable(roomRepository.findOne(id));
    }

    @Override
    public PagedListHolder<Room> findByQueryItems(List<QueryItem> queryItems, int page, int size, String sortField, Sort.Direction direction) {
        Query query = RepositoryUtil.buildQuery(queryItems);
        query.with(RepositoryUtil.buildPage(page, size, sortField, direction));
        List<Room> rooms = mongoTemplate.find(query, Room.class);
        return RepositoryUtil.buildPagedListHolder(rooms, page, size, sortField, direction);
    }

    @Override
    public void delete(String id) {
        roomRepository.delete(id);
    }

    @Override
    public void update(Room room) {
        roomRepository.save(room);
    }
}
