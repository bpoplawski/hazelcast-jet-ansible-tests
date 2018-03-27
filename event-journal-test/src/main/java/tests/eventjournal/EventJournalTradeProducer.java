/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.eventjournal;

import com.hazelcast.core.IMap;

public class EventJournalTradeProducer extends Thread {

    private static final long LOG_PER_TIMESTAMP = 1_000;

    private final int countPerTicker;
    private final IMap<Long, Long> map;
    private volatile boolean running = true;

    public EventJournalTradeProducer(int countPerTicker, IMap<Long, Long> map) {
        this.countPerTicker = countPerTicker;
        this.map = map;
    }

    public void close() throws InterruptedException {
        running = false;
        join();
    }

    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        for (long i = 0; running; i++) {
            for (int j = 0; j < countPerTicker; j++) {
                try {
                    map.set(i, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i % LOG_PER_TIMESTAMP == 0) {
                long elapsed = System.currentTimeMillis() - begin;
                System.out.println("Total time elapsed: " + elapsed + ", timestamp: " + i);
                System.out.println(i / countPerTicker / elapsed);
            }
        }
    }

}