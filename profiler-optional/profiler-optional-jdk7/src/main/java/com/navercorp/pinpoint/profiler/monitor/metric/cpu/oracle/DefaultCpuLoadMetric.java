/*
 * Copyright 2017 NAVER Corp.
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

package com.navercorp.pinpoint.profiler.monitor.metric.cpu.oracle;

import com.navercorp.pinpoint.profiler.monitor.metric.cpu.CpuLoadMetric;
import com.navercorp.pinpoint.profiler.monitor.metric.cpu.CpuLoadMetricSnapshot;

import java.lang.management.OperatingSystemMXBean;

/**
 * @author HyunGil Jeong
 */
public class DefaultCpuLoadMetric implements CpuLoadMetric {

    private final com.sun.management.OperatingSystemMXBean operatingSystemMXBean;

    public DefaultCpuLoadMetric(OperatingSystemMXBean operatingSystemMXBean) {
        if (operatingSystemMXBean == null) {
            throw new NullPointerException("operatingSystemMXBean must not be null");
        }
        this.operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) operatingSystemMXBean;
    }

    @Override
    public CpuLoadMetricSnapshot getSnapshot() {
        double jvmCpuUsage = operatingSystemMXBean.getProcessCpuLoad();
        double systemCpuUsage = operatingSystemMXBean.getSystemCpuLoad();
        return new CpuLoadMetricSnapshot(jvmCpuUsage, systemCpuUsage);
    }

    @Override
    public String toString() {
        return "CpuLoadMetric for Oracle Java 1.7+";
    }
}
