package com.sharfine.springsecurity.constant;

/**
 * Main业务模块异常枚举编码构成常量
 * <p>
 * 异常枚举编码由3部分组成，如下：
 * <p>
 * 模块编码（2位） + 分类编码（4位） + 具体项编码（至少1位）
 * <p>
 * 模块编码和分类编码在ExpEnumConstant类中声明
 *
 * @author dujc
 * @date 2021/4/28 15:30
 */
public interface MainExpEnumConstant {

    /**
     * 模块分类编码（2位）
     * <p>
     * guns-main模块异常枚举编码
     */
    int GUNS_MAIN_MODULE_EXP_CODE = 60;

    /*  分类编码（4位）
     *   前2位代表业务模块，比如人员（11）物料（12）、设备（13）、工序（14）等
     *   后2位代表异常分类编码，比如参数校验相关的异常（01）等
     *  */

    //  人员档案

    /**
     * 人员参数校验相关异常枚举
     */
    int MAIN_STAFF_VALIDATION_EXCEPTION_ENUM = 1101;


    //  物料档案

    /**
     * 物料参数校验相关异常枚举
     */
    int MAIN_MATERIAL_VALIDATION_EXCEPTION_ENUM = 1201;


    // 设备档案

    /**
     * 设备参数校验相关异常枚举
     */
    int MAIN_DEVICE_VALIDATION_EXCEPTION_ENUM = 1301;

    // 工序档案
    /**
     * 工序参数校验相关异常枚举
     */
    int MAIN_PROCESS_VALIDATION_EXCEPTION_ENUM = 1401;

    //  生产班组
    /**
     * 生产班组参数校验相关异常枚举
     */
    int MAIN_PRODUCTION_GROUP_VALIDATION_EXCEPTION_ENUM = 1501;

    //  工艺路线
    /**
     * 工艺路线参数校验相关异常枚举
     */
    int MAIN_TECHNOLOGY_VALIDATION_EXCEPTION_ENUM = 1601;
    //  数采配置
    /**
     * 数采配置参数校验相关异常枚举
     */
    int MAIN_DATA_ACQUISITION_CONFIG_VALIDATION_EXCEPTION_ENUM = 1701;
    //  单位配置
    /**
     * 单位配置参数校验相关异常枚举
     */
    int MAIN_UNIT_VALIDATION_EXCEPTION_ENUM = 1801;
    int MAIN_PRODUCTION_PLAN_VALIDATION_EXCEPTION_ENUM = 1901;
    int MAIN_ORDER_VALIDATION_EXCEPTION_ENUM = 2001;
    int MAIN_BOM_VALIDATION_EXCEPTION_ENUM = 2101;
    int MAIN_SCHEDULE_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_QC_PRECEPT_VALIDATION_EXCEPTION_ENUM = 2301;
    /**
     * 库存管理参数校验相关异常枚举
     */
    int MAIN_INVENTORY_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_PROCESS_PLAN_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_PRODUCT_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_PICKING_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_WORK_ORDER_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_TASK_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_TASK_PRODUCTION_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_TASK_PICKING_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_TASK_OUTSOURCING_VALIDATION_EXCEPTION_ENUM = 2201;
    int MAIN_TASK_QC_VALIDATION_EXCEPTION_ENUM = 2201;
}
