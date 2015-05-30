/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.irdresearch.tbreach2.shared.model.Users;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServletReminders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServletReminders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destadmin="/ReminderAdmin.jsp";
		String destadmin2="/ReminderManagement.jsp";
        RequestDispatcher dispatcher;
        HttpSession session=request.getSession();
        
        ServerServiceImpl ssl = new ServerServiceImpl();
        String ssn=request.getParameter("admin");
        String pass=request.getParameter("adminpw");
        Users user = null;
        String role = null;
        String status = null;
		if (ssl.authenticate (ssn, pass))
		{

			try
			{
				user = ssl.findUser (ssn);
				role = user.getRole();
				status = user.getStatus();
			}

				
                catch(Exception e){
                	e.printStackTrace ();
              
                }
			if(role.equalsIgnoreCase("admin")&&!(status.equalsIgnoreCase("suspend")) )
			{

				session.setAttribute("users", user);
                dispatcher=getServletContext().getRequestDispatcher(destadmin);
			}
			/*if(status.equalsIgnoreCase("suspend"))
			{
				request.setAttribute("Invalid", "Account Suspended!");
				dispatcher=getServletContext().getRequestDispatcher(destadmin2);
			}*/

			else
			{
				request.setAttribute("Invalid", "Only Admins Allowed or Account Suspended!");
				dispatcher=getServletContext().getRequestDispatcher(destadmin2);
			}
			
			
                dispatcher.forward(request,response);
                return;
		}
		
        else{
        	
        request.setAttribute("Invalid","Invalid Admin Username or Password!");
        														 
        dispatcher=getServletContext().getRequestDispatcher(destadmin2);
        dispatcher.forward(request, response);
        return;
        }

	}

}
