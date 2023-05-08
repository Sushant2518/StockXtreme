package com.masai.utility;

public class IdGenration {
	public static int genrateId() {
		int id = (int) (Math.random() * 10000);
		return id;
	}
}
