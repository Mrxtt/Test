package com.example.menu.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MenuDTO {

    private Long id;

    @NotBlank(message = "菜单名称不能为空")
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
}
