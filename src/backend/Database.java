package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.*;


class Image {

	public int image_id;
	public double latitude;
	public double longitude;
	public int heading;
	public int pitch;
	public String answer_a;
	public String answer_b;
	public String answer_c;
	public String answer_d;
	public String correct_answer;
	
	
	public Image(int im, double lat, double lon, int head, int pit,
		String a, String b, String c, String d, String correct)
	{
		this.image_id = im;
		this.latitude = lat;
		this.longitude = lon;
		this.heading = head;
		this.pitch = pit;
		this.answer_a = a;
		this.answer_b = b;
		this.answer_c = c;
		this.answer_d = d;
		this.correct_answer = correct;
	};
};

public class Database {
	
	public String username;
	public String pass_hash;
	public int jeff_embs;
	
	
	/*Creates a new registered user
	 * Input: user name, hashed password
	 * Output: (void) adds user with 0 emblems
	 */
	public void create_user(String user, String hashing)
	{
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/geoq_data?user=root&password=root&useSSL=false");
			st = conn.createStatement();	
			ps = conn.prepareStatement("INSERT INTO `geoq_data`.`users` (`username`, `password_hash`, `num_jeff_emblems`) VALUES (?, ?, '0')");
			ps.setString(1, user);
			ps.setString(2, hashing);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	};
	
	
	/* Checks whether a user name is 
	 * already in the system
	 * Input: user name
	 * Output: true if exists, 0 otherwise
	 */
	public Boolean verify_user(String name)
	{
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/geoq_data?user=root&password=root&useSSL=false");
			st = conn.createStatement();	
			ps = conn.prepareStatement("SELECT u.username FROM users u WHERE u.username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next())
			{
				String data_name = rs.getString("username");
				if(data_name.equals(name))
				{
					return true;
				}
				return false;
			}
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return false;
	};
	
	
	/*
	 * Input: user name
	 * Output: password hashing associated
	 * with the user name in database
	 */
	public String get_pass(String name)
	{
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String hash = "";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/geoq_data?user=root&password=root&useSSL=false");
			st = conn.createStatement();	
			ps = conn.prepareStatement("SELECT u.password_hash FROM users u WHERE u.username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next())
			{
				hash = rs.getString("password_hash");
			}
		}
		
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		
		return hash;	
	};
	
	
	/*Input: user name
	 * Output: number of jeffrey miller emblems
	 */
	public int get_num_embs(String name)
	{
		int num = 0;
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/geoq_data?user=root&password=root&useSSL=false");
			st = conn.createStatement();	
			ps = conn.prepareStatement("SELECT u.num_jeff_emblems FROM users u WHERE u.username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next())
			{
				num = rs.getInt("num_jeff_emblems");
			}
		}
		
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return num;
	};
	
	
	/*
	 * Updates a user's emblems
	 * Input: user name, number of emblems
	 * Output: (void) updates user's emblems
	 */
	public void update_jeff_embs(String name, int num)
	{
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/geoq_data?user=root&password=root&useSSL=false");
			st = conn.createStatement();	
			ps = conn.prepareStatement("UPDATE `geoq_data`.`users` SET `num_jeff_emblems`=? WHERE `username`=?");
			ps.setInt(1, num);
			ps.setString(2, name);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	};
	
	
	/*
	 * Updates a user's password hashing
	 * Input: user name, new password hashing
	 * Output: (void) updates user's password hashing
	 */
	public void update_pass_hash(String name, String p_hash)
	{
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/geoq_data?user=root&password=root&useSSL=false");
			st = conn.createStatement();	
			ps = conn.prepareStatement("UPDATE `geoq_data`.`users` SET `password_hash`=? WHERE `username`=?");
			ps.setString(1, p_hash);
			ps.setString(2, name);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	};
	
	
	/*
	 * Retrieves an image object with all necessary data
	 * Input: image ID
	 * Output: image object
	 */
	public Image retreive_image_data(int image_ID)
	{
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int image_id = 0;
		double latitude = 0;
		double longitude = 0;
		int heading = 0;
		int pitch = 0;
		String answer_a = "";
		String answer_b = "";
		String answer_c = "";
		String answer_d = "";
		String correct_answer = "";
		
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/geoq_data?user=root&password=root&useSSL=false");
			st = conn.createStatement();	
			ps = conn.prepareStatement("SELECT * FROM image_data WHERE image_id=?");
			ps.setInt(1, image_ID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				image_id = rs.getInt("image_id");
				latitude = rs.getDouble("latitude");
				longitude = rs.getDouble("longitude");
				heading = rs.getInt("heading");
				pitch = rs.getInt("pitch");
				answer_a = rs.getString("answer_a");
				answer_b = rs.getString("answer_b");
				answer_c = rs.getString("answer_c");
				answer_d = rs.getString("answer_d");
				correct_answer = rs.getString("correct_answer");
			}
		}
		
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		
		
		Image image = new Image(image_id, latitude, longitude, heading, pitch,
			answer_a, answer_b, answer_c, answer_d, correct_answer);
		
		return image;
	}
	
	
	/*
	 * Purely for testing purposes
	 */
	public static void main (String[] args) {
		
		Database sample = new Database();
		
		String pass = sample.get_pass("K");
		System.out.println(pass);
		int num = sample.get_num_embs("miller");
		System.out.println(num);
		sample.update_pass_hash("miller", "millhash");
		
		Image my_image = sample.retreive_image_data(1);
		System.out.println(my_image.correct_answer);
		return;
	}
}