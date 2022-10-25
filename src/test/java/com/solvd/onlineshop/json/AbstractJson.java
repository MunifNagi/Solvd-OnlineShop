package com.solvd.onlineshop.json;

import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.JsonMapper;

import java.util.List;

public class AbstractJson {
    protected JsonMapper jsonMapper = new JsonMapper();
    protected final String READ_PATH = "src/main/resources/json/user.json";
    protected final String WRITE_PATH = "src/main/resources/json/new-user.json";
    protected List<User> expectedUserList = List.of(new User(1, "Tom", "Alex", null, "347-200-0000", "tom@email.com", "tom1234"), new User(2, "Maria", "Hernandez", null, "347-222-0001", "maria@email.com", "maria1234"), new User(3, "Marta", "Tsyndra", null, "347-222-1111", "marta@email.com", "marta1234"));

}
