package com.study.jsp.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BListSerchCommand implements PCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		String bType[] = request.getParameterValues("bType_ser");
		String bSerch[] = request.getParameterValues("bSer_t");
		String serch = request.getParameter("serch");
		
		BDao dao = new BDao();
		
		ArrayList<BDto> dtos = dao.listSerch(bType, bSerch, serch);
		request.setAttribute("list", dtos);
		
	}
}