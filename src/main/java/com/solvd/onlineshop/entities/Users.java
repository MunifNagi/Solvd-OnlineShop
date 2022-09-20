package com.solvd.onlineshop.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Users")
public class Users {
    @XmlElement(name = "User", type = User.class)
    private List<User> users = new ArrayList<User>();

    public Users() {}

    public Users(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setBooks(List<User> users) {
        this.users = users;
    }
}
