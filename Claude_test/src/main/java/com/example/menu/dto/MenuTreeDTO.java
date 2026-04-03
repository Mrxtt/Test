package com.example.menu.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MenuTreeDTO {

    private Long id;
    private String name;
    private Long parentId;
    private String path;
    private String component;
    private String icon;
    private String menuType;
    private String permission;
    private Integer sortOrder;
    private Boolean visible;
    private Boolean status;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<MenuTreeDTO> children = new ArrayList<>();
}
