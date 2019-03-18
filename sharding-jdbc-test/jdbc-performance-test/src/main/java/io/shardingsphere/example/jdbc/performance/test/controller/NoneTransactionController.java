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

import io.shardingsphere.example.jdbc.performance.test.service.NoneTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jdbc/autocommit")
public final class NoneTransactionController {
    
    private final NoneTransactionalService noneTransactionalService;
    
    @Autowired
    public NoneTransactionController(final NoneTransactionalService noneTransactionalService) {
        this.noneTransactionalService = noneTransactionalService;
    }
    
    /**
     * XA insert commit.
     * @return string
     */
    @RequestMapping(value = "/insert")
    public String insertCommit() {
        noneTransactionalService.insertSuccess();
        return "commit";
    }
}
