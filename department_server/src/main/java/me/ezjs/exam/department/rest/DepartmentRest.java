package me.ezjs.exam.department.rest;

import me.ezjs.exam.department.model.Department;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Zjs-yd on 2016/12/28.
 */
@Path("/")
@Produces({MediaType.APPLICATION_JSON})
public interface DepartmentRest {

    @GET
    @Path("/")
    public Department getAll();
}
