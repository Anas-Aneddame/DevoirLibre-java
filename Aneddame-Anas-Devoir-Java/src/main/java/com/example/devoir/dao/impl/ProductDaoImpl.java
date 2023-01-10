package com.example.devoir.dao.impl;

import com.example.devoir.dao.ConnexionDB;
import com.example.devoir.dao.ProductDao;
import com.example.devoir.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    public void update(Product p){

        ConnexionDB con = new ConnexionDB();
        Statement statement;

        try{
            statement = con.getCon().createStatement();
            String req="select * from product where id="+p.getId();
            ResultSet rs = statement.executeQuery(req);
            if(rs.next()){
                String req1= "update product set prix="+p.getPrix()+",dateModification='"+LocalDateTime.now() +"'where id="+p.getId();
                statement.executeUpdate(req1);
            }else{
                String req1= "insert into product(id,nom,prix) values('"+p.getId()+"','"+p.getNom()+"',"+p.getPrix()+")";
                statement.executeUpdate(req1);
            }
            con.getCon().close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public List<Product> findAll(){

        List<Product> products= new ArrayList<>();

        ConnexionDB con= new ConnexionDB();

        Statement st;
        try{

            st =con.getCon().createStatement();
            String req="select * from product ";
            ResultSet rs= st.executeQuery(req);
            while(rs.next()){

                Product p = new Product(rs.getLong("id"),rs.getString("nom"),rs.getDouble("prix"));
                products.add(p);

            }
            con.getCon().close();
            return products;

        }catch(Exception e ){
            e.printStackTrace();
            return null;
        }}

}
