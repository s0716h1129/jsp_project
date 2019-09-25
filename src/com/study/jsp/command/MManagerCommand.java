package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.MDao;
import com.study.jsp.dto.MDto;

public class MManagerCommand implements PCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{

		MDao mdao = new MDao();
		
		ArrayList<MDto> mdtos = mdao.list();
		request.setAttribute("mlist", mdtos);
	}
}
