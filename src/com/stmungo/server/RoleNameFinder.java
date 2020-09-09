package com.stmungo.server;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoleNameFinder implements Serializable{
	

	public String roleFind(String roleString) {
	
	Pattern pattern = Pattern.compile("(?<=role ).*");

	Matcher matcher = pattern.matcher(roleString);

	boolean found = false;
	while (matcher.find()) {
		roleString = matcher.group().toString();
		found = true;
		
		roleString = roleString.replace("(", " ");
		roleString = roleString.replace(",", "");
		roleString = roleString.replace("role ", "");
		roleString = roleString.replace(")", "");
		roleString = roleString.replace(" {", "");
		roleString = roleString.replace("{", "");
		
	if (!found)
	roleString = ("ERROR");
	return roleString;

}
	return roleString;
}
}