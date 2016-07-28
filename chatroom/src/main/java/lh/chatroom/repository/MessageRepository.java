package lh.chatroom.repository;

import lh.chatroom.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by lh on 2016/7/16.
 */
public interface MessageRepository extends MongoRepository<Message, String> {

}
