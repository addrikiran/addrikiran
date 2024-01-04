package com.addri.dao;

public class Query {
		static String valid="SELECT * FROM members WHERE username = ? AND password = ?";
		static String insert="INSERT INTO members (username, email, password) VALUES (?, ?, ?)";
}
