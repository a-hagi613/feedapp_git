package com.bptn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtils {

    public static void main(String[] args) {

//		Calling the first method:
        ifFileExists("C:\\Users\\hagiA\\OneDrive\\Documents\\Coding\\feedapp_git\\src\\main\\java\\test.txt");

//		Calling the second method:
        isFileExistsWithExceptionHandling("C:\\Users\\hagiA\\OneDrive\\Documents\\Coding\\Filesss\\src\\test3.txt");

//		Calling the third method:
        createFileIfNotExist("C:\\Users\\hagiA\\OneDrive\\Documents\\Coding\\Filesss\\src\\test3.txt");


    }


    //	method 1: ifFileExists
    public static boolean ifFileExists(String filePaths) {
        File file = new File(filePaths);
        if (file.exists()) {
            System.out.println("The file exists");
        }
        else {
            System.out.println("The file does not exist");
        }
        return false;
    }

    //	method 2: isFileExistsWithExceptionHandling
    public static void isFileExistsWithExceptionHandling(String filePaths) {
        File file = new File(filePaths);
        try {
            System.out.println("The file exists, here are its contents: ");
            @SuppressWarnings("resource")
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    //	 method 3: createFileIfNotExists
    public static void createFileIfNotExist(String filePath) {
        String filePaths = filePath;
        File file = new File(filePaths);
        if (file.exists()) {
            System.out.println("The file exists");
        }
        else {
            System.out.println("The file does not exist, we will now create this file");
            try {
                FileWriter output = new FileWriter("test4.txt");
                String text = "helllo \\n asfhsjdfb\\nkjhdbsg \\n wehrfbuhfjdsb \\n hfbiuaw \\nsdhf \\nsdhfb \\n end";
                output.write(text);
                System.out.println("File has been succesfully created and text has been inserted");
                output.close();
            } catch (IOException e) {
                System.out.println("File write error...");
                e.printStackTrace();
            }
        }

    }


}
