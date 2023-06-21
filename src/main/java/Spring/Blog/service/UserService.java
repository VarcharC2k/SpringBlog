package Spring.Blog.service;

import Spring.Blog.model.User;
import Spring.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

        userRepository.save(user);
    }

    //전통적인 방식의 로그인
//    @Transactional(readOnly = true) // readOnly true를 걸면 Select할 떄 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료할때 정합성을 유지할 수 있음
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
