package com.sample.android.model.entity;

/**
 * @version v1.0
 * @项目: sample-user-manager-application
 * @包名： com.sample.android.model.entity
 * @功能描述： 用户实体类
 * @作者： 杨松松
 * @创建时间： 2018/3/7 22:05
 */

public class UserEntity {

    // id:
    private Long id;

    // Login_name :登录名
    private String loginName;

    // zh_name :中文名
    private String zhName;

    // en_name :英文名
    private String enName;

    // sex :性别
    private Integer sex;

    // birth :生日
    private String birth;

    // email :邮箱
    private String email;

    // phone :电话
    private String phone;

    // address :地址
    private String address;

    // password :ic_user_pwd
    private String password;

    // order_by :排序
    private Long orderBy;

    // create_time :创建时间
    private String createTime;

    // update_time :更新时间
    private String updateTime;

    // status :数据状态，1、正常，2、删除，3、禁用
    private Integer status;

    // is_final :是否能修改
    private Integer isFinal;

    /**
     * get id
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * set id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get 登录名
     * @return
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * set 登录名
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * get 中文名
     * @return
     */
    public String getZhName() {
        return zhName;
    }

    /**
     * set 中文名
     * @param zhName
     */
    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    /**
     * get 英文名
     * @return
     */
    public String getEnName() {
        return enName;
    }

    /**
     * set 英文名
     * @param enName
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * get 性别
     * @return
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * set 性别
     * @param sex
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * get 生日
     * @return
     */
    public String getBirth() {
        return birth;
    }

    /**
     * set 生日
     * @param birth
     */
    public void setBirth(String birth) {
        this.birth = birth;
    }

    /**
     * get 邮箱地址
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * set 邮箱地址
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get 电话
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set 电话
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * get 地址
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * set 地址
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * get ic_user_pwd
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * set ic_user_pwd
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * get 排序方式
     * @return
     */
    public Long getOrderBy() {
        return orderBy;
    }

    /**
     * set 排序方式
     * @param orderBy
     */
    public void setOrderBy(Long orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * get 创建日期
     * @return
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * set 创建日期
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * get 更新日期
     * @return
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * set 更新日期
     * @param updateTime
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * gte 文件状态
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * set 文件状态
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * get 能否修改参数
     * @return
     */
    public Integer getIsFinal() {
        return isFinal;
    }

    /**
     * set 修改参数
     * @param isFinal
     */
    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", zhName='" + zhName + '\'' +
                ", enName='" + enName + '\'' +
                ", sex=" + sex +
                ", birth='" + birth + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", orderBy=" + orderBy +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", isFinal=" + isFinal +
                '}';
    }
}
