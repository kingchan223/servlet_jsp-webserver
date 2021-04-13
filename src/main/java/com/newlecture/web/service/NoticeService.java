package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Comment;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public class NoticeService {
	private final String password = "1111";
	
	public int removeNoticeAll(int[] ids){
		return 0;
	}
	public int pubNoticeAll(int[] ids){
		return 0;
	}
	public int insertNotice(Notice notice){
	
		int result = 0;;
		String sql = "INSERT newlecture.NOTICE (TITLE, CONTENT, WRITER_ID, PUB, FILES) VALUES (?, ?, ?, ?, ?);";
		
		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, notice.getTitle());
			st.setString(2 , notice.getContent());
			st.setString(3, notice.getWriter_id());
			st.setBoolean(4,  notice.getPub());
			st.setString(5,  notice.getFiles());
			result = st.executeUpdate();
			
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public int deleteNotice(int id){
		return 0;
	}
	public int updateNotice(Notice notice){
		return 0;
	}
	public List<Notice >getNoticeNewestList(){
		return null;
	}
	public List<NoticeView> getNoticeList() {
		return getNoticeList("title", "", 1);
	}

	public List<NoticeView> getNoticeViewList(int page) {
		return getNoticeList("title", "", page);
	}

	 
		
	public List<NoticeView> getNoticePubList(String field, String query, int page) {
		List<NoticeView> list = new ArrayList<>();

		String sql = "SELECT *  FROM (SELECT @rownum:=@rownum+1  rnum, N.* "
				+ " FROM newlecture.notice_view3 N, (SELECT @ROWNUM := 0) R WHERE 1=1) list WHERE rnum >= ? AND rnum <= ? AND "
				+ field + " LIKE ? AND pub = true;";

		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
	
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, 1 + (page - 1) * 10);
			st.setInt(2, page * 10);
			st.setString(3, "%" + query + "%");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getDate("regdate");
				String writer_id = rs.getString("writer_id");
				String hit = rs.getString("hit");
				String files = rs.getString("FILES");
				boolean pub = rs.getBoolean("pub");
//				String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("cmt_count");
				NoticeView notice = new NoticeView(id, title, regdate, writer_id, hit, files, cmtCount, pub);
				list.add(notice);
			}

			rs.close();
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
		
	}
	public List<NoticeView> getNoticeList(String field, String query, int page) {

		List<NoticeView> list = new ArrayList<>();

		String sql ="SELECT *  FROM (SELECT @rownum:=@rownum+1  rnum, N.* "
				+ " FROM newlecture.notice_view3 N, (SELECT @ROWNUM := 0) R WHERE 1=1) list WHERE rnum >= ? AND rnum <= ? AND "
				+ field + " LIKE ?;";

		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
	
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, 1 + (page - 1) * 10);
			st.setInt(2, page * 10);
			st.setString(3, "%" + query + "%");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getDate("regdate");
				String writer_id = rs.getString("writer_id");
				String hit = rs.getString("hit");
				String files = rs.getString("FILES");
				boolean pub = rs.getBoolean("pub");
//				String content = rs.getString("CONTENT");
				int cmtCount = rs.getInt("cmt_count");
				NoticeView notice = new NoticeView(id, title, regdate, writer_id, hit, files, cmtCount, pub);
				list.add(notice);
			}

			rs.close();
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}

	public int getNoticeCount(String field, String query) {

		int count = 0;
		String sql = "SELECT COUNT(N.ID) COUNT " + "FROM (SELECT @rownum:=@rownum+1  rnum, N.* "
				+ " FROM newlecture.notice_view N, (SELECT @ROWNUM := 0) R WHERE 1=1) list WHERE " + field + " LIKE ?;";
		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			ResultSet rs = st.executeQuery();

			count = rs.getInt("count");// 소문자 count를 써도 가능하다.

			rs.close();
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public Notice getNotice(int id) {

		Notice notice = null;

		String sql = "SELECT * FROM newlecture.NOTICE WHERE ID=?";

		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getDate("regdate");
				String writer_id = rs.getString("writer_id");
				String hit = rs.getString("hit");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("pub");
				
				notice = new Notice(nid, title, regdate, writer_id, hit, files, content, pub);

			}

			rs.close();
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return notice;
	}

	public int getNoticeCount2() {

		int count = 0;

		String sql = "SELECT * FROM newlecture.NOTICE;";

		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				count++;
			}

			rs.close();
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	public Notice getNextNotice(int id) {
		Notice notice = null;
		String sql = "SELECT " + "    A.ID " + "FROM " + " ( " + "    SELECT "
				+ "        @ROWNUM := @ROWNUM + 1 AS ROWNUM, " + "        NOTICE.* " + "        " + "    FROM "
				+ "        NOTICE, " + "       (SELECT @ROWNUM := 0) R " + "        WHERE ID>? " + ") A " + " WHERE "
				+ "    A.ROWNUM = 1; ";

		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getDate("regdate");
				String writer_id = rs.getString("writer_id");
				String hit = rs.getString("hit");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("pub");
				notice = new Notice(nid, title, regdate, writer_id, hit, files, content, pub);

			}

			rs.close();
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}

	public Notice PrevgetNotice(int id) {
		Notice notice = null;
		String sql = "SELECT " + "    A.ID " + " FROM " + " ( " + "    SELECT "
				+ "        @ROWNUM := @ROWNUM + 1 AS ROWNUM, " + "        NOTICE.* " + "        " + "    FROM "
				+ "        NOTICE, " + "       (SELECT @ROWNUM := 0) R " + "        WHERE ID>? " + " ) A " + " WHERE "
				+ "    A.ROWNUM = 1; ";

		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				Date regdate = rs.getDate("regdate");
				String writer_id = rs.getString("writer_id");
				String hit = rs.getString("hit");
				String files = rs.getString("FILES");
				String content = rs.getString("CONTENT");
				boolean pub = rs.getBoolean("pub");
				notice = new Notice(nid, title, regdate, writer_id, hit, files, content, pub);

			}

			rs.close();
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}

	public List<Comment> getComment(int notice_id) {
		List<Comment> coList = new ArrayList<>();
		
		String sql = "SELECT * FROM newlecture.COMMENT WHERE notice_id=?;";
		int id;
		String content;
		Date regdate;
		String writer_id;

		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement st = connection.prepareStatement(sql);
			st.setInt(1, notice_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				id  = rs.getInt("id");
				content = rs.getString("content");
				regdate = rs.getDate("regdate");
				writer_id = rs.getString("writer_id");
				
				Comment comment = new Comment(id, content, regdate, writer_id, notice_id);
				coList.add(comment);
			}
			
			rs.close();
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return coList;
	}
	public int deleteNoticeAll(int[] ids) {
		
		int result = 0;
		String params = "";
		for(int i=0; i<ids.length; i++) {
			params += ids[i];
			if(i < ids.length-1)
				params += ", ";
		}
		String sql = "DELETE newlecture.NOTICE WHERE ID IN ("+params+");";
		int id;
		String content;
		Date regdate;
		String writer_id;

		Connection connection;
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String driverName = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			Statement st = connection.createStatement();
			result = st.executeUpdate(sql);
			
			st.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
//	public void upHit(int id) {
//		int result = 0;
//
//		String sql = "DELETE newlecture.NOTICE WHERE ID = ?);";
//		int id;
//		String content;
//		Date regdate;
//		String writer_id;
//
//		Connection connection;
//		String url = "jdbc:mysql://localhost:3306/";
//		String user = "root";
//		String password = "dlcksdud12~";
//		String driverName = "com.mysql.cj.jdbc.Driver";
//
//		try {
//			Class.forName(driverName);
//			connection = DriverManager.getConnection(url, user, password);
//			Statement st = connection.createStatement();
//			result = st.executeUpdate(sql);
//			
//			st.close();
//			connection.close();
//
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//		
//	}
}
