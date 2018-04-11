/*
 * Copyright 2018 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.titus.api.model.v2;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkerAssignments {

    private int stage;
    private int numWorkers;
    private Map<Integer, WorkerHost> hosts; // lookup by workerNumber

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public WorkerAssignments(@JsonProperty("stage") Integer stage,
                             @JsonProperty("numWorkers") Integer numWorkers,
                             @JsonProperty("hosts") Map<Integer, WorkerHost> hosts) {
        this.stage = stage;
        this.numWorkers = numWorkers;
        this.hosts = hosts;
    }

    public int getStage() {
        return stage;
    }

    public int getNumWorkers() {
        return numWorkers;
    }

    public void setNumWorkers(int numWorkers) {
        this.numWorkers = numWorkers;
    }

    public Map<Integer, WorkerHost> getHosts() {
        return hosts;
    }

    @Override
    public String toString() {
        return "WorkerAssignments [stage=" + stage + ", numWorkers=" + numWorkers + ", hosts=" + hosts + "]";
    }
}