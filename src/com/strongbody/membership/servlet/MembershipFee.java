package com.strongbody.membership.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.strongbody.beans.Membership;
import com.strongbody.beans.MembershipPrice;
import com.strongbody.utils.MembershipUtils;

@WebServlet(urlPatterns= {"/app/membershipFee"})
public class MembershipFee extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(MembershipFee.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{	
		
		double daysInMonth=30.45;
		
		RequestDispatcher dispatcher = null;
		//Get parameter from membership.jsp href ( &amp;paymentStatus=${membership.paymentStatus} )
		String status = request.getParameter("paymentStatus");
		
		//If status is Payed forward to payedMembership.jsp and stop the code execution below
		if(status.equals("Payed")) {
		    dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/membershipJSP/payedMembership.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//If status is Not Payed get parameters from membership.jsp href ("membershipFee?membershipType=${membership.membershipType }&amp;id=${membership.id} )
		String fee = request.getParameter("membershipType");
		String service = request.getParameter("serviceLevel");
		String idStr = request.getParameter("id");
		
		Membership membership = null;
		int id = 0;
		
		try {
			id= Integer.parseInt(idStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		//Find membership
		try{
			membership = MembershipUtils.findMembership(id);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		
		//Get the date difference between the membership valid from and valid through dates
		int dateDiff=0;
		try {
			dateDiff=MembershipUtils.dateDifference(membership);
		}catch(SQLException e) {
			LOGGER.error(e);
		}
		System.out.println(service);

		
		//Set membership object to "membership" attribute so it can be used in membershipFee.jsp
		request.setAttribute("membership", membership);
		
		// Create a new MembershipPrice object
		MembershipPrice memPrice = new MembershipPrice();
		
		//Get different prices based on membership type
		if(fee.equals("Daily")) {
			if(service.equals("1x Week")) {
				request.setAttribute("price", (int) (memPrice.dailyFee()*dateDiff/7));
			}else if(service.equals("2x Week")) {
				request.setAttribute("price", (int) (memPrice.dailyFee()*dateDiff/7)*2);
			}else if(service.equals("3x Week")) {
				request.setAttribute("price", (int) (memPrice.dailyFee()*dateDiff/7)*3);
			}else if(service.equals("4x Week")) {
				request.setAttribute("price", (int) (memPrice.dailyFee()*dateDiff/7)*4);
			}else if(service.equals("5x Week")) {
				request.setAttribute("price", (int) (memPrice.dailyFee()*dateDiff/7)*5);
			}else if(service.equals("6x Week")) {
				request.setAttribute("price", (int) (memPrice.dailyFee()*dateDiff/7)*6);
			}else if(service.equals("Unlimited")) {
				request.setAttribute("price", (int) (memPrice.dailyFee()*dateDiff/7)*7);
			}
		}else if(fee.equals("Weekly")) {
			request.setAttribute("price", (int) (memPrice.weeklyFee()*dateDiff/7));
		}else if(fee.equals("Monthly")) {
			request.setAttribute("price", (int) (memPrice.monthlyFee()*dateDiff/daysInMonth));
		}else if(fee.equals("Trimester")) {
			request.setAttribute("price", (int) (memPrice.trimesterFee()*dateDiff/(daysInMonth*3)));
		}else if(fee.equals("Biannual")) {
			request.setAttribute("price", (int) (memPrice.biannualFee()*dateDiff/(daysInMonth*6)));
		}else if(fee.equals("Annual")) {
			request.setAttribute("price", (int) (memPrice.annualFee()*dateDiff/(daysInMonth*12)));
		}
		

		//Forward to membershipFee.jsp
		dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/app/membershipJSP/membershipFee.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,IOException{

		RequestDispatcher dispatcher = null;
		
		//Get parameters from hidden input in membershipFee.jsp POST method
		String idStr = request.getParameter("id");
		String registrationDateStr = request.getParameter("registrationDate");
		String validFromStr = request.getParameter("validFrom");
		String validThroughStr = request.getParameter("validThrough");
		String serviceLevel = request.getParameter("serviceLevel");
		String membershipType = request.getParameter("membershipType");
		
		//Set to "Payed" in POST method
		String paymentStatus = request.getParameter("payment"); 
		
		int id = 0;
		Date registrationDate = null;
		Date validFrom = null;
		Date validThrough = null;
		String error = null;
		
		try {
			id = Integer.parseInt(idStr);
			registrationDate = Date.valueOf(registrationDateStr);
			validFrom = Date.valueOf(validFromStr);
			validThrough = Date.valueOf(validThroughStr);
		}catch(NumberFormatException e) {
			LOGGER.error(e);
		}
		
		// Create new membership object based on hidden fields in POST method( nothing is changed except PaymentStatus)
		Membership membership = new Membership(id,registrationDate,validFrom,validThrough,serviceLevel,membershipType);
		membership.setPaymentStatus(paymentStatus);

		try{
			//Update membership with payment method from MembershipUtils(Changes paymentStatus to "Payed")
			LOGGER.info("Membership has been payed for!");
			MembershipUtils.paymentUpdate(membership);
		}catch(SQLException e) {
			LOGGER.error(e);
			error = e.getMessage();
		}

		// Forward if there was an error, redirect if everyhing is ok
		if(error != null) {
			//Forwarding uses the given jsp to present the results
			dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp");
			dispatcher.forward(request, response);

		}else {
			//Redirect sends a new http request(changes the url)
			response.sendRedirect(request.getContextPath() + "/app/memberList");
		}

		
	}

}
