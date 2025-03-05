package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void insert(Seller seller) {
		PreparedStatement ps = null;
		try {
			
			ps = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			ps.setDouble(4, seller.getBaseSalary());
			ps.setInt(5, seller.getDepartment().getId());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				
				DB.closeResultSet(rs);
			} else {
				throw new DbException("No rows affected! Something is wrong!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
		}
		
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name ");
			
			rs = ps.executeQuery();
			
			List<Seller> listSeller = new ArrayList<>();
			Map<Integer, Department> mapDepartment = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = mapDepartment.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					mapDepartment.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller sel = instantiateSeller(rs, dep);
				listSeller.add(sel);
			}
			return listSeller;
			
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name ");
			
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();
			
			List<Seller> listSeller = new ArrayList<>();
			Map<Integer, Department> mapDepartment = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = mapDepartment.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					mapDepartment.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller sel = instantiateSeller(rs, dep);
				listSeller.add(sel);
			}
			return listSeller;
			
			
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
	}

}
