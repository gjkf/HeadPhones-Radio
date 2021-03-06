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

	public static File linkFolder;
	public static File urlsFile = new File(CommonUtils.getMinecraftDir().getName() + "/headPhones/urlsFile.txt");

	public static ArrayList<String> urls = new ArrayList<String>();

	public URLsHandler(){
		Main.log.info("URLsHandler(urls): " + urls);
	}

	public void initReader(){

		try{
			reader = new BufferedReader(new FileReader(urlsFile));
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}

	public void initWriter(){

		try{
			writer = new BufferedWriter(new FileWriter(urlsFile));
		}catch(IOException e) {
			e.printStackTrace();
		}

		//	Main.log.info("Successfully initialized writer");
	}

	public void closeReader(){

		try{
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void closeWriter(){
		try{
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		//	Main.log.info("Successfully closed the writer");
	}

	public void writeLink(String link){

		try {
			initWriter();

			writer.write(link + "\n");

			Main.log.info("Succesfully written into the file: " + link);

		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			closeWriter();
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

	public String readAll(){

		try{
			initReader();

			while(reader.readLine() != null){
				return reader.readLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			closeReader();
		}

		return null;

	}

}