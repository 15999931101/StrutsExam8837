package com.hand.dao;

import java.sql.Connection;
import java.util.List;

import net.sf.json.JSONArray;

public interface UserDao {

	public boolean save(Connection connection, String fname, String lname, String emial, int address);
	public boolean check(Connection connection, String name, String pwd);
	public JSONArray all(Connection connection);
	public boolean delete(Connection connection, int id);
	public List<String> address(Connection connection);

}
