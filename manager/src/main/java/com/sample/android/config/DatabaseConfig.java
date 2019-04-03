package com.sample.android.config;


/**
 * @version v1.0
 * @项目: sample-user-manager-application
 * @包名： com.sample.android.server
 * @功能描述： SQLite是配置
 * @Author： 杨松松
 * @创建时间： 2017/11/19 16:26
 */

public class DatabaseConfig {

    /**
     * 数据库名称
     */
    public static final String DATABASE_NAME = "user.db";
    /**
     * 数据库版本1
     */
    public static final int DATABASE_VERSION = 1;
    /**
     * 数据库表名
     */
    public static final String USER_TABLE_NAME = "user";
    /**
     * 字段_id
     */
    public static final String FIELD_ID = "_id";
    /**
     * 字段name
     */
    public static final String FIELD_NAME = "name";
    /**
     * 字段remark
     */
    public static final String FIELD_REMARK = "remark";
    /**
     * 字段时间time
     */
    public static final String FIELD_TIME = "time";


    /**
     * 用户信息配置文件
     */
    public static final String ID = "id";

    /**
     * Login_name :登录名
     */
    public static final String LOGI_NNAME = "loginName";

    /**
     * zh_name :中文名
     */
    public static final String ZH_NAME = "zhName";

    /**
     * en_name :英文名
     */
    public static final String EN_NAME = "enName";

    /**
     * sex :性别
     */
    public static final String SEX = "sex";

    /**
     * birth :生日
     */
    public static final String BIRTH = "birth";

    /**
     * email :邮箱
     */
    public static final String EMAIL = "email";

    /**
     *  phone :电话
     */
    public static final String PHONE = "phone";

    /**
     * address :地址
     */
    public static final String ADDRESS = "address";

    /**
     * password :ic_user_pwd
     */
    public static final String PASSWORD = "password";

    /**
     * order_by :排序
     */
    public static final String ORDER_BY = "orderBy";

    /**
     *  create_time :创建时间
     */
    public static final String CREATE_TIME = "createTime";

    /**
     * update_time :更新时间
     */
    public static final String UPDATE_TIME = "updateTime";
}
