package board.bean;

import lombok.Data;
@Data

public class BoardDTO {
	private String id;
	private String name;
	private String email;
	private String subject;
	private String content;
	private String logtime;
	private int seq;
	private int ref;
	private int lev;
	private int step;
	private int reply;
	private int hit;
	private int pseq;
}