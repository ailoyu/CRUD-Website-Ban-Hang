package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class test {
	public static void name() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MINUTE, 2); // thời gian hiệu lực: 2 phút
		Date thoiGianHieuLucXacThuc1 = new Date(c1.getTimeInMillis()); 
		
	}

	public static void main(String[] args) {
		name();
		
	}
}
