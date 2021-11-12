package kr.co.hwang.mvc.parameter;

import kr.co.hwang.mvc.domain.BoardType;
import lombok.Data;

/**
 * 게시물 파라메터
 */
@Data
public class BoardParameter {

	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;
	
	public BoardParameter() {
		
	}
	
	public BoardParameter(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

}
