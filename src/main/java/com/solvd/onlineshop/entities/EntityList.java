package com.solvd.onlineshop.entities;

import javax.swing.text.html.parser.Entity;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class EntityList<T> {
    @XmlAnyElement(lax = true)
    private List<T> entities = new ArrayList<>();


    public EntityList() {
    }
    public void addEntity(T entity){
        entities.add(entity);
    }

    public EntityList(List<T> entities) {
        this.entities = entities;
    }

    public List<T> getEntities(){
        return entities;
    }
}
