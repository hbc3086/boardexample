package kr.co.hwang.mvc.parameter;

import java.util.List;

import kr.co.hwang.mvc.domain.BoardType;
import lombok.Data;

/**
 * 게시물 검색 파라메터
 */
@Data
public class BoardSearchParameter {

	private String keyword;
	private List<BoardType> boardTypes;
	
	public BoardSearchParameter() {
		
	}
	
}
