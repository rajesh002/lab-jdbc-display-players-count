package dao;

import model.Skill;
import utility.ConnectionManager;

import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 


public class SkillDAO{
	public TreeMap<String,Integer> skillCount() throws SQLException, Exception{
		int count=0;
		TreeMap<String,Integer> treemap = new TreeMap<String,Integer>();
		ConnectionManager cm=new ConnectionManager();
		Skill skill=null;
		Connection con=cm.getConnection();
		String sql="select name from skill";
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		
		while(rs.next()) {
			skill=new Skill();
		    skill.setSkillName(rs.getString("name"));
		    
		    
		    PreparedStatement statement = con.prepareStatement("SELECT count(*) FROM skill where name = ?");  
		    statement.setString(1, skill.getSkillName());   
		    ResultSet resultSet = statement.executeQuery();
		    
		    while(resultSet.next()) {
		    	count=resultSet.getInt("count(*)");		    	  
		    }
		    treemap.put(skill.getSkillName(),count);
		   
		    }
		return treemap;
	}
}
















