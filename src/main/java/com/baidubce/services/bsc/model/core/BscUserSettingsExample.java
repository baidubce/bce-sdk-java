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
 * the access for bsc user setting table
 */
public class BscUserSettingsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BscUserSettingsExample() {
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

        public Criteria andUserKeyIsNull() {
            addCriterion("user_key is null");
            return (Criteria) this;
        }

        public Criteria andUserKeyIsNotNull() {
            addCriterion("user_key is not null");
            return (Criteria) this;
        }

        public Criteria andUserKeyEqualTo(String value) {
            addCriterion("user_key =", value, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyNotEqualTo(String value) {
            addCriterion("user_key <>", value, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyGreaterThan(String value) {
            addCriterion("user_key >", value, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyGreaterThanOrEqualTo(String value) {
            addCriterion("user_key >=", value, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyLessThan(String value) {
            addCriterion("user_key <", value, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyLessThanOrEqualTo(String value) {
            addCriterion("user_key <=", value, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyLike(String value) {
            addCriterion("user_key like", value, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyNotLike(String value) {
            addCriterion("user_key not like", value, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyIn(List<String> values) {
            addCriterion("user_key in", values, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyNotIn(List<String> values) {
            addCriterion("user_key not in", values, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyBetween(String value1, String value2) {
            addCriterion("user_key between", value1, value2, "userKey");
            return (Criteria) this;
        }

        public Criteria andUserKeyNotBetween(String value1, String value2) {
            addCriterion("user_key not between", value1, value2, "userKey");
            return (Criteria) this;
        }

        public Criteria andSettingNameIsNull() {
            addCriterion("setting_name is null");
            return (Criteria) this;
        }

        public Criteria andSettingNameIsNotNull() {
            addCriterion("setting_name is not null");
            return (Criteria) this;
        }

        public Criteria andSettingNameEqualTo(String value) {
            addCriterion("setting_name =", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameNotEqualTo(String value) {
            addCriterion("setting_name <>", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameGreaterThan(String value) {
            addCriterion("setting_name >", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameGreaterThanOrEqualTo(String value) {
            addCriterion("setting_name >=", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameLessThan(String value) {
            addCriterion("setting_name <", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameLessThanOrEqualTo(String value) {
            addCriterion("setting_name <=", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameLike(String value) {
            addCriterion("setting_name like", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameNotLike(String value) {
            addCriterion("setting_name not like", value, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameIn(List<String> values) {
            addCriterion("setting_name in", values, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameNotIn(List<String> values) {
            addCriterion("setting_name not in", values, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameBetween(String value1, String value2) {
            addCriterion("setting_name between", value1, value2, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingNameNotBetween(String value1, String value2) {
            addCriterion("setting_name not between", value1, value2, "settingName");
            return (Criteria) this;
        }

        public Criteria andSettingValueIsNull() {
            addCriterion("setting_value is null");
            return (Criteria) this;
        }

        public Criteria andSettingValueIsNotNull() {
            addCriterion("setting_value is not null");
            return (Criteria) this;
        }

        public Criteria andSettingValueEqualTo(String value) {
            addCriterion("setting_value =", value, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueNotEqualTo(String value) {
            addCriterion("setting_value <>", value, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueGreaterThan(String value) {
            addCriterion("setting_value >", value, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueGreaterThanOrEqualTo(String value) {
            addCriterion("setting_value >=", value, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueLessThan(String value) {
            addCriterion("setting_value <", value, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueLessThanOrEqualTo(String value) {
            addCriterion("setting_value <=", value, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueLike(String value) {
            addCriterion("setting_value like", value, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueNotLike(String value) {
            addCriterion("setting_value not like", value, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueIn(List<String> values) {
            addCriterion("setting_value in", values, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueNotIn(List<String> values) {
            addCriterion("setting_value not in", values, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueBetween(String value1, String value2) {
            addCriterion("setting_value between", value1, value2, "settingValue");
            return (Criteria) this;
        }

        public Criteria andSettingValueNotBetween(String value1, String value2) {
            addCriterion("setting_value not between", value1, value2, "settingValue");
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