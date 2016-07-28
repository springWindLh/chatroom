package lh.chatroom.service;

import lh.chatroom.domain.Message;

import java.util.Optional;

/**
 * Created by lh on 2016/7/16.
 */
public interface IMessageService {
    Message create(Message message);

    Optional<Message> getById(String id);
}
