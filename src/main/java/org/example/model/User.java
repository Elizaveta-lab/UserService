package org.example.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "age")
    private String age;

    @Column(name = "create_at",nullable = false,updatable = false)
    private LocalDateTime createAt;

    public User(){

    }

    public User(String name, String email, String age){
        this.name=name;
        this.email=email;
        this.age=age;
    }

    public Long getId() {
        return id;
    }

    public String getName(){return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAge(){return age;}
    public void setAge(String age){this.age=age;}
    public LocalDateTime getCreateAt() {
        return createAt;
    }
    public void setCreateAt(LocalDateTime createAt){this.createAt=createAt;}
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", age='" + age + '\''
                + ", createAt=" + createAt
                + '}';
    }
    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }
}
