package lh.chatroom.repository;

import lh.chatroom.domain.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by lh on 2016/7/16.
 */
public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByUserId(String userId);
}
