package lh.chatroom.repository;

import lh.chatroom.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by lh on 2016/7/16.
 */
public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findByName(String name);
}
