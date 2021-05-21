/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;

/**
 *
 * @author camran1234
 */
public class FileHandler {
    
    
    public void read(String path){
        String text;
        File directory = new File(path);
        StringBuffer fileContent = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            //Reading file
            String sCurrentLine;
            while((sCurrentLine=br.readLine())!=null){
                fileContent.append(sCurrentLine).append("\n");
            }
            text = fileContent.toString();
            String codigo = text;
            StringReader reader = new StringReader(codigo);
            SACLexic lexico = new SACLexic(reader);
            parser parser = new parser(lexico);
            parser.parse();
        }catch(Throwable e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
