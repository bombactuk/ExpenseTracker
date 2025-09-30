package com.expense.tracker.service.impl;



import com.expense.tracker.entity.Role;
import com.expense.tracker.repository.RoleRepository;
import com.expense.tracker.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }

}
