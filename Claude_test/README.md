# Multi-Level Menu System

Spring Boot 多级权限菜单系统

## 功能特性

- 多级菜单树形结构
- RESTful API 接口
- MySQL 数据库存储
- 菜单 CRUD 操作
- 父子关系管理
- 排序功能

## 技术栈

- Spring Boot 3.2.1
- Spring Data JPA
- MySQL 8.0+
- Lombok
- Maven

## 数据库配置

1. 创建数据库:
```sql
CREATE DATABASE menu_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 修改 `application.yml` 中的数据库连接信息:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/menu_system
    username: root
    password: your_password
```

## 运行项目

```bash
mvn spring-boot:run
```

## API 接口

### 1. 获取菜单树
```
GET /api/menus/tree
GET /api/menus/tree?status=true
```

### 2. 获取单个菜单
```
GET /api/menus/{id}
```

### 3. 获取子菜单列表
```
GET /api/menus/children?parentId={parentId}
```

### 4. 创建菜单
```
POST /api/menus
Content-Type: application/json

{
  "name": "系统管理",
  "parentId": null,
  "path": "/system",
  "icon": "system",
  "menuType": "M",
  "sortOrder": 1,
  "visible": true,
  "status": true
}
```

### 5. 更新菜单
```
PUT /api/menus/{id}
Content-Type: application/json

{
  "name": "系统管理",
  "path": "/system",
  "sortOrder": 1
}
```

### 6. 删除菜单
```
DELETE /api/menus/{id}
```

## 菜单字段说明

- `id`: 菜单ID
- `name`: 菜单名称
- `parentId`: 父菜单ID (null表示顶级菜单)
- `path`: 路由路径
- `component`: 组件路径
- `icon`: 菜单图标
- `menuType`: 菜单类型 (M=目录, C=菜单, F=按钮)
- `permission`: 权限标识
- `sortOrder`: 排序号
- `visible`: 是否可见
- `status`: 菜单状态
- `remark`: 备注

## 测试数据

可以使用以下 SQL 插入测试数据:

```sql
INSERT INTO sys_menu (name, parent_id, path, icon, menu_type, sort_order, visible, status, create_time, update_time)
VALUES
('系统管理', NULL, '/system', 'system', 'M', 1, 1, 1, NOW(), NOW()),
('用户管理', 1, '/system/user', 'user', 'C', 1, 1, 1, NOW(), NOW()),
('角色管理', 1, '/system/role', 'role', 'C', 2, 1, 1, NOW(), NOW()),
('菜单管理', 1, '/system/menu', 'menu', 'C', 3, 1, 1, NOW(), NOW());
```
