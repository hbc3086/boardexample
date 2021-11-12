package kr.co.hwang.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.co.hwang.framework.data.domain.PageRequestParameter;
import kr.co.hwang.mvc.domain.Board;
import kr.co.hwang.mvc.parameter.BoardParameter;
import kr.co.hwang.mvc.parameter.BoardSearchParameter;

/**
 * 게시판 Repository
 */
@Repository
public interface BoardRepository {

	List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter);
	
	Board get(int boardSeq);
	
	void save(BoardParameter board);
	
	void update(BoardParameter board);
	
	void delete(int boardSeq);
	
}
