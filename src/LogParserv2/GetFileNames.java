/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv2;

import java.io.File;

/**
 *
 * @author Ohana
 */
public class GetFileNames {

    public static void main(String[] args) {
        File folder = new File("E:\\LOGS\\OFFICE01\\amadeus\\");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String filename = listOfFiles[i].getName();
                if (filename.contains("root")) {
                    System.out.println(listOfFiles[i].getName());
                }
            }
        }
    }
}
