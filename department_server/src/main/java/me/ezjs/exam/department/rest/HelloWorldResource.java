package me.ezjs.exam.department.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
 
public class HelloWorldResource implements HelloWorldIntf
{
    public Response greet() {
 
       return Response.status(Status.OK).
                 entity("Hi There!!").
                    build();
    }
 
    public Response sayHello(String input) {
       Hello hello = new Hello();
       hello.setHello("Hello");
       hello.setName("Default User");
 
        if(input != null)
           hello.setName(input);
 
       return Response.
                 status(Status.OK).
                   entity(hello).
                     build();
    }
}