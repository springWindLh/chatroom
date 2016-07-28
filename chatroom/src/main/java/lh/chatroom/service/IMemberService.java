package lh.chatroom.service;

import lh.chatroom.domain.Member;

import java.util.Optional;

/**
 * Created by lh on 2016/7/16.
 */
public interface IMemberService {
    Member create(Member member);

    Optional<Member> getByUserId(String userId);

    Optional<Member> getById(String id);

    void updateNickName(Member member);

    void delete(Member member);
}
