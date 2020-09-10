package com.stmungo.server;

import java.io.*;

//Finds the directory of file based on text input utilising the found name returning the found files directory location
class FileFinder implements Serializable {

	String output;

	public String dirFind(String name, File file) {

		File[] list = file.listFiles();
		if (list != null)
			for (File f : list) {
				if (f.isDirectory()) {
					dirFind(name, f);
				} else if (name.equalsIgnoreCase(f.getName())) {
					output = (f.getParentFile().toString());
				}
			}
		return output;
	}
}