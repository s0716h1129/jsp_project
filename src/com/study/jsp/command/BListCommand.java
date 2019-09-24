package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BListCommand implements PCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{

		BDao dao = new BDao();
		
		ArrayList<BDto> dtos = dao.list();
		request.setAttribute("list", dtos);
	}
}
