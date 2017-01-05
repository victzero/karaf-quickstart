/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.ezjs.exam.department.service2;

import me.ezjs.exam.department.model.Order;

import java.util.Random;

public class DepartmentServiceImpl implements DepartmentService {

    public String echo(String message) {
        return "Echo processed: " + message;
    }

    public String toEndpoint(String in) {
        System.out.println(">>> in method DepartmentServiceImpl.toEndpoint: " + in);
        return "to endpoint: " + in;
    }

    private int counter = 100;
    private Random ran = new Random();

    /**
     * Generates a new order
     */
    public Order generateOrder() {
        System.out.println("...generating order: " + counter);
        Order order = new Order();
        order.setId(counter++);
        order.setItem(counter + " " + ran.nextInt(100));
        order.setAmount(ran.nextInt(100) + 1);
        order.setDescription(counter % 2 == 0 ? "...Camel in Action" : "...ActiveMQ in Action");
        return order;
    }

    public String processOrder(Order order) {
        return ">>>>>< order id " + order.getId() + " item " + order.getItem() + " of " + order.getAmount() + " copies of " + order.getDescription();
    }
}