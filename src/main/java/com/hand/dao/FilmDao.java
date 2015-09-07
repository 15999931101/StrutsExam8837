package com.hand.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.hand.entity.Customer;;



public interface FilmDao {


	public void save (Connection conn,Customer cs) throws SQLException;
	public void update(Connection conn,Customer cs) throws SQLException;
	public void delete(Connection conn ,Customer cs) throws SQLException;
	public ResultSet get(Connection conn ) throws SQLException;
}
