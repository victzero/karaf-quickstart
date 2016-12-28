package me.ezjs.exam.department.rest.impl;

import me.ezjs.exam.department.model.Department;
import me.ezjs.exam.department.rest.DepartmentRest;

/**
 * Created by Zjs-yd on 2016/12/28.
 */
public class DepartmentRestImpl implements DepartmentRest {

    public Department getAll() {
        Department department = new Department();
        department.setName("test");
        department.setId("1");
        return department;
    }

}
