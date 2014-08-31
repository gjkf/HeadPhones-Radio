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

	public File myModDir = new File(CommonUtils.getModsFolder().getName() + "/headPhones");
	public File urlFile = new File(CommonUtils.getModsFolder().getName() + "/headPhones/urlList.txt");

	public static ArrayList<String> urls = new ArrayList<String>();

	public URLsHandler(ArrayList<String> urlList){

		try{
			if(!myModDir.exists()){
				if(myModDir.mkdir()){
					Main.log.info("Successfully created directory");
				}else{
					Main.log.info("Failed to create directory");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		/*
		 * Sets the content of the arrayList of this class like the arrayList that I pass as parameter of the constructor
		 */

		for(int i=0; i<urlList.size(); i++){
			urls.set(i, urlList.get(i));
		}

		/*
		 * Instantiate a writer 
		 */

		try {
			try {
				writer = new BufferedWriter(new FileWriter(urlFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			/*
			 * This will write all the links of the ArrayList into the file
			 */

			for(int i=0; i<urls.size(); i++){
				writer.write(urls.get(i) + "\n");
			}

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

		try {
			reader = new BufferedReader(new FileReader(urlFile));
		}catch (FileNotFoundException e){
			e.printStackTrace();
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