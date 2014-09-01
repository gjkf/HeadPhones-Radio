package com.gjkf.headPhones.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.gjkf.headPhones.Main;
import com.gjkf.lib.util.CommonUtils;

public class URLsHandler {

	public BufferedReader reader = null;
	public BufferedWriter writer = null;

	//public static File myModDir = new File(CommonUtils.getMinecraftDir().getName() + "/headPhones");
	public static File linkFolder;
	public static File urlsFile = new File(CommonUtils.getMinecraftDir().getName() + "/headPhones/urlsFile.txt");

	public static ArrayList<String> urls = new ArrayList<String>();

	public URLsHandler(ArrayList<String> urlList){

		urls.add("");
		urls.add("");
		urls.add("");
		urls.add("");
		
		try {
			try {
				writer = new BufferedWriter(new FileWriter(urlsFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			writer.write("");
			
			Main.log.info("Succesfully written into the file");

		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				//Closes the writer

				writer.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		/*
		 * Sets the content of the arrayList of this class like the arrayList that I pass as parameter of the constructor
		 */

		for(int i = 0; i < urlList.size(); i++){
			urls.set(i, urlList.get(i));
		}

		Main.log.info("URLsHandler(urls): " + urls);
		
		

		try {
			reader = new BufferedReader(new FileReader(urlsFile));
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}
	
	public void writeLink(String link){
		
		/*
		 * Instantiate a writer 
		 */

		try {
			try {
				writer = new BufferedWriter(new FileWriter(urlsFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			writer.write(link);
			
			Main.log.info("Succesfully written into the file");

		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				//Closes the writer

				writer.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

	//TODO: Make it properly work
	
	public void deleteLink(String link){

		try{

			int count = 0;

			while(reader.readLine() != null){

				count += 1;

				if(reader.readLine().equalsIgnoreCase(link)){
					writer.write("", count, 0);
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}