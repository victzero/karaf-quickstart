package me.ezjs.exam.department;

import me.ezjs.exam.department.rest.impl.DepartmentRestImpl;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;

import java.io.IOException;

/**
 * Created by Zjs-yd on 2016/12/28.
 */
public class RestStarter {


    @SuppressWarnings("rawtypes")
    public void startRestService() {
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setAddress("http://localhost:8282/department");
        factory.setResourceClasses(DepartmentRestImpl.class);
        factory.setResourceProvider(new SingletonResourceProvider(new DepartmentRestImpl()));
        factory.setProvider(new JAXBElementProvider());
//        factory.setProvider(new org.apache.cxf.jaxrs.provider.json.JSONProvider());
        Server server = factory.create();
        server.start();
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new RestStarter().startRestService();
        System.in.read();
    }

}
