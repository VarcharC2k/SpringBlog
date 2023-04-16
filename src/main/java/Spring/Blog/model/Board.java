package Spring.Blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob //대용량 데이터 인서트시 사용
    private String content; //섬머노트 라이브러리 사용시 <html> 태그를 섞어서 넣기 때문에 크기가 커야함

    @ColumnDefault("0")
    private int count; //조회수

    @ManyToOne //1:* 관계, Many는 Board, One은 유저
    @JoinColumn(name="userId") // 실제 DB 생성시 컬럼명은 userId
    private User user; //작성자, DB는 오브젝트를 저장할수 없기 때문에 FK를 사용

    @CreationTimestamp
    private Timestamp createDate;
}


