package com.pan.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pan.service.IBoardService;

import sun.rmi.server.Dispatcher;

@WebServlet("*.pan")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Map<String, IBoardService> commandMap = new HashMap<>();
	
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	loadProperties();
    }
    
	private void loadProperties() {
		// TODO Auto-generated method stub
		ResourceBundle bundle = ResourceBundle.getBundle("com.pan.properties.Command");
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			String command = keys.nextElement();
			String className = bundle.getString(command);
			try {
				Class<?> proClass = Class.forName(className);
				Object obj = proClass.newInstance();
				commandMap.put(command, (IBoardService)obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = getCommand(request);
		IBoardService service = commandMap.get(command);
		try {
			String path = service.execute(request, response);
			if (path.startsWith("redirect:"))   {
				String location = path.substring("redirect:".length());
				response.sendRedirect(location);
			} else  {
				RequestDispatcher dispc = request.getRequestDispatcher(path);
				dispc.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getCommand(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String command = uri.substring(request.getContextPath().length() + 1);
		return command;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
