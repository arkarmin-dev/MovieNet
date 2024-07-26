package cgm.system.MovieNet.service;

import cgm.system.MovieNet.entity.Role;


import java.util.ArrayList;

public interface RoleService {

    public void saveRoles(Role role);

    public void saveAllRoles(ArrayList<Role> roleArrayList);
}
