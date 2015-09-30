/*
 * Copyright (c) ANTVISION 2011 All Rights Reserved Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package cjy.com.hazelcast;


/**
 * <p>聚集统计对象。<p>
 * 
 * 创建日期 2013-7-26<br>
 * @author 陈军营(chenjunying@antvision.cn) <br>
 * @version $Revision$ $Date$
 * @since 3.0.0
 */
public class AggregationStatistics implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    // 度量元ID
    private Integer metricId;
    // 开始时间
    private Long startTimeInMillis;
    // 最后修改时间
    private Long modifyTimeInMillis;
    // 最大值
    private Double max;
    // 最小值
    private Double min;
    // 度量元值总和
    private Double sum;
    // 度量元个数
    private Integer num;

    public AggregationStatistics() {}

    public AggregationStatistics(Integer metricId, Long startTimeInMillis, Long modifyTimeInMillis, Double max,
            Double min, Double sum, Integer num) {
        this.metricId = metricId;
        this.startTimeInMillis = startTimeInMillis;
        this.modifyTimeInMillis = modifyTimeInMillis;
        this.max = max;
        this.min = min;
        this.sum = sum;
        this.num = num;
    }

    /*
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "metricId=" + metricId + ", startTimeInMillis=" + startTimeInMillis + ", modifyTimeInMillis="
                + modifyTimeInMillis + ", max=" + max + ", min=" + min + ", sum=" + sum + ", num=" + num;
    }

    /**
     * @return the metricId
     */
    public Integer getMetricId() {
        return metricId;
    }

    /**
     * @param metricId the metricId to set
     */
    public void setMetricId(Integer metricId) {
        this.metricId = metricId;
    }

    /**
     * @return the startTimeInMillis
     */
    public Long getStartTimeInMillis() {
        return startTimeInMillis;
    }

    /**
     * @param startTimeInMillis the startTimeInMillis to set
     */
    public void setStartTimeInMillis(Long startTimeInMillis) {
        this.startTimeInMillis = startTimeInMillis;
    }

    /**
     * @return the modifyTimeInMillis
     */
    public Long getModifyTimeInMillis() {
        return modifyTimeInMillis;
    }

    /**
     * @param modifyTimeInMillis the modifyTimeInMillis to set
     */
    public void setModifyTimeInMillis(Long modifyTimeInMillis) {
        this.modifyTimeInMillis = modifyTimeInMillis;
    }

    /**
     * @return the max
     */
    public Double getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Double max) {
        this.max = max;
    }

    /**
     * @return the min
     */
    public Double getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(Double min) {
        this.min = min;
    }

    /**
     * @return the sum
     */
    public Double getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }

    /**
     * @return the num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(Integer num) {
        this.num = num;
    }
}
