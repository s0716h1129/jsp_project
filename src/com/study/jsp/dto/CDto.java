package com.study.jsp.dto;

public class CDto {
	int boardId;
	String cId;
	String cName;
	String cComment;
	
	public CDto() {

	}

	public CDto(int boardId, String cId, String cName, String cComment) {
		super();
		this.boardId = boardId;
		this.cId = cId;
		this.cName = cName;
		this.cComment = cComment;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcComment() {
		return cComment;
	}

	public void setcComment(String cComment) {
		this.cComment = cComment;
	}
	
	
}
