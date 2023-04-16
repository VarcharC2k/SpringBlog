package Spring.Blog.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

//User 클래스가 자동으로 mysql에 Table화
@Entity
public class User {

    @Id
    //프로젝트에서 연결된 DB의 넘버링 전략을 따름
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 30) //Null값 들어올 수 없음, 최대 30자
    private String Username;

    @Column(nullable = false,length = 100)//Hash 변환을 위하여 넉넉하게 줌
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

    @ColumnDefault("'user'") //Default를 User로 설정, 문자열인것을 알리기 위해 '를 붙여주어야 함
    private String role; //역할의 경우 Enum을 사용하는것이 좋음

    @CreationTimestamp //자동으로 시간을 입력
    private Timestamp createDate;

}
