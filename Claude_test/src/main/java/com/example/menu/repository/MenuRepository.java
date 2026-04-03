package com.example.menu.repository;

import com.example.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByParentIdOrderBySortOrder(Long parentId);

    List<Menu> findByParentIdIsNullOrderBySortOrder();

    List<Menu> findByStatusOrderBySortOrder(Boolean status);

    boolean existsByParentId(Long parentId);
}
