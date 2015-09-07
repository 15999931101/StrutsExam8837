package com.hand.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hand.entity.Customer;





public interface CustomerDao {


	public void save (Connection conn,Customer cst) throws SQLException;
	public boolean update(Connection conn, Customer cst) throws SQLException;
	public void delete(Connection conn ,Customer cst) throws SQLException;
	public ResultSet get(Connection conn ) throws SQLException;
}
