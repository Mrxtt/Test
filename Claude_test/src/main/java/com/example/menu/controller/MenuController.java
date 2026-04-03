package com.example.menu.controller;

import com.example.menu.dto.MenuDTO;
import com.example.menu.dto.MenuTreeDTO;
import com.example.menu.entity.Menu;
import com.example.menu.service.MenuService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/tree")
    public ResponseEntity<List<MenuTreeDTO>> getMenuTree(
            @RequestParam(required = false) Boolean status) {
        List<MenuTreeDTO> tree;
        if (status != null) {
            tree = menuService.getMenuTreeByStatus(status);
        } else {
            tree = menuService.getMenuTree();
        }
        return ResponseEntity.ok(tree);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        Menu menu = menuService.getMenuById(id);
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/children")
    public ResponseEntity<List<Menu>> getChildrenMenus(
            @RequestParam(required = false) Long parentId) {
        List<Menu> menus = menuService.getChildrenMenus(parentId);
        return ResponseEntity.ok(menus);
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@Valid @RequestBody MenuDTO menuDTO) {
        Menu menu = menuService.createMenu(menuDTO);
        return ResponseEntity.ok(menu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(
            @PathVariable Long id,
            @Valid @RequestBody MenuDTO menuDTO) {
        Menu menu = menuService.updateMenu(id, menuDTO);
        return ResponseEntity.ok(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.ok().build();
    }
}
