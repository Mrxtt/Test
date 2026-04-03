package com.example.menu.entity;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "sys_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(length = 200)
    private String path;

    @Column(length = 100)
    private String component;

    @Column(length = 50)
    private String icon;

    @Column(name = "menu_type", length = 20)
    private String menuType;

    @Column(length = 100)
    private String permission;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(nullable = false)
    private Boolean visible = true;

    @Column(nullable = false)
    private Boolean status = true;

    @Column(length = 500)
    private String remark;

    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Transient
    private List<Menu> children = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }
}
