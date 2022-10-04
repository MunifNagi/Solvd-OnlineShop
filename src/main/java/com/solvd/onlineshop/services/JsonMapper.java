package com.solvd.onlineshop.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonMapper {
   private static final Logger logger = LogManager.getLogger(JsonMapper.class);
   private static ObjectMapper objectMapper = new ObjectMapper();


   public <T> List<T> readJSON(String filePath, Class<T> classRef) {
      try {
         JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, classRef);
         Object entities = objectMapper.readValue(new File(filePath), javaType);
         return (List<T>) entities;
      } catch (JsonProcessingException e) {
         logger.error("Error while trying to Process JsonFile", e);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      return new ArrayList<>();
   }
   public <T> void writeJSON(List<T> entityList, String filePath) {
      try {
         ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
         writer.writeValue(new File(filePath),entityList);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

}
