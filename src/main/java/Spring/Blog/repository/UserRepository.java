package Spring.Blog.repository;

import Spring.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//DAO와 같은 역할
//자동으로 bean으로 등록이 됨 즉, @Repository가 생략 가능함
public interface UserRepository extends JpaRepository<User, Integer> {

    //SELECT * FROM user WHERE username = ? 라는 쿼리와 동일
    Optional<User> findByUsername(String username);

    //전통적인 방식의 로그인을 위한 함수 생성
    //JPA Naming 쿼리 전략
    //아래와 같이 작성하면 자동으로 Select * From user Where Username=? AND Password = ? 라는 쿼리를 호출함
    //즉, 이름 설정을 findBy변수값 으로 하면 자동으로 해당 쿼리를 만들어 줌
    //따라서 nativeQuery를 작성할 필요가 없음
//    User findByUsernameAndPassword(String username, String password);

    //다음과 같은 방법도 가능함
//    @Query(value = "Select * From user WHERE username = ? AND password = ?", nativeQuery = true)
//    User login(String username, String password);

}
