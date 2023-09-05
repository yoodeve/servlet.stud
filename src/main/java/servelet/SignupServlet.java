package servelet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> userMap = JsonParseUtil.toMap(request.getInputStream());
		
		System.out.println(userMap);
		
		List<User> userList = UserData.userList;
		
		User user = User.builder()
				.userId(userList.size()+1)
				.username((String) userMap.get("username"))
				.password((String) userMap.get("password"))
				.name((String) userMap.get("name"))
				.email((String) userMap.get("email"))
				.build();
		
		userList.add(user);
		
		// 201:생성(create)코드
		ResponseUtil.response(response).of(201).body(true);
		
	}

}
