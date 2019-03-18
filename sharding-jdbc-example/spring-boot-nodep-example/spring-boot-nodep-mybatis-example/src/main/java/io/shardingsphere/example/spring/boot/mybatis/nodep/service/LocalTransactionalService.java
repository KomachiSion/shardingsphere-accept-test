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

package io.shardingsphere.example.spring.boot.mybatis.nodep.service;

import io.shardingsphere.example.repository.mybatis.service.SpringPojoService;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LocalTransactionalService {
    
    private final SpringPojoService springPojoService;
    
    @Autowired
    public LocalTransactionalService(final SpringPojoService springPojoService) {
        this.springPojoService = springPojoService;
    }
    
    /**
     * process success, XA transaction will be committed.
     */
    @ShardingTransactionType(TransactionType.LOCAL)
    @Transactional
    public void insertSuccess() {
        springPojoService.insertData();
    }
    
    /**
     * process failure, XA transaction will be rollback.
     */
    @ShardingTransactionType(TransactionType.LOCAL)
    @Transactional
    public void insertFailure() {
        springPojoService.insertData();
        throw new RuntimeException("process failure exception");
    }
}
