package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Util.DButil;

import com.bean.Employee;
import com.bean.Salary;
import com.mysql.cj.protocol.Resultset;

public class DAO {
	Connection con = DButil.getConnection();
	
	@SuppressWarnings("finally")
	public boolean addSalary(Salary s) {
		try {
			System.out.println("Comes in db");
			PreparedStatement ps = con.prepareStatement("insert into salary values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, s.getEmpid());
			ps.setString(2, s.getMonth());
			ps.setString(3, s.getFinancial_year());
			ps.setInt(4, s.getBasic_pay());
			ps.setInt(5, s.getPerformance_pay());
			ps.setInt(6, s.getIncome_tax());
			ps.setInt(7, s.getFood_coupon());
			ps.setInt(8, s.getTotal_salary());
			ps.executeUpdate();
			System.out.println("No errors here");
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
			PreparedStatement ps = con.prepareStatement("select * from employee");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Employee e = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				list.add(e);
				System.out.println(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}

}
