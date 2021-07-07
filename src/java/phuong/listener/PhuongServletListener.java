/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class PhuongServletListener implements ServletContextListener {

    public static void setup(ServletContextEvent sce)
            throws IOException, FileNotFoundException{
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        Map<String, String> map = null;
        FileReader file = null;
        BufferedReader bf = null;
        try {
            file = new FileReader(realPath + "/WEB-INF/PhuongServletListener.txt");
            bf = new BufferedReader(file);
            String line = "";
            while ((line = bf.readLine()) != null) {
                if (line.contains("=")) {
                    String[] arrays = line.split("=");

                    if (map == null) {
                        map = new HashMap<>();
                    }
                    map.put(arrays[0], arrays[1]);
                }
            }
            context.setAttribute("MAP", map);
        } finally {
            if (bf != null){
                bf.close();
            }
            if (file != null){
                file.close();
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        try {
            setup(sce);
        } catch (IOException ex) {
            context.log("PhuongServletListener _IOException" + ex.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
