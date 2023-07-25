package Spring.Blog.service;

import Spring.Blog.model.Board;
import Spring.Blog.model.RoleType;
import Spring.Blog.model.User;
import Spring.Blog.repository.BoardRepository;
import Spring.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board, User user) { //board가 받는 것은 title, content 2가지임
        board.setCount(0);
        board.setUser(user); //principal로 받은 유저 전달
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board showDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });

    }

    @Transactional
    public void boardDelete(int id) {
        boardRepository.deleteById(id);
    }
}
