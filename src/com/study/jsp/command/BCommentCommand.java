package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BCommentCommand implements PCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
	
		String boardId = request.getParameter("boardId");
		String bId = (String)session.getAttribute("id");
		String bName = (String)session.getAttribute("name");
		String bComment = request.getParameter("bComment");
		
		System.out.println(boardId + bId + bName + bComment);
		
		BDao dao = new BDao();
		dao.bComment(boardId, bId, bName, bComment);
		
	}

}
