package com.stmungo.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.stmungo.client.model.TranslatorChoice;
import com.stmungo.client.model.TranslatorMain;
import com.stmungo.client.model.TranslatorProtocol;
import com.stmungo.client.model.TranslatorRole;
import com.stmungo.client.model.TranslatorScribble;
import com.stmungo.client.model.TranslatorScribbleRole;
import com.stmungo.client.model.TranslatorTypestate;
import com.stmungo.client.model.ValidatorChoice;
import com.stmungo.client.model.ValidatorMain;
import com.stmungo.client.model.ValidatorProtocol;
import com.stmungo.client.model.ValidatorRole;
import com.stmungo.client.model.ValidatorTypestate;
import com.stmungo.client.service.Service;

public class ServiceImp extends RemoteServiceServlet implements Service {
	String globalName;
	String localName;

	@Override
	public TranslatorScribbleRole getTranslatorScribbleRole(String textScr) {
		TranslatorScribbleRole scribbleRole = new TranslatorScribbleRole();

		FileWriter inputFile;
		try {
			inputFile = new FileWriter("scribble.scr");
			inputFile.write(textScr);
			inputFile.flush();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		GlobalNameFinder gnf = new GlobalNameFinder();
		globalName = (gnf.nameFind(textScr));

		RoleNameFinder rnf = new RoleNameFinder();
		String roleName = (rnf.roleFind(textScr));

		if (roleName.contentEquals("ERROR") || globalName.contentEquals("ERROR")) {
			scribbleRole.setText("An error has occured when proccessing, please check your data and try again");
		}

		scribbleRole.setText(roleName);
		return scribbleRole;
	}

	@Override
	public TranslatorScribble getTranslatorScribble(String text) {
		TranslatorScribble scribbleText = new TranslatorScribble();

		ProcessBuilder pb = new ProcessBuilder("cmd", "/c",
				"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/scribble-0.4.3/scribblec.sh",
				"C:/Users/SA276/MastersProject/StMungoApp/war/scribble.scr", "-project " + globalName + " " + text);

		try {
			Process p = pb.start();

			String nameScr = "scribble.scr";
			String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

			FileFinder ff = new FileFinder();
			String loc = (ff.dirFind(nameScr, new File(dir)));

			BufferedReader reader = new BufferedReader(new FileReader(loc + "/" + nameScr));

			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();

			String content = stringBuilder.toString();
			scribbleText.setText(content);

			return scribbleText;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return scribbleText;
	}

	@Override
	public TranslatorMain getTranslatorMain(String text) {

		TranslatorMain mainText = new TranslatorMain();
		TranslatorRole r = new TranslatorRole();

		FileWriter inputFile;
		try {
			inputFile = new FileWriter("input.scr");
			inputFile.write(text);
			inputFile.flush();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/stmungo.jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/input.scr");
			Process p = pb.start();

			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			FileNameFinder fnf = new FileNameFinder();
			localName = (fnf.nameFind(text));
			String fileName = localName;
			if (fileName.contentEquals("ERROR")) {
				mainText.setText("An error has occured when proccessing, please check your data and try again");
			}

			String nameMain = fileName + "Main.java";
			String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

			FileFinder ff = new FileFinder();
			String loc = (ff.dirFind(nameMain, new File(dir)));

			BufferedReader reader = new BufferedReader(new FileReader(loc + "/" + nameMain));

			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();

			String content = stringBuilder.toString();
			mainText.setText(content);

			return mainText;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return mainText;
	}

	@Override
	public ValidatorMain getValidatorMain(String vText) {
		ValidatorMain isValid = new ValidatorMain();

		if (localName == null || vText == null) {
			isValid.setText("Validation Failed");
			return isValid;
		}

		String fileName = localName;
		String nameMain = fileName + "Main.java";
		String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

		FileFinder ff = new FileFinder();
		String loc = (ff.dirFind(nameMain, new File(dir)));

		FileWriter inputFile;
		try {
			inputFile = new FileWriter(loc + "/" + nameMain);
			inputFile.write(vText);
			inputFile.flush();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Process p = Runtime.getRuntime().exec(
					"java -jar C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/mungo.jar C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/mungo.jar\" "
							+ loc + nameMain);
			p.waitFor();

			InputStream is = p.getInputStream();

			byte b[] = new byte[is.available()];
			is.read(b, 0, b.length);
			String s = new String(b);

			isValid.setText(s);
			if (s.equals("")) {
				s = "Validation Successful";
				isValid.setText(s);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			isValid.setText("Validation Failed");
			return isValid;
		}
		return isValid;
	}

	@Override
	public TranslatorRole getTranslatorRole(String text) {
		TranslatorRole roleText = new TranslatorRole();
		try {

			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/stmungo.jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/input.scr");
			Process p = pb.start();

			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			FileNameFinder fnf = new FileNameFinder();
			String fileName = (fnf.nameFind(text));
			if (fileName.contentEquals("ERROR")) {
				roleText.setText(" ");
			}

			String nameRole = fileName + "Role.java";
			String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

			FileFinder ff = new FileFinder();
			String loc = (ff.dirFind(nameRole, new File(dir)));

			BufferedReader reader;
			reader = new BufferedReader(new FileReader(loc + "/" + nameRole));

			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();

			String content = stringBuilder.toString();
			roleText.setText(content);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return roleText;
	}

	@Override
	public ValidatorRole getValidatorRole(String vText) {
		ValidatorRole isValid = new ValidatorRole();

		if (localName == null || vText == null) {
			isValid.setText("Validation Failed");
			return isValid;
		}

		String fileName = localName;
		String nameMain = fileName + "Role.java";
		String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

		FileFinder ff = new FileFinder();
		String loc = (ff.dirFind(nameMain, new File(dir)));

		FileWriter inputFile;
		try {
			inputFile = new FileWriter(loc + "/" + nameMain);
			inputFile.write(vText);
			inputFile.flush();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Process p = Runtime.getRuntime().exec(
					"java -jar C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/mungo.jar C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/mungo.jar\" "
							+ loc + nameMain);
			p.waitFor();

			InputStream is = p.getInputStream();

			byte b[] = new byte[is.available()];
			is.read(b, 0, b.length);
			String s = new String(b);

			isValid.setText(s);
			if (s.equals("")) {
				s = "Validation Successful";
				isValid.setText(s);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			isValid.setText("Validation Failed");
			return isValid;
		}
		return isValid;
	}

	@Override
	public TranslatorProtocol getTranslatorProtocol(String text) {
		TranslatorProtocol protocolText = new TranslatorProtocol();
		try {

			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/stmungo.jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/input.scr");
			Process p = pb.start();

			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			FileNameFinder fnf = new FileNameFinder();
			String fileName = (fnf.nameFind(text));
			if (fileName.contentEquals("ERROR")) {
				protocolText.setText(" ");
			}

			String nameRole = fileName + "Protocol.protocol";
			String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

			FileFinder ff = new FileFinder();
			String loc = (ff.dirFind(nameRole, new File(dir)));

			BufferedReader reader;
			reader = new BufferedReader(new FileReader(loc + "/" + nameRole));

			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();

			String content = stringBuilder.toString();
			protocolText.setText(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return protocolText;
	}

	@Override
	public ValidatorProtocol getValidatorProtocol(String vText) {
		ValidatorProtocol isValid = new ValidatorProtocol();

		if (localName == null || vText == null) {
			isValid.setText("Validation Failed");
			return isValid;
		}

		String fileName = localName;
		String nameMain = fileName + "Protocol.java";
		String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

		FileFinder ff = new FileFinder();
		String loc = (ff.dirFind(nameMain, new File(dir)));

		FileWriter inputFile;
		try {
			inputFile = new FileWriter(loc + "/" + nameMain);
			inputFile.write(vText);
			inputFile.flush();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/mungo.jar", loc + "/" + nameMain);

			Process p = pb.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			String result = builder.toString();
			if (result.equals("")) {
				result = "Validation Successful";
			}
			isValid.setText(result);

		} catch (IOException e) {
			e.printStackTrace();
			isValid.setText("Validation Failed");
			return isValid;
		}
		return isValid;
	}

	@Override
	public TranslatorChoice getTranslatorChoice(String text) {
		TranslatorChoice choiceText = new TranslatorChoice();
		try {

			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/stmungo.jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/input.scr");
			Process p = pb.start();

			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			FileNameFinder fnf = new FileNameFinder();
			String fileName = (fnf.nameFind(text));

			if (fileName.contentEquals("ERROR")) {
				choiceText.setText(" ");
			}

			String nameChoice = fileName + "Choice1.java";

			String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

			FileFinder ff = new FileFinder();
			String loc = (ff.dirFind(nameChoice, new File(dir)));

			BufferedReader reader;
			reader = new BufferedReader(new FileReader(loc + "/" + nameChoice));

			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();

			String content = stringBuilder.toString();
			choiceText.setText(content);

		} catch (IOException e) {
			e.printStackTrace();
		}
		String nameChoice = localName + "Choice1.java";
		String nameChoiceTwo = localName + "Choice2.java";
		String nameChoiceThree = localName + "Choice3.java";
		String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";
		FileFinder ff = new FileFinder();
		String loc = (ff.dirFind(nameChoice, new File(dir)));
		
		if (new File(nameChoiceTwo, loc).exists() == false) {
			return choiceText;
		}
				try {
					BufferedReader reader;
					reader = new BufferedReader(new FileReader(loc + "/" + nameChoiceTwo));

					StringBuilder stringBuilder = new StringBuilder();
					String line = null;
					String ls = System.getProperty("line.separator");

					while ((line = reader.readLine()) != null) {
						stringBuilder.append(line);
						stringBuilder.append(ls);
					}

					stringBuilder.deleteCharAt(stringBuilder.length() - 1);
					reader.close();

					String content = stringBuilder.toString();
					String c = choiceText.getText();
					String concatanated = c.concat(content);
					choiceText.setText(concatanated);

				} catch (IOException e) {
					e.printStackTrace();
				}
				if (new File(nameChoiceThree, loc).exists() == false) {
					return choiceText;
				}	
					try {
					BufferedReader reader;
					reader = new BufferedReader(new FileReader(loc + "/" + nameChoiceThree));

					StringBuilder stringBuilder = new StringBuilder();
					String line = null;
					String ls = System.getProperty("line.separator");

					while ((line = reader.readLine()) != null) {
						stringBuilder.append(line);
						stringBuilder.append(ls);
					}

					stringBuilder.deleteCharAt(stringBuilder.length() - 1);
					reader.close();

					String content = stringBuilder.toString();
					String c = choiceText.getText();
					String concatanated = c.concat(content);
					choiceText.setText(concatanated);

				} catch (IOException e) {
					e.printStackTrace();
				}
		return choiceText;
	}

	@Override
	public ValidatorChoice getValidatorChoice(String vText) {
		ValidatorChoice isValid = new ValidatorChoice();

		if (localName == null || vText == null) {
			isValid.setText("Validation Failed");
			return isValid;
		}

		String fileName = localName;
		String nameMain = fileName + "Choice1.java";
		String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

		FileFinder ff = new FileFinder();
		String loc = (ff.dirFind(nameMain, new File(dir)));

		FileWriter inputFile;
		try {
			inputFile = new FileWriter(loc + "/" + nameMain);
			inputFile.write(vText);
			inputFile.flush();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/mungo.jar", loc + "/" + nameMain);

			Process p = pb.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			String result = builder.toString();
			if (result.equals("")) {
				result = "Validation Successful";
			}
			isValid.setText(result);

		} catch (IOException e) {
			e.printStackTrace();
			isValid.setText("Validation Failed");
			return isValid;
		}
		return isValid;
	}

	@Override
	public TranslatorTypestate getTranslatorTypestate(String text) {
		TranslatorTypestate typestateText = new TranslatorTypestate();
		try {

			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/stmungo.jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/input.scr");
			Process p = pb.start();

			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String nameTypestate = "Typestate.java";
			String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

			FileFinder ff = new FileFinder();
			String loc = (ff.dirFind(nameTypestate, new File(dir)));

			BufferedReader reader;
			reader = new BufferedReader(new FileReader(loc + "/" + nameTypestate));

			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();

			String content = stringBuilder.toString();
			typestateText.setText(content);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return typestateText;
	}

	@Override
	public ValidatorTypestate getValidatorTypestate(String vText) {
		ValidatorTypestate isValid = new ValidatorTypestate();

		if (localName == null || vText == null) {
			isValid.setText("Validation Failed");
			return isValid;
		}

		String fileName = localName;
		String nameMain = fileName + "Typestate.java";
		String dir = "C:/Users/SA276/MastersProject/StMungoApp/war";

		FileFinder ff = new FileFinder();
		String loc = (ff.dirFind(nameMain, new File(dir)));

		FileWriter inputFile;
		try {
			inputFile = new FileWriter(loc + "/" + nameMain);
			inputFile.write(vText);
			inputFile.flush();
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-jar",
					"C:/Users/SA276/MastersProject/StMungoApp/war/WEB-INF/lib/mungo.jar", loc + "/" + nameMain);

			Process p = pb.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));
			}
			String result = builder.toString();
			if (result.equals("")) {
				result = "Validation Successful";
			}
			isValid.setText(result);

		} catch (IOException e) {
			e.printStackTrace();
			isValid.setText("Validation Failed");
			return isValid;
		}
		return isValid;
	}

}
