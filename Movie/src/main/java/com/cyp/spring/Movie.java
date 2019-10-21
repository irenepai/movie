package com.cyp.spring;

public class Movie {
	private int id;
	private String name;
	private String info;
	private int score;
	
	
	public Movie(int id, String name, String info, int score) {
		super();
		this.id = id;
		this.name = name;
		this.info = info;
		this.score = score;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

}
