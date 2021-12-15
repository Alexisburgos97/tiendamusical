package com.aburgos.tiendamusicalweb.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class PayPalClient {
	
	
	private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
			"AXmvGx4rtGU-vuWXWX5seAGsn8Kw7m-oNDPrCNkyqIhWrQBL9qyVfnLAPWRTjvBIshlqqhcTQBvEa4EL", 
			"EAZJjf-S_4Bp7kgqHAjkxkB6Cr8mti4VhSK7R1pQEa0OgxGJJA3IelOsW6e4Qy_EqipGWvBYq8PuegOH" );
	
	PayPalHttpClient client = new PayPalHttpClient(environment);
	
	public PayPalHttpClient client() {
		return this.client;
	}
	
}
