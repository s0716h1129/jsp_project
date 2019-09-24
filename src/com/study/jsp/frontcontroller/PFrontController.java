package com.study.jsp.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.jsp.command.BWriteCommand;
import com.study.jsp.command.PCommand;

@WebServlet("*.do")
public class PFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
	{
		actionDo(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = null;
		PCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		
		HttpSession session = null;
		session = request.getSession();
		
		if (com.equals("/mLogin.do")) {
			viewPage = "MLogin.jsp";
		} else if (com.equals("/mJoin.do")) {
			viewPage = "MJoin.jsp";
		} else if (com.equals("/mMain.do")) {
			viewPage = "MMain.jsp";
		} else if (com.equals("/mLogout.do")) {
			session.invalidate();
			viewPage = "MLogin.jsp";
		} else if (com.equals("/mDelete.do")) {
			viewPage = "MDelete.jsp";
		}
		
		else if(com.equals("/bWrite_view.do")) {
			viewPage = "BWrite_view.jsp";
		} else if (com.equals("/bWrite.do")) {
			command = new BWriteCommand();
			command.execute(request, response);
			viewPage = "bList.do";
		} else if (com.equals("/bList.do")) {
//			command = new BListCommand();
//			command.execute(request, response);
//			viewPage = "BList.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}