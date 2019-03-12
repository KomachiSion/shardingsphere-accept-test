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

package io.shardingsphere.example.repository.api.trace;

import io.shardingsphere.example.repository.api.service.CommonService;
import io.shardingsphere.example.repository.api.service.CommonServiceImpl;
import io.shardingsphere.example.repository.api.service.TransactionService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultAssertUtils {

    public static void assertShardingDatabaseResult(final CommonService commonService) {
        assertShardingDatabaseResult(commonService, false);
    }
    
    public static void assertShardingDatabaseResult(final CommonService commonService, final boolean isRangeSharding) {
        MemoryLogService memoryLogService = ((CommonServiceImpl) commonService).getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        if (isRangeSharding) {
            assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(2));
        } else {
            assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
        }
    }

    public static void assertShardingTableResult(final CommonService commonService) {
        assertShardingTableResult(commonService, false);
    }
    
    public static void assertShardingTableResult(final CommonService commonService, final boolean isRangeSharding) {
        MemoryLogService memoryLogService = ((CommonServiceImpl) commonService).getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        if (isRangeSharding) {
            assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(5));
        } else {
            assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        }
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        if (isRangeSharding) {
            assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(5));
        } else {
            assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
        }
    }

    public static void assertShardingDatabaseAndTableResult(final CommonService commonService) {
        assertShardingDatabaseAndTableResult(commonService, false);
    }

    public static void assertShardingDatabaseAndTableResult(final CommonService commonService, final boolean isRangeSharding) {
        MemoryLogService memoryLogService = ((CommonServiceImpl) commonService).getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        if (isRangeSharding) {
            assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(5));
        } else {
            assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(10));
        }
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        if (isRangeSharding) {
            assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(2));
        } else {
            assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(10));
        }
    }

    public static void assertMasterSlaveResult(final CommonService commonService) {
        MemoryLogService memoryLogService = ((CommonServiceImpl) commonService).getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(0));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(10));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(0));
    }
    
    public static void assertTransactionServiceResult(final TransactionService transactionService) {
        MemoryLogService memoryLogService = transactionService.getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(60));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(30));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(60));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(30));
    }
    
    public static void assertTransactionMasterSlaveResult(final TransactionService transactionService) {
        MemoryLogService memoryLogService = ((CommonServiceImpl) transactionService).getMemoryLogService();
        assertThat(memoryLogService.getOrderData(DatabaseAccess.INSERT).size(), is(60));
        assertThat(memoryLogService.getOrderData(DatabaseAccess.SELECT).size(), is(30));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.INSERT).size(), is(60));
        assertThat(memoryLogService.getOrderItemData(DatabaseAccess.SELECT).size(), is(30));
    }
}
