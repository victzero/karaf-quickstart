package me.ezjs.exam.department;

import me.ezjs.exam.department.model.DatabaseBean;
import org.apache.camel.CamelContext;
import org.apache.camel.component.mybatis.MyBatisComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Zjs-yd on 2016/12/29.
 */
public class DepDAOImpl implements DepDAO {

    private static final Logger LOG = LoggerFactory.getLogger(DepDAOImpl.class);
    private CamelContext camelContext;

    public CamelContext getCamelContext() {
        return camelContext;
    }

    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    public String hello(String msg) {
        System.out.println("hello in DAO: " + msg);

        String sql = "create table ORDERS2 (\n"
                + "  ORD_ID integer primary key,\n"
                + "  ITEM varchar(10),\n"
                + "  ITEM_COUNT varchar(5),\n"
                + "  ITEM_DESC varchar(30),\n"
                + "  ORD_DELETED boolean\n"
                + ")";

        LOG.info("Creating table orders2 ...");

        try {
            execute("drop table orders2");
        } catch (Throwable e) {
            // ignore
        }

        try {
            execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.info(e.getMessage());
        }

        LOG.info("... created table orders2");

        return "hello in DAO: " + msg;
    }

    private void execute(String sql) throws SQLException {
        MyBatisComponent component = camelContext.getComponent("mybatis", MyBatisComponent.class);
        Connection con = component.getSqlSessionFactory().getConfiguration().getEnvironment().getDataSource().getConnection();
        con.setAutoCommit(false);
        Statement stm = con.createStatement();
        stm.execute(sql);
        // must commit connection
        con.commit();
        stm.close();
        con.close();
    }
}
