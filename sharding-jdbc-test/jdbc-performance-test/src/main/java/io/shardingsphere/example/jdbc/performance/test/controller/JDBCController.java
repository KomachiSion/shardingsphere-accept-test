/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.shardingsphere.example.jdbc.performance.test.controller;

import io.shardingsphere.example.repository.mybatis.service.SpringPojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping(value = "/jdbc")
public final class JDBCController {
    
    private final SpringPojoService springPojoService;
    
    @Autowired
    private DataSource shardingDataSource;
    
    @Autowired
    public JDBCController(final SpringPojoService springPojoService) {
        this.springPojoService = springPojoService;
    }
    
    @RequestMapping(value = "/init")
    public String init() {
        springPojoService.initEnvironment();
        return "ok";
    }
    
    @RequestMapping(value = "/print")
    public String print() {
        springPojoService.printData();
        return "ok";
    }
}
