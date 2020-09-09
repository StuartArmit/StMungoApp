package com.stmungo.server;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameFinder implements Serializable{
	

	public String nameFind(String fileString) {
	
	Pattern pattern = Pattern.compile("(?<=local protocol ).*");

	Matcher matcher = pattern.matcher(fileString);

	boolean found = false;
	while (matcher.find()) {
		String fileText = matcher.group().toString();
		found = true;
		
		String checkOne = "(";
		String checkTwo = "_";

		if (fileText.contains(checkOne)) {
			String[] st = fileText.split("");
			int posOne = -1;
			int posTwo = 0;
			int marker = 0;
			for (int i = 0; i <= st.length; i++) {
				posOne++;
				posTwo++;

				if (st[i].equals(checkTwo)) {
					marker = posTwo;

				} else if (st[i].equals(checkOne)) {

					StringBuffer sb = new StringBuffer();
					for (int j = marker; j < posOne; j++) {
						sb.append(st[j]);
					}
					fileString = sb.toString();
					if(fileString == null) {
					fileString = ("ERROR");	
					}
					return fileString;
				}
			}
		}
	}
	if (!found){
	fileString = ("ERROR");
	}
	return fileString;
}
}