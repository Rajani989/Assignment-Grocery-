package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Grocery;

@Service
public class GroceryServices {
	@Autowired
	JdbcTemplate template;
	@PostConstruct
	public void executeMe()
	{
		/*-----------Create Operation-----------*/
		  template.execute("Create Table Grocery(Id int Primary key,Name Varchar(30),Price int,Quantity int)");  
		
			
		/*-----------Insert Operation using objects-----------*/
		  Grocery g1=new Grocery(1,"Apple",80,10);
		  Grocery g2=new Grocery(2,"Grapes",70,10);
		  
		  //Insert value in list
		  List<Grocery> grocery=new ArrayList<>();
		  
		  grocery.add(g1);
		  grocery.add(g2);
		  
		  grocery.forEach(A->template.update("Insert into grocery values(?,?,?,?)" ,A.getId(),A.getName(),A.getPrice(),A.getQuantity()));  
		
		 
	    /*-----------Insert Operation-----------*/
		 template.update("Insert into grocery values(3,'Banana',50,15)");

		
	   /*---------------Update Operation----------*/
		grocery.forEach(x->template.update("Update grocery set Quantity=20 where Id=1"));

	  /*---------------Delete Operation----------*/
		template.update("Delete from grocery where Id=1 ");
		 
	  /*---------------Select Operation----------*/ 
	   grocery=template.query("select * from grocery",(rs,noofrows) ->new Grocery(rs.getInt("Id"),rs.getString("Name"),rs.getInt("Price"),rs.getInt("Quantity")));
	   grocery.forEach(l->System.out.println(l));
	}
}
