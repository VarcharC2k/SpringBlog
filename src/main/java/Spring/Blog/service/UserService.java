package Spring.Blog.service;

import Spring.Blog.model.RoleType;
import Spring.Blog.model.User;
import Spring.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void join(User user){

//        //save에서 오류가 나면 문제가 됨
//        try {
//            userRepository.save(user);
//            return 1;
//        } catch (Exception e){
//            e.printStackTrace();
//            System.out.println("UserService : 회원가입()" + e.getMessage());
//        }
//        return -1;

        String rawPassword = user.getPassword(); //비밀번호 원문
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    //전통적인 방식의 로그인
//    @Transactional(readOnly = true) // readOnly true를 걸면 Select할 떄 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료할때 정합성을 유지할 수 있음
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }

    @Transactional
    public void updateUser(User user) {
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고, 영속화 된 User 오브젝트를 수정해야 함(가장 좋은 방법)
        // select를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위함
        // 영속화를 하면 영속화된 오브젝트를 변경시 자동으로 DB에 업데이트 할 수 있음
        User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원 Id가 없습니다");
        });

        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
        // 회원 수정 함수 종료시 = Service가 종료, 즉 트랜잭션 종료 = Commit 자동으로 수행
        // 영속화 된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 자동으로 수행
    }
}
