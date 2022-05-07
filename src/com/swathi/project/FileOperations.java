package com.swathi.project;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileOperations {
	public static void main(String args[]) {
		int a;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter root directory \n" );
		String rootDir = "";
		File root = null;
		boolean rootExists = false;
		int maxRetry=0;
		// System.out.println("************************************");
		do {
			rootDir = sc.nextLine();
			root = new File(rootDir);
			rootExists = root.exists();
			if(!rootExists) {
				maxRetry++;
				if(maxRetry==3) {
					System.out.println("Invalid path. Maximum attempts reached. Closing the application.");
					sc.close();
					return;
				}
				System.out.println("Invalid path. Please try again");
			}

		} while (!rootExists);
		
		//new FileOperations().displayMenuOptions();
		boolean taskComplete=false;
		int taskRetry=0;
		do {
		displayMainMenuOptions();
		a = Integer.parseInt(sc.nextLine());
		if (a == 1) {
			String[] fileList = root.list();
			for (int i = 0; i < fileList.length; i++)
				System.out.println(fileList[i]);
			//displayMainMenuOptions();
			//a = Integer.parseInt(sc.nextLine());
		} 
		else if (a == 2) {
			boolean subTaskComplete=false;
			int subTaskRetry=0;
			do {	
			displayMenu2Options();
			a = Integer.parseInt(sc.nextLine());
			
			if(a==4) {
				System.out.println("Enter file name");
				String fileName= sc.nextLine();
				System.out.println("Enter file content");
				String fileContent=sc.nextLine();
				File f=new File(rootDir+"\\"+fileName+".txt");
				try {
					if(f.createNewFile())
						System.out.println("File is created");
					FileWriter fw=new FileWriter(f);
					fw.write(fileContent);
					fw.close();
				}
				catch(Exception ex) {
					
				}
				subTaskComplete=true;
				
			}
			else if(a==5) {
				System.out.println("Enter file name to delete");
				String fileName= sc.nextLine();
				File f=new File(rootDir+"\\"+fileName+".txt");
				try {
					if(f.delete())
						System.out.println("File is deleted");
					
				}
				catch(Exception ex) {
					
				}
				subTaskComplete=true;
			}
			else if(a==6) {
				System.out.println("Enter file name to Search");
				String fileName= sc.nextLine();
				File f=new File(rootDir+"\\"+fileName+".txt");
				try {
					if(f.exists())
						System.out.println("File exists");
					
				}
				catch(Exception ex) {
					
				}
				try
				{
					List<String> data=Files.readAllLines(Paths.get(rootDir+"\\"+fileName+".txt"));
					
					for(var v:data)
						System.out.println(v);
				}
				catch(Exception ex)
				{
					
				}
				subTaskComplete=true;
			}
			else if(a==7) {
				displayMainMenuOptions();
				subTaskComplete=true;
			}
			else {
				subTaskRetry++;
				if(subTaskRetry==3) {
					System.out.println("Maximum attempts reached. Going back to main menu.");
					subTaskComplete=true;
				}
				else {
				System.out.println("Choose correct option");
				//displayMenu2Options();
				}
			}
		}while(!subTaskComplete);

		} else if (a == 3) {
			System.out.println("Closing the application.");
			sc.close();
			return;
		} else {
			taskRetry++;
			if(taskRetry==3) {
				System.out.println("Maximum attempts reached. Closing the application.");
				taskComplete=true;
				sc.close();
				return;
			}
			else {
			System.out.println("Choose correct option");
			//displayMainMenuOptions();
			}

		}
		}while(!taskComplete);
	}
	static void displayMainMenuOptions() {
		System.out.println("Select option to proceed: ");
		System.out.println("Option 1: Current Directory Content");
		System.out.println("Option 2: Add/Delete/Search Files");
		System.out.println("Option 3: Close Application");
	}
	static void displayMenu2Options() {
		System.out.println("Select option to proceed: ");
		System.out.println("Option 4: Add File");
		System.out.println("Option 5: Delete File");
		System.out.println("Option 6: Search File");
		System.out.println("Option 7: Go to welcome page");
	}

}
