package com.study.jsp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.MDao;
import com.study.jsp.dto.MDto;

public class MMember_viewCommand implements PCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String mId = request.getParameter("mId");
		MDao mdao = new MDao();
		MDto mdto = mdao.memberView(mId);
		
		request.setAttribute("MMember_view", mdto);
	}

}
