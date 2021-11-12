package kr.co.hwang.mvc.domain;

import java.util.Date;

import lombok.Data;

/**
 * 게시물
 */
@Data
public class Board {

	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;
	private Date regDate;
	
}
