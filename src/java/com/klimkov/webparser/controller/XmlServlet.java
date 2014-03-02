package com.klimkov.webparser.controller;
import com.klimkov.webparser.creator.InitBuilder;
import com.klimkov.webparser.unit.AirplaneList;
import java.io.IOException; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

public class XmlServlet extends javax.servlet.http.HttpServlet {
        
        @Override
	protected void doGet(HttpServletRequest request,  HttpServletResponse response)  throws ServletException, IOException { 
        
        }
        
        @Override
	protected void doPost(HttpServletRequest request,  HttpServletResponse response)  throws ServletException, IOException { 
		Integer value = new Integer(request.getParameter("parser"));
                AirplaneList al = null;
                switch(value){
                    case 1:
                        al = InitBuilder.initSax(request.getServletContext().getRealPath("resource/aircraft.xml"));
                        request.setAttribute("planelist", al);                        
                        break;
                    case 2: 
                        al = InitBuilder.initStax(request.getServletContext().getRealPath("resource/aircraft.xml"));
                        request.setAttribute("planelist", al);
                        break;
                    case 3:
                        al = InitBuilder.initDom(request.getServletContext().getRealPath("resource/aircraft.xml"));
                        request.setAttribute("planelist", al);
                        break;
                    default: break;
                };
                RequestDispatcher dispatcher =  request.getRequestDispatcher("jsp/output.jsp");   
                dispatcher.forward(request, response);

	}   

}
