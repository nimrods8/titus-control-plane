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

package com.netflix.titus.master.eviction.service.quota;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import com.netflix.titus.api.jobmanager.model.job.disruptionbudget.TimeWindow;
import com.netflix.titus.api.jobmanager.model.job.disruptionbudget.TimeWindowFunctions;
import com.netflix.titus.common.runtime.TitusRuntime;

/**
 * Time window quota oscillates between two states. No quota, if an instant is outside of the configured time
 * windows, or unlimited quota if it is within the configured time windows.
 */
public class TimeWindowQuotaTracker implements QuotaTracker {

    private final Supplier<Boolean> predicate;

    public TimeWindowQuotaTracker(List<TimeWindow> timeWindows, TitusRuntime titusRuntime) {
        this.predicate = TimeWindowFunctions.isInTimeWindowPredicate(titusRuntime, timeWindows);
    }

    @Override
    public long getQuota() {
        return predicate.get() ? Long.MAX_VALUE / 2 : 0;
    }

    @Override
    public Optional<String> explainRestrictions(String taskId) {
        return predicate.get()
                ? Optional.empty()
                : Optional.of("outside time window");
    }
}
