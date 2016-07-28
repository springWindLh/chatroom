package lh.chatroom.service.impl;

import lh.chatroom.domain.Message;
import lh.chatroom.repository.MessageRepository;
import lh.chatroom.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by lh on 2016/7/16.
 */
@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message create(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Optional<Message> getById(String id) {
        return Optional.ofNullable(messageRepository.findOne(id));
    }
}
