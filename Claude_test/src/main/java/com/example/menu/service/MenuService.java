package com.example.menu.service;

import com.example.menu.dto.MenuDTO;
import com.example.menu.dto.MenuTreeDTO;
import com.example.menu.entity.Menu;
import com.example.menu.repository.MenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<MenuTreeDTO> getMenuTree() {
        List<Menu> allMenus = menuRepository.findAll();
        return buildTree(allMenus, null);
    }

    public List<MenuTreeDTO> getMenuTreeByStatus(Boolean status) {
        List<Menu> allMenus = menuRepository.findByStatusOrderBySortOrder(status);
        return buildTree(allMenus, null);
    }

    private List<MenuTreeDTO> buildTree(List<Menu> allMenus, Long parentId) {
        return allMenus.stream()
            .filter(menu -> {
                if (parentId == null) {
                    return menu.getParentId() == null;
                }
                return parentId.equals(menu.getParentId());
            })
            .map(menu -> {
                MenuTreeDTO dto = new MenuTreeDTO();
                BeanUtils.copyProperties(menu, dto);
                dto.setChildren(buildTree(allMenus, menu.getId()));
                return dto;
            })
            .collect(Collectors.toList());
    }

    public Menu getMenuById(Long id) {
        return menuRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("菜单不存在"));
    }

    @Transactional
    public Menu createMenu(MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO, menu);

        if (menu.getSortOrder() == null) {
            menu.setSortOrder(0);
        }
        if (menu.getVisible() == null) {
            menu.setVisible(true);
        }
        if (menu.getStatus() == null) {
            menu.setStatus(true);
        }

        return menuRepository.save(menu);
    }

    @Transactional
    public Menu updateMenu(Long id, MenuDTO menuDTO) {
        Menu menu = getMenuById(id);

        if (id.equals(menuDTO.getParentId())) {
            throw new RuntimeException("不能将自己设置为父菜单");
        }

        BeanUtils.copyProperties(menuDTO, menu, "id", "createTime");
        return menuRepository.save(menu);
    }

    @Transactional
    public void deleteMenu(Long id) {
        if (menuRepository.existsByParentId(id)) {
            throw new RuntimeException("存在子菜单，无法删除");
        }
        menuRepository.deleteById(id);
    }

    public List<Menu> getChildrenMenus(Long parentId) {
        if (parentId == null) {
            return menuRepository.findByParentIdIsNullOrderBySortOrder();
        }
        return menuRepository.findByParentIdOrderBySortOrder(parentId);
    }
}
