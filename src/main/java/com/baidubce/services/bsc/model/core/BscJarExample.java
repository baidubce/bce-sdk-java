/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bsc.model.core;

import java.util.ArrayList;
import java.util.List;

/**
 * the access for bsc jar table
 */
public class BscJarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BscJarExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(Long value) {
            addCriterion("job_id =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(Long value) {
            addCriterion("job_id <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(Long value) {
            addCriterion("job_id >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(Long value) {
            addCriterion("job_id >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(Long value) {
            addCriterion("job_id <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(Long value) {
            addCriterion("job_id <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<Long> values) {
            addCriterion("job_id in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<Long> values) {
            addCriterion("job_id not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(Long value1, Long value2) {
            addCriterion("job_id between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(Long value1, Long value2) {
            addCriterion("job_id not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNull() {
            addCriterion("file_type is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("file_type is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(String value) {
            addCriterion("file_type =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(String value) {
            addCriterion("file_type <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(String value) {
            addCriterion("file_type >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(String value) {
            addCriterion("file_type >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(String value) {
            addCriterion("file_type <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(String value) {
            addCriterion("file_type <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLike(String value) {
            addCriterion("file_type like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotLike(String value) {
            addCriterion("file_type not like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<String> values) {
            addCriterion("file_type in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<String> values) {
            addCriterion("file_type not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(String value1, String value2) {
            addCriterion("file_type between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(String value1, String value2) {
            addCriterion("file_type not between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andMainClassIsNull() {
            addCriterion("main_class is null");
            return (Criteria) this;
        }

        public Criteria andMainClassIsNotNull() {
            addCriterion("main_class is not null");
            return (Criteria) this;
        }

        public Criteria andMainClassEqualTo(String value) {
            addCriterion("main_class =", value, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassNotEqualTo(String value) {
            addCriterion("main_class <>", value, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassGreaterThan(String value) {
            addCriterion("main_class >", value, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassGreaterThanOrEqualTo(String value) {
            addCriterion("main_class >=", value, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassLessThan(String value) {
            addCriterion("main_class <", value, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassLessThanOrEqualTo(String value) {
            addCriterion("main_class <=", value, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassLike(String value) {
            addCriterion("main_class like", value, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassNotLike(String value) {
            addCriterion("main_class not like", value, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassIn(List<String> values) {
            addCriterion("main_class in", values, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassNotIn(List<String> values) {
            addCriterion("main_class not in", values, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassBetween(String value1, String value2) {
            addCriterion("main_class between", value1, value2, "mainClass");
            return (Criteria) this;
        }

        public Criteria andMainClassNotBetween(String value1, String value2) {
            addCriterion("main_class not between", value1, value2, "mainClass");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andBosBucketIsNull() {
            addCriterion("bos_bucket is null");
            return (Criteria) this;
        }

        public Criteria andBosBucketIsNotNull() {
            addCriterion("bos_bucket is not null");
            return (Criteria) this;
        }

        public Criteria andBosBucketEqualTo(String value) {
            addCriterion("bos_bucket =", value, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketNotEqualTo(String value) {
            addCriterion("bos_bucket <>", value, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketGreaterThan(String value) {
            addCriterion("bos_bucket >", value, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketGreaterThanOrEqualTo(String value) {
            addCriterion("bos_bucket >=", value, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketLessThan(String value) {
            addCriterion("bos_bucket <", value, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketLessThanOrEqualTo(String value) {
            addCriterion("bos_bucket <=", value, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketLike(String value) {
            addCriterion("bos_bucket like", value, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketNotLike(String value) {
            addCriterion("bos_bucket not like", value, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketIn(List<String> values) {
            addCriterion("bos_bucket in", values, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketNotIn(List<String> values) {
            addCriterion("bos_bucket not in", values, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketBetween(String value1, String value2) {
            addCriterion("bos_bucket between", value1, value2, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosBucketNotBetween(String value1, String value2) {
            addCriterion("bos_bucket not between", value1, value2, "bosBucket");
            return (Criteria) this;
        }

        public Criteria andBosObjectIsNull() {
            addCriterion("bos_object is null");
            return (Criteria) this;
        }

        public Criteria andBosObjectIsNotNull() {
            addCriterion("bos_object is not null");
            return (Criteria) this;
        }

        public Criteria andBosObjectEqualTo(String value) {
            addCriterion("bos_object =", value, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectNotEqualTo(String value) {
            addCriterion("bos_object <>", value, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectGreaterThan(String value) {
            addCriterion("bos_object >", value, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectGreaterThanOrEqualTo(String value) {
            addCriterion("bos_object >=", value, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectLessThan(String value) {
            addCriterion("bos_object <", value, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectLessThanOrEqualTo(String value) {
            addCriterion("bos_object <=", value, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectLike(String value) {
            addCriterion("bos_object like", value, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectNotLike(String value) {
            addCriterion("bos_object not like", value, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectIn(List<String> values) {
            addCriterion("bos_object in", values, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectNotIn(List<String> values) {
            addCriterion("bos_object not in", values, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectBetween(String value1, String value2) {
            addCriterion("bos_object between", value1, value2, "bosObject");
            return (Criteria) this;
        }

        public Criteria andBosObjectNotBetween(String value1, String value2) {
            addCriterion("bos_object not between", value1, value2, "bosObject");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameIsNull() {
            addCriterion("local_filename is null");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameIsNotNull() {
            addCriterion("local_filename is not null");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameEqualTo(String value) {
            addCriterion("local_filename =", value, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameNotEqualTo(String value) {
            addCriterion("local_filename <>", value, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameGreaterThan(String value) {
            addCriterion("local_filename >", value, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("local_filename >=", value, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameLessThan(String value) {
            addCriterion("local_filename <", value, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameLessThanOrEqualTo(String value) {
            addCriterion("local_filename <=", value, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameLike(String value) {
            addCriterion("local_filename like", value, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameNotLike(String value) {
            addCriterion("local_filename not like", value, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameIn(List<String> values) {
            addCriterion("local_filename in", values, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameNotIn(List<String> values) {
            addCriterion("local_filename not in", values, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameBetween(String value1, String value2) {
            addCriterion("local_filename between", value1, value2, "localFilename");
            return (Criteria) this;
        }

        public Criteria andLocalFilenameNotBetween(String value1, String value2) {
            addCriterion("local_filename not between", value1, value2, "localFilename");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}