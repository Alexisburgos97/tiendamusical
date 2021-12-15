package com.aburgos.tiendamusicalweb.paypal;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aburgos.tiendamusicalweb.session.SessionBean;
import com.google.gson.Gson;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;

/**
 * Servlet implementation class PayPalServlet
 */
@WebServlet("/PayPalServlet")
public class PayPalServlet extends HttpServlet {
	
	private static final Logger LOGGER = LogManager.getLogger(PayPalServlet.class);
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PayPalServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Generando orden de PayPal...");
		
		PayPalCreateOrder payPalCreateOrder = new PayPalCreateOrder();
		
		HttpSession session = request.getSession(false);
		
		if(session.getAttribute("sessionBean") != null) {
			SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
			HttpResponse<Order> order = payPalCreateOrder.crearOrden(sessionBean);
			
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(order));
		}
		
	}

}
