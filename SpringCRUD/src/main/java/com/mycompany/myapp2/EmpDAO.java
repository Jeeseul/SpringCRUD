package com.mycompany.myapp2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmpDAO {

    private JdbcTemplate template;
    
    public JdbcTemplate getTemplate() {
		return template;
	}



	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public int save(EmpVO p){
		String sql = "insert into Emp99(name, salary, designation) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";
		return template.update(sql);
	}
	
	public int update(EmpVO p){
		String sql = "update Emp99 set name='"+p.getName()+"', salary="+p.getSalary()+", designation='"+p.getDesignation()+"' where id="+p.getId()+"";
		return template.update(sql);
	}	
	
	public int delete(int id) {
		String sql = "delete from Emp99 where id="+id+"";
		return template.update(sql);
	}
	
	public EmpVO getEmpById(int id) {
		String sql ="select * from Emp99 where id=?";
		
		return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(EmpVO.class));
	}
	
	public List<EmpVO> getList(){
		return template.query("select * from Emp99", new RowMapper<EmpVO>() {
			public EmpVO mapRow(ResultSet rs, int row) throws SQLException{
				EmpVO e = new EmpVO();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setSalary(rs.getFloat(3));
				e.setDesignation(rs.getString(4));
				e.setRegdate(rs.getDate(5));
				return e;
			}
		});
	} 
	
}
