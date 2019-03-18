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

package io.shardingsphere.example.spring.boot.mybatis.nodep.controller;

import io.shardingsphere.example.spring.boot.mybatis.nodep.service.XATransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jdbc/xa")
public final class XATransactionController {
    
    private final XATransactionalService xaTransactionalService;
    
    @Autowired
    public XATransactionController(final XATransactionalService xaTransactionalService) {
        this.xaTransactionalService = xaTransactionalService;
    }
    
    /**
     * XA insert commit.
     * @return string
     */
    @RequestMapping(value = "/insert/commit")
    public String insertCommit() {
        xaTransactionalService.insertSuccess();
        return "commit";
    }
    
    /**
     * XA insert rollback.
     * @return string
     */
    @RequestMapping(value = "/insert/rollback")
    public String insertRollback() {
        try {
            xaTransactionalService.insertFailure();
        } catch (final Exception ignore) {
        
        }
        return "rollback";
    }
}
