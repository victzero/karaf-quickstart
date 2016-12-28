package me.ezjs.exam.department.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Zjs-yd on 2016/12/28.
 */
@XmlRootElement
public class Department {

    String id;
    String name;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
