package cgm.system.MovieNet.service.impl;

import cgm.system.MovieNet.entity.Role;
import cgm.system.MovieNet.repository.RoleRepository;
import cgm.system.MovieNet.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveRoles(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void saveAllRoles(ArrayList<Role> roleArrayList) {
        roleRepository.saveAll(roleArrayList);
    }
}
