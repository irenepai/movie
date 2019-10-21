package com.cyp.spring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

	DataBase db = new DataBase();
	Connection con;

	@GetMapping("/movie")
	public Object listAll() throws SQLException {
		System.out.println("listAll");
		con = db.getCoonection();
		if(!con.isClosed()) {
			Statement sta = (Statement) con.createStatement();
			ResultSet rs = sta.executeQuery("select * from movie");
			System.out.println("select * from movie");
//			List<Map> movies = new ArrayList();
//			while(rs.next()) {
//				Map<String,Object> aMovie = new HashMap<String,Object>();
//				aMovie.put("id",rs.getInt("id"));
//				aMovie.put("name",rs.getString("name"));
//				aMovie.put("info",rs.getString("info"));
//				aMovie.put("score",rs.getInt("score"));
//				movies.add(aMovie);
//			}
			List<Movie> movies= new ArrayList<Movie>();
			while(rs.next()) {
				Movie aMovie = new Movie(rs.getInt("id"),rs.getString("name"),rs.getString("info"),rs.getInt("score"));
				movies.add(aMovie);
			}
			
			rs.close();
		    sta.close();
		    con.close();
		    System.out.println("return movies");
			return movies;
		}
		System.out.println("error");
		return "error";
	}
	@PostMapping("/movie")
	public Object createOneMovie(
			@RequestParam("id") long id,
			@RequestParam("name") String name,
			@RequestParam("info") String info,
			@RequestParam("score") long score) throws SQLException 
	{
		String sql = "insert into movie (id,name,info,score) "
				+ "values("+ id+",'"+name+"','"+info+"',"+score+")";
		con = db.getCoonection();
		if(!con.isClosed()) {
			Statement sta = (Statement) con.createStatement();
			int ret = sta.executeUpdate(sql);
			
		    sta.close();
		    con.close();
		    System.out.println(sql);
			if(ret == 0) {
				return "Create Failed.";
			}
			else {
				return "Success create " + ret +" record(s).";
			}
		}
		return "error";
	}
	@PutMapping("/movie")
	public Object updateOneMovie(
			@RequestParam("id") long id,
			@RequestParam("name") String name,
			@RequestParam("info") String info,
			@RequestParam("score") long score) throws SQLException 
	{
		String sql = "update movie "
				+ "set name='"+name+"', "
				+ "info='"+info+"', "
				+ "score="+score+" "
				+ "where id="+ id;
		con = db.getCoonection();
		if(!con.isClosed()) {
			Statement sta= (Statement)con.createStatement();
			int ret = sta.executeUpdate(sql);
			sta.close();
			con.close();
			System.out.println(sql);
			if(ret == 0) {
				return "Update Failed.";
			}else {
				return "Update "+ ret + "record(s).";
			}
		}
		return "error";
		
		
	}
	@DeleteMapping("/movie")
	public Object deleteOneMovie(@RequestParam long id) throws SQLException{
		String sql = "delete from movie where id=" + id;
		con = db.getCoonection();
		if(!con.isClosed()) {
			Statement sta = con.createStatement();
			int ret = sta.executeUpdate(sql);
			sta.close();
			con.close();
			System.out.println(sql);
			if(ret == 0) {
				return "Delete Failed";
			}else {
				return "Delete "+ ret +" record(s)";
			}
		}
		return "error";
		
	}

}
