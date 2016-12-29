package me.ezjs.exam.department.rest.impl;

import me.ezjs.exam.department.DepartmentService;
import me.ezjs.exam.department.model.Department;
import me.ezjs.exam.department.rest.DepartmentRest;

/**
 * Created by Zjs-yd on 2016/12/28.
 */
public class DepartmentRestImpl implements DepartmentRest {

    DepartmentService departmentService;

    public Department getAll() {
        String name = departmentService.echo("haha");
        Department department = new Department();
        department.setName(name);
        department.setId("1");
        return department;
    }


    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
