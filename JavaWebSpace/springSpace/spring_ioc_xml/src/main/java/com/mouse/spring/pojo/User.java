package com.mouse.spring.pojo;

import lombok.*;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;

    public User() {
        System.out.println("生命周期1：实例化");
    }

    public void setId(Integer id) {
        System.out.println("生命周期2：依赖注入");
        this.id = id;
    }

    public void initMethod() {
        System.out.println("生命周期3：初始化");
    }

    public void destroyMethod() {
        System.out.println("生命周期4：使用当前对象，销毁");
    }
}
