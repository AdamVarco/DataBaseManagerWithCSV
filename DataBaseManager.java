package database;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// class to read and write to a csv file
public class DataBaseManager {
	private final static String filePath = "C:\\Users\\adamv\\Desktop\\database.csv";

	public static ArrayList<String> ReadCSV() {
		ArrayList<String> arList = new ArrayList<String>();
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
				while (stringTokenizer.hasMoreElements()) {
					String username = stringTokenizer.nextElement().toString();
					String password = stringTokenizer.nextElement().toString();
					String email = stringTokenizer.nextElement().toString();
					String salary = stringTokenizer.nextElement().toString();
					arList.add(username + "," + password + "," + email +"," + salary);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return arList;

	}
   public static void PrintCSV(String username, String password, String email, String salary) {
		ArrayList<String> read = ReadCSV();
		if (!read.contains(username) || !read.contains(password) || !read.contains(email))
			read.add(username + "," + password + "," + email + "," + salary);
		StringBuilder stB = new StringBuilder();
		try {
			PrintWriter pw = new PrintWriter(new File(filePath));

			for (String s : read) {
				String[] comma = s.split("[,]");
				stB.append(comma[0]).append(",");
				stB.append(comma[1]).append(",");
				stB.append(comma[2]).append(",");
				stB.append(comma[3]).append('\n');
			}
			pw.write(stB.toString());
			pw.close();
			Desktop desktop = Desktop.getDesktop();
			File file = new File(filePath);
			if (file.exists())
				desktop.open(file);
			else
				throw new FileNotFoundException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void RemoveFromCSV(String username) {
		ArrayList<String> read = ReadCSV();
		 for ( int i = 0;  i < read.size(); i++){
	            String tempName = read.get(i);
	            if(tempName.contains(username))
	                read.remove(i);
	            else
	            	System.out.println("Username is not in the DataBase");
	        }
		 try {
			DeleteFromCSV(read);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	private static void DeleteFromCSV(ArrayList<String> read) throws IOException {
		StringBuilder stB = new StringBuilder();
        PrintWriter pw = new PrintWriter(new File(filePath));
		for(String s : read) {
			String[] comma = s.split("[,]");
			stB.append(comma[0]).append(",");
			stB.append(comma[1]).append(",");
			stB.append(comma[2]).append(",");
			stB.append(comma[3]).append('\n');
		}
		pw.write(stB.toString());
		pw.close();
		Desktop desktop = Desktop.getDesktop();
		File file = new File(filePath);
		if(file.exists()) 
        	desktop.open(file);
		else
			throw new FileNotFoundException();
		}
	
	public static boolean SearchDataBase(String username) {
		ArrayList<String> read = ReadCSV();
		boolean trueF = false;
		for(String s : read) {
			if(s.contains(username)) {
				System.out.println(username + " is in the DataBase");
				trueF = true;
				return true;
			}
			else
              trueF = false;
			}
		   if(trueF!=true)
			   System.out.println(username + " is not in the DataBase");
		   return trueF;
		}
	 
    
	public static void main(String [] args) {
			//PrintCSV("u2", "b3", "k4", "50k");
		SearchDataBase("uuuuu");
	}

}
