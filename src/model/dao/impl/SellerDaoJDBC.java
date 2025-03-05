package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller depart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller depart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller sel = instantiateSeller(rs, dep);
				
				return sel;
			}
			return null;
			
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
		
	}
	
	// Criação de Instancias para evitar escrever vários linhas de códigos de forma repetida
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException { 
		Seller sell = new Seller(); 
		sell.setId(rs.getInt("Id")); 
		sell.setName(rs.getString("Name")); 
		sell.setEmail(rs.getString("Email")); 
		sell.setBaseSalary(rs.getDouble("BaseSalary")); 
		sell.setBirthDate(rs.getDate("BirthDate")); 
		sell.setDepartment(dep); 
		return sell; 
	} 
	private Department instantiateDepartment(ResultSet rs) throws SQLException { 
		Department dep = new Department(); 
		dep.setId(rs.getInt("DepartmentId")); 
		dep.setName(rs.getString("DepName")); 
		return dep; 
	} 


	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
