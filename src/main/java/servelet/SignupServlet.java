package servelet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputStream inputStream =  request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		StringBuilder requestBody = new StringBuilder("");
		
		while(true) {
			String data = bufferedReader.readLine();
			if(data == null) {
				break;
			}
			requestBody.append(data);
		}
		Gson gson = new Gson();
		
		Map<String, String> userMap = gson.fromJson(requestBody.toString(), Map.class);
		
		System.out.println(userMap);
		System.out.println(userMap.get("username"));
		System.out.println(userMap.get("password"));
		System.out.println(userMap.get("name"));
		System.out.println(userMap.get("email"));
		
	}

}
