package com.baidubce.services.aihc.common;

/**
 * AIHC 常量定义
 */
public class AihcConstants {

    /**
     * 数据集相关API动作
     */
    public static class DatasetActions {
        public static final String CREATE_DATASET = "CreateDataset";
        public static final String MODIFY_DATASET = "ModifyDataset";
        public static final String CREATE_DATASET_VERSION = "CreateDatasetVersion";
        public static final String DESCRIBE_DATASETS = "DescribeDatasets";
        public static final String DESCRIBE_DATASET = "DescribeDataset";
        public static final String DESCRIBE_DATASET_VERSIONS = "DescribeDatasetVersions";
        public static final String DESCRIBE_DATASET_VERSION = "DescribeDatasetVersion";
        public static final String DELETE_DATASET = "DeleteDataset";
        public static final String DELETE_DATASET_VERSION = "DeleteDatasetVersion";
    }

    /**
     * 模型相关API动作
     */
    public static class ModelActions {
        public static final String DESCRIBE_MODELS = "DescribeModels";
        public static final String DESCRIBE_MODEL = "DescribeModel";
        public static final String CREATE_MODEL = "CreateModel";
        public static final String DELETE_MODEL = "DeleteModel";
        public static final String MODIFY_MODEL = "ModifyModel";
    }

    /**
     * 推理相关API动作
     */
    public static class InferenceActions {
        public static final String CREATE_INFERENCE = "CreateInference";
        public static final String DESCRIBE_INFERENCES = "DescribeInferences";
        public static final String DELETE_INFERENCE = "DeleteInference";
    }

    /**
     * 存储类型
     */
    public static class StorageType {
        public static final String PFS = "PFS";
        public static final String BOS = "BOS";
    }

    /**
     * 导入格式
     */
    public static class ImportFormat {
        public static final String FILE = "FILE";
        public static final String FOLDER = "FOLDER";
    }

    /**
     * 可见范围
     */
    public static class VisibilityScope {
        public static final String ALL_PEOPLE = "ALL_PEOPLE";
        public static final String ONLY_OWNER = "ONLY_OWNER";
        public static final String USER_GROUP = "USER_GROUP";
    }

    /**
     * 权限类型
     */
    public static class Permission {
        public static final String READ_ONLY = "r";
        public static final String READ_WRITE = "rw";
    }
} 