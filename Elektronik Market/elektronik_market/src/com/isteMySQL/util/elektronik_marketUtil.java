package com.isteMySQL.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class elektronik_marketUtil {
static Connection con=null;
	
	public static Connection Baglan() {
		try {
			//"jdbc:mysql://ServerIPAdresi/db_ismi", "kullanici", "þifre"
			con=DriverManager.getConnection("jdbc:mysql://localhost/elektronik", "root", "mysql");
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
}
