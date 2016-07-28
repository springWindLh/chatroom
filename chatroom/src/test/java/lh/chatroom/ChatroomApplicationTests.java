package lh.chatroom;

import com.google.common.collect.Lists;
import lh.chatroom.domain.Member;
import lh.chatroom.domain.Role;
import lh.chatroom.domain.Room;
import lh.chatroom.domain.User;
import lh.chatroom.repository.MemberRepository;
import lh.chatroom.repository.surrport.QueryItem;
import lh.chatroom.service.IMemberService;
import lh.chatroom.service.IRoleService;
import lh.chatroom.service.IRoomService;
import lh.chatroom.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChatroomApplication.class)
@WebAppConfiguration
public class ChatroomApplicationTests {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RedisTemplate redisTemplate;
//    private StringRedisTemplate template;

    @Test
    public void contextLoads() {

    }

    @Test
    public void createUser() {
//        System.out.println("hello");
//        User user = new User();
//        user.setName("小明");
//        user.setSex(User.Sex.MAN);
//        userService.create(user);
//        Role role = new Role();
//        role.setName("角色1");
//        role.setCode("role1");
//        roleService.create(role);
        Room room = new Room();
        room.setName("room1");
        room.setIntroduction("world");
//        Member member = new Member();
//        member.setUser(user);
//        member.setRole(role);
//        memberService.create(member);
//        room.setMembers(Lists.newArrayList(member));
//        roomService.create(room);
            roomService.getById("579712f70cd2961960245c5e");
//        Optional<Room> roomOptional = roomService.getByName("room1");
//        Optional<Member> memberOptional = memberService.getByUserId("578a06400cd29625a069bd22");

//            roomService.findByQueryItems(Lists.newArrayList(new QueryItem("user.name","小明")),0,1,"createdTime", Sort.Direction.DESC);
//            memberRepository.delete("578a06400cd29625a069bd24");
//        redisTemplate.opsForList().leftPush("user1",user);
//        template.opsForValue().set("user", "user1");
//        User tempuser = (User)redisTemplate.opsForList().index("user1",0);
//        System.out.println(tempuser.getName());
    }

}
