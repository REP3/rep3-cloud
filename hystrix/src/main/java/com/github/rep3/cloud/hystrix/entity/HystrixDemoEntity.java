package com.github.rep3.cloud.hystrix.entity;


import java.util.Objects;

public class HystrixDemoEntity {
    private String str;

    public HystrixDemoEntity(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "HystrixDemoEntity{" +
                "str='" + str + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HystrixDemoEntity that = (HystrixDemoEntity) o;
        return Objects.equals(str, that.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str);
    }
}
