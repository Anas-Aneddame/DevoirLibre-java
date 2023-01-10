package com.example.devoir;

import com.example.devoir.dao.ProductDao;
import com.example.devoir.dao.impl.ProductDaoImpl;
import com.example.devoir.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonParse {


    public static void parseJson() throws IOException {

        File[] files = new File("inputs").listFiles();
        ProductDao dao= new ProductDaoImpl();

        Arrays.sort(files, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));
        // Get the last 5 files
        List<File> lastFiveFiles = new ArrayList<>();
        for (int i = 0; i < 5 && i < files.length; i++) {
            lastFiveFiles.add(files[i]);
        }

        for (File f : lastFiveFiles) {
            System.out.println(f.getName());
        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Product> products = mapper.readValue(f, new TypeReference<List<Product>>(){});
            System.out.println(products);
            Files.move(f.toPath(), Paths.get("done/"+f.getName()));
            for (Product p : products){
                dao.update(p);
            }

        } catch (Exception ex) {
            Files.move(f.toPath(), Paths.get("error/"+f.getName()));
        }}



        }}

