package com.zhangys.entity;

import java.io.Serializable;

/**
 * Created by 123 on 2017/3/8.
 */
//@Entity(name = "department")
public class DepartmentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
    private Integer id;
//    @Column(name = "depName")
    private String depName;
//    @Column(name = "parentId")
    private Integer parentId;
//    @Column(name = "place")
    private String place;

    public DepartmentEntity() {
        super();
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getDepName() { return depName; }

    public void setDepName(String depName) { this.depName = depName; }

    public Integer getParentId() { return parentId; }

    public void setParentId(Integer parentId) { this.parentId = parentId; }

    public String getPlace() { return place; }

    public void setPlace(String place) { this.place = place; }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if(!(obj instanceof DepartmentEntity)){
            flag = false;
        }else {
            DepartmentEntity org = (DepartmentEntity)obj;
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
