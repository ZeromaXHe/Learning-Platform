package com.zerox.cache;

import com.google.common.base.MoreObjects;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 15:10
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson31_32Employee {
    private final String name;
    private final String dept;
    private final String empId;
    private final byte[] data = new byte[2 * 1024 * 1024];

    public Lesson31_32Employee(String name, String dept, String empId) {
        this.name = name;
        this.dept = dept;
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getEmpId() {
        return empId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("department", dept)
                .add("employeeId", empId)
                .toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("The employee [" + getName() + "] will be GC.");
    }
}
