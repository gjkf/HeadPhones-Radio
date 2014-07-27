package com.gjkf.headPhones.handler;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.gjkf.headPhones.Main;
import com.gjkf.headPhones.utility.LogHelper;

public class LinkHandler {

	public static File linksFolder;
	public static HashMap<String, ArrayList<String>> categories = new HashMap<String, ArrayList<String>>();
	private static HashMap<File, String> linkNames = new HashMap<File, String>();
	public static boolean reloadingLinks;
	
	public static void deleteLink(String linkName, boolean disable){
        deleteLink(linksFolder, linkName, disable);
    }

    public static void deleteLink(File dir, String linkName, boolean disable){
        File[] files = dir.listFiles();
        for(File file : files){
            if(!file.isDirectory() && file.getName().equalsIgnoreCase(linkName + ".tc2")){
                if(disable){
                    File disabledDir = new File(dir, "/Disabled");
                    if(!disabledDir.exists()){
                        disabledDir.mkdirs();
                    }
                    File disabledName = new File(disabledDir, linkName + ".tc2");
                    if(disabledName.exists()){
                        disabledName.delete();
                    }
                    if(!file.renameTo(disabledName)){
                       LogHelper.error("Failed to disable link: " + file.getName());
                    }
                }
                else{
                    if(!file.delete()){
                    	LogHelper.error("Failed to delete link: " + file.getName());
                    }
                }
                break;
            }
        }
        files = dir.listFiles();
        for(File file : files){
            if(file.isDirectory() && !file.getName().equalsIgnoreCase("Disabled")){
                deleteLink(file, linkName, disable);
            }
        }
    }
    
    public static boolean isInFavourites(String linkName){
        return isInCategory(linkName, "Favourites");
    }

    public static boolean isInCategory(String linkName, String category){
        if(category.equalsIgnoreCase("Contributors")){
            return false;
        }
        ArrayList<String> favs = categories.get(category);
        if(favs != null){
            for(String s : favs){
                if(s.equalsIgnoreCase(linkName)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static ArrayList<String> getAllLinkNamesAsList(){
        ArrayList<String> linkNameList = new ArrayList<String>();

        Iterator<Entry<File, String>> ite = LinkHandler.getLinkNames().entrySet().iterator();
        while(ite.hasNext()){
            Entry<File, String> e = ite.next();
            String name = e.getKey().getName().substring(0, e.getKey().getName().length() - 4);
            linkNameList.add(name);
        }
        Collections.sort(linkNameList);

        return linkNameList;
    }
    
    public static String[] getAllLinkAsArray(){
        ArrayList<String> linkNameList = getAllLinkNamesAsList();

        String[] linkNameArray = new String[linkNameList.size()];

        linkNameList.toArray(linkNameArray);

        return linkNameArray;
    }
    
    public static HashMap<File, String> getLinkNames(){
        if(reloadingLinks){
            return new HashMap<File, String>();
        }
        return linkNames;
    }
    
    public static ArrayList<String> getAllLinks(){
        ArrayList<String> linkList = new ArrayList<String>();

        Iterator<Entry<File, String>> ite = LinkHandler.getLinkNames().entrySet().iterator();
        while(ite.hasNext()){
            Entry<File, String> e = ite.next();
            linkList.add(e.getValue());
        }
        return linkList;
    }
	
    public static String getLinkStartingWith(String name){
        for(Entry<File, String> e : getLinkNames().entrySet()){
            if(e.getValue().toLowerCase().startsWith(name.toLowerCase())){
                return e.getValue();
            }
        }
        return name;
    }
    
    public static void removeFromCategory(String linkName, String category){
        ArrayList<String> favs = categories.get(category);
        if(favs != null){
            for(int i = favs.size() - 1; i >= 0; i--){
            	String fav = favs.get(i);
                if(fav.equalsIgnoreCase(linkName)){
                    File favFile = new File(linksFolder, "/" + category +"/" + linkName + ".tc2");
                    File linkFile = new File(linksFolder, linkName + ".tc2");
                    if(!linkFile.exists()){
                        favFile.renameTo(linkFile);
                    }else{
                        favFile.delete();
                    }
                    favs.remove(i);
                    break;
                }
            }
        }
    }
    
}