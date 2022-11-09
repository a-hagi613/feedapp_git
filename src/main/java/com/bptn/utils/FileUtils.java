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

//    public void readFile(Path path) {
//        if (isFileExist(path)) {
//            try {
//                Scanner scanner = new Scanner(new File(path.toUri()));
//                while (scanner.hasNextLine()) {
//                    String data = scanner.nextLine();
//                    LOGGER.info("Data available in file : {}", data);
//                }
//            } catch (FileNotFoundException e) {
//                LOGGER.error("Unable to read the file, cause={}", e.getMessage());
//            }
//        }
//    }
//
//    public void deleteFile(Path path) {
//        if (Files.exists(path)) {
//            try {
//                Files.delete(path);
//                LOGGER.info("File deleted at path={}", path);
//            } catch (IOException e) {
//                LOGGER.error("Unable to delete file at path={}, cause={}", path, e.getMessage());
//            }
//        } else {
//            LOGGER.error("File doesn't exist at Path={}", path);
//        }
//    }
//
//    public void writeFile(Path path) {
//        if (isFileExist(path)) {
//            try {
//                FileWriter writer = new FileWriter(new File(path.toUri()));
//                writer.write("I'm overwriting the file");
//                writer.close();
//                readFile(path);
//            } catch (IOException e) {
//                LOGGER.error("Unable to write the file cause={}", e.getMessage());
//            }
//        }
//    }
//
//    public void updateFile(Path path) {
//        if (ifFileExist(path)) {
//            try {
//                FileWriter writer = new FileWriter(new File(path.toUri()), true);
//                writer.write("\r\n");
//                writer.write("I'm updating the file");
//                writer.close();
//                readFile(path);
//            } catch (IOException e) {
//                LOGGER.error("Unable to update the file cause={}", e.getMessage());
//            }
//        }
//    }


}
