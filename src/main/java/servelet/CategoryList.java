package servelet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import lombok.ToString;

@WebServlet("/category")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String[] categoryList = { "한식", "체험관광", "카페", "자연명소", "양식", "문화예술" };

	@ToString
	private class Feed {
		private String feedName;
		private String categoryName;

		public Feed(String feedName, String categoryName) {
			this.feedName = feedName;
			this.categoryName = categoryName;
		}
		
		public String getCategoryName() {
			return categoryName;
		}

		public String getFeedInfo() {
			return "feedName: " + feedName + ",categoryName: " + categoryName + "\n";
		}
	}

	private Feed[] FeedArray = { 
			new Feed("1번피드", "한식"),
			new Feed("2번피드", "한식"),
			new Feed("3번피드", "체험관광"),
			new Feed("4번피드", "카페"),
			new Feed("5번피드", "자연명소"),
			new Feed("6번피드", "한식"),
			new Feed("7번피드", "한식"),
			new Feed("8번피드", "한식"),
			new Feed("9번피드", "자연명소"),
			new Feed("10번피드", "문화예술"),
			new Feed("11번피드", "카페"),
			new Feed("12번피드", "문화예술"),
			new Feed("13번피드", "체험관광"),
			};

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println(request.getMethod());
		String categoryName = request.getParameter("categoryName");
		
		if(!checkCategory(categoryName)) {
			response.setCharacterEncoding("UTF-8");
			response.setStatus(400);
			// getWriter : 데이터 전송의 기능을 담고 있음. 있어야만이 데이터전송 기능이 일어남. 
			// 전송시 무조건 문자열로 변환되므로 숫자등이 필요할 시 json형태로 전송
			response.getWriter().println("해당 카테고리는 존재하지 않는 카테고리입니다.");
			return;
		}
		// 람다 전용 스트링
//		AtomicReference<String> responseData = new AtomicReference<>("");
//		
//		findFeedByCategoryName(categoryName).forEach(feed -> {
//			responseData.set(responseData.get() + feed.getFeedInfo());
//		});
		
		Gson gson = new Gson();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(findFeedByCategoryName(categoryName)).toString());
		
	}
	
	private boolean checkCategory(String categoryName) {
		
		for (int i = 0; i < categoryList.length; i++) {
			if(categoryList[i].equals(categoryName)) {
				return true;
			}
		}		
		return false;
	}
	
	private List<Feed> findFeedByCategoryName (String categoryName) {
		List<Feed> feeds = new ArrayList<>();
		
		for (int i = 0; i < FeedArray.length; i++) {
			if(FeedArray[i].getCategoryName().equals(categoryName)) {
				feeds.add(FeedArray[i]);
			}
		}
		
		return feeds;
	}

}
