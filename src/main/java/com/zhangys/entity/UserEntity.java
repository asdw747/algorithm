package com.zhangys.entity;

import java.io.Serializable;

/**
 * Created by 123 on 2017/3/7.
 */

//@Entity(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Integer id;
//    @Column(name = "fullName")
    private String fullName;
//    @Column(name = "loginName", unique = true, nullable = false)
    private String loginName;
//    @Column(name = "password")
    private String password;
//    @Column(name = "isActive")
    private Integer isActive = 1;
//    @Column(name = "email")
    private String email;
//    @Column(name = "cn")
    private String cn;
//    @Column(name = "cellphoneNum")
    private String cellphoneNum;

//    @ManyToOne(fetch = FetchType.EAGER)
    private DepartmentEntity department;

    public UserEntity() {
        super();
    }

    public UserEntity(String fullName, String loginName, String password, int isActive) {
        this.fullName = fullName;
        this.loginName = loginName;
        this.password = password;
        this.isActive = isActive;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLoginName() { return loginName; }

    public void setLoginName(String loginName) { this.loginName = loginName; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getCellphoneNum() { return cellphoneNum; }

    public void setCellphoneNum(String cellphoneNum) { this.cellphoneNum = cellphoneNum; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCn() { return cn; }

    public void setCn(String cn) { this.cn = cn; }

    public Integer getIsActive() { return isActive; }

    public void setIsActive(Integer isActive) { this.isActive = isActive; }

    public DepartmentEntity getDepartment() { return department; }

    public void setDepartment(DepartmentEntity department) { this.department = department; }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if(!(obj instanceof UserEntity)){
            flag = false;
        }else {
            UserEntity org = (UserEntity)obj;
            if(org.getId() == this.getId()){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
