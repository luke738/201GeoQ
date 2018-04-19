package backend;

import shared.GameSettings;
import shared.Question;
import websockets.Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {
	
	public String username;
	public String pass_hash;
	public int jeff_embs;
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	/*
	 * Constructor initializes connections
	 */
	public Database()
	{
		conn = null;
		st = null;
		ps = null;
		rs = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/geoq_data?user=root&password=root&useSSL=false");
			st = conn.createStatement();
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} 
		catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		}
	};
	
	
	/*Creates a new registered user
	 * Input: user name, hashed password, salt value
	 * Output: (void) adds user with 0 emblems
	 */
	public void create_user(String user, String hashing, String salting)
	{
		try 
		{
			ps = conn.prepareStatement("INSERT INTO `geoq_data`.`users` (`username`, `password_hash`, `password_salt`, `num_jeff_emblems`) VALUES (?, ?, ?, '0')");
			ps.setString(1, user);
			ps.setString(2, hashing);
			ps.setString(3, salting);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} 
	};
	
	
	/* Checks whether a user name is 
	 * already in the system
	 * Input: user name
	 * Output: true if exists, 0 otherwise
	 */
	public Boolean verify_user(String name)
	{
		try
		{
			ps = conn.prepareStatement("SELECT u.username FROM users u WHERE u.username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next())
			{
				String data_name = rs.getString("username");
				return data_name.equals(name);
			}
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		return false;
	};
	
	
	/*
	 * Input: user name
	 * Output: password hashing associated
	 * with the user name in database
	 */
	public ArrayList<String> get_password_info(String name)
	{
		String hash = "";
		String salt = "";
		ArrayList<String> pass_info = new ArrayList<String>();
		try 
		{
			ps = conn.prepareStatement("SELECT u.password_hash, u.password_salt FROM users u WHERE u.username=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next())
			{
				hash = rs.getString("password_hash");
				salt = rs.getString("password_salt");
			}
		}
		
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} 
		
		pass_info.add(hash);
		pass_info.add(salt);
		return pass_info;	
	};
	
	
	/*Input: user name
	 * Output: number of jeffrey miller emblems
	 */
	public int get_num_embs(String name)
	{
		int num = 0;
		try 
		{
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
		try 
		{
			ps = conn.prepareStatement("UPDATE `geoq_data`.`users` SET `num_jeff_emblems`=? WHERE `username`=?");
			ps.setInt(1, num);
			ps.setString(2, name);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} 
	};
	
	
	/*
	 * Updates a user's password hashing
	 * Input: user name, new password hashing
	 * Output: (void) updates user's password hashing
	 */
	public void update_password_info(String name, String p_hash, String p_salt)
	{
		try 
		{
			ps = conn.prepareStatement("UPDATE `geoq_data`.`users` SET `password_hash`=?, `password_salt`=? WHERE `username`=?");
			ps.setString(1, p_hash);
			ps.setString(2, p_salt);
			ps.setString(3, name);
			ps.executeUpdate();
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} 
			
	};
	
	
	/*
	 * Retrieves an image object with all necessary data
	 * Input: image ID
	 * Output: image object
	 */
	public Question retreive_image_data(int image_ID)
	{
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
		}

		String[] answers = {answer_a, answer_b, answer_c, answer_d};
		int correctAnswer = 0;
		for(int i = 0; i < answers.length; i++)
		{
			if(correct_answer.equals(answers[i])) correctAnswer = i;
		}
		return new Question(latitude, longitude, heading, pitch, answers, correctAnswer);
	}
	
	/*
	 * Retrieves an image object with all necessary data
	 * Input: image ID
	 * Output: image object
	 */
	public GameSettings retreive_settings()
	{
		int start_time = 0;
		int num_questions = 0;
		int game_interval_time = 0;
		int question_time = 0;
		int leaderboard_time = 0;
		
		try 
		{
			ps = conn.prepareStatement("SELECT * FROM management");
			rs = ps.executeQuery();
			while(rs.next())
			{
				start_time = rs.getInt("start_time");
				num_questions = rs.getInt("num_questions");
				game_interval_time = rs.getInt("game_interval_time");
				question_time = rs.getInt("question_time");
				leaderboard_time = rs.getInt("leaderboard_time");
			}
		}
		catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		return new GameSettings(LocalDateTime.ofEpochSecond(start_time, 0, ZoneOffset.ofHours(-7)), game_interval_time, num_questions, question_time, leaderboard_time);
	}

	public void update_settings(GameSettings settings)
    {
        try
        {
            ps = conn.prepareStatement("UPDATE `geoq_data`.`management` SET `start_time`=?, `num_questions`=?, `game_interval_time`=?, `question_time`=?, `leaderboard_time`=? WHERE `primarykey`=?");
            ps.setInt(1, (int)settings.startTime.toEpochSecond(ZoneOffset.ofHours(-7)));
            ps.setInt(2, settings.numQuestions);
            ps.setInt(3, settings.timeBetweenGames);
            ps.setInt(4, settings.questionTime);
            ps.setInt(5, settings.leaderboardTime);
            ps.setInt(6, 1);
            ps.executeUpdate();
        }
        catch (SQLException sqle) {
            System.out.println ("SQLException: " + sqle.getMessage());
        }
    }
	
	public void close_connections()
	{
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
	};
	
	/*
	 * Purely for testing purposes
	 */
	public static void main (String[] args) {
		
		Database sample = new Database();
		/*Testing create_user*/
//		sample.create_user("Kiran", "kieranhash", "kieransalt");
//		
//		/*Testing verify_user*/
//		Boolean my_bool = sample.verify_user("Kiran");
//		if(my_bool)
//		{
//			System.out.println("true, it worked");
//		}
//		else
//		{
//			System.out.println("false, failed");
//		}
//		my_bool = sample.verify_user("Luke");
//		if(my_bool)
//		{
//			System.out.println("true, failed");
//		}
//		else
//		{
//			System.out.println("false, it worked");
//		}
//		
//		
//		/*Testing get_password_info*/
//		ArrayList<String> pass = sample.get_password_info("Kiran");
//		System.out.println(pass.get(0));
//		System.out.println(pass.get(1));
//		
//		//*Testing get_num_emblems*/
//		int num = sample.get_num_embs("Kiran");
//		System.out.println(num);
//		
//		/*Testing update_jeff_embs*/
//		sample.update_jeff_embs("Kiran", 2);
//		num = sample.get_num_embs("Kiran");
//		System.out.println(num);
//		
//		/*Testing update_password_info*/
//		sample.update_password_info("Kiran", "millhash", "myhash");
//		
//		/*Testing image retrieval*/
//		Question my_image = sample.retreive_image_data(2);
//		System.out.println(my_image.correctAnswerString);
		GameSettings gs = sample.retreive_settings();
		
		sample.close_connections();
		return;
	}
}