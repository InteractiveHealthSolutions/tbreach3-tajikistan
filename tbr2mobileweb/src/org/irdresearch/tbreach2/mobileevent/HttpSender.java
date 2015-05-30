/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.mobileevent;



import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Hashtable;



public class HttpSender {
	
	private boolean currentWaitForResponse;
	
	public String		baseUrl;
	public Hashtable	model;
	


	public Hashtable doPost(String baseUrl) {
		HttpURLConnection hc = null;
		OutputStream os = null;
		String url = null;
		int responseCode;
		boolean successful = false;
		boolean waitForResponse;
		model = null;
		//XMLResponseModel responseModel = null;
		URL obj = null;
		try {
			obj = new URL(baseUrl);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("<doPost>");
		System.out.println("making URL");
		//url = entry.getUrl();
		System.out.println("making URL");
		waitForResponse = true;
		currentWaitForResponse = waitForResponse;
		
		System.out.println("URL:" + url);

		
		System.out.println("WaitForResponse = " + String.valueOf(waitForResponse));
		System.out.println("Connecting");
		try {
			//FileWriter.rite("Post Paramters :" + entry.getPostParams(), tbrMidlet);
			hc = (HttpURLConnection) obj.openConnection();
			
			hc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			hc.setRequestProperty("User-Agent", "Profile/MIDP-2.0 Configuration/CLDC-1.0");
			hc.setRequestProperty("Content-Language", "en-US");
			hc.connect();
			
			responseCode = hc.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {

				throw new IOException("Http response code: " + responseCode);
			}
			
			if (waitForResponse) {

				System.out.println("Parsing response");

				//model = XmlUtil.parseXmlResponse(new InputStreamReader(hc.openInputStream()));

				System.out.println("Response Complete");
				successful = true; // the progress listener handles if this is null
			} else {


				System.out.println("Parsing response");

				}

				System.out.println("Response Complete");
				
	

		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Not an HTTP URL");
		} catch (IOException e) {

			e.printStackTrace();
		} catch (SecurityException e) {

			e.printStackTrace();					
		}  catch (Exception e)  {
			if (e instanceof InterruptedException) {				
		
			}
			e.printStackTrace();			
		} finally {

			if (os != null) {
				try {
					System.out.println("Closing output stream");
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
/*			if (hc != null) {
				try {
					System.out.println("Closing http connection");
					//hc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/

			System.out.println("</doPost>");
			
		}	
		return model;
	}


}
