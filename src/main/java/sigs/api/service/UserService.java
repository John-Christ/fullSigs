package sigs.api.service;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import sigs.api.exception.RestApiNotFoundException;
import sigs.api.dao.DAOUser;
import sigs.api.model.Role;
import sigs.api.repository.DaoUser;
import sigs.api.repository.RoleRepository;

import java.util.Set;

public class UserService {

    private final  RoleRepository roleRepository;

    private final  DaoUser daouser;

    @Autowired
    public UserService (RoleRepository roleRepository, DaoUser daouser) {

        this.daouser = daouser;
        this.roleRepository = roleRepository;
    }
    public void enrollUser (Long userId, Long roleId){

        DAOUser daoUser = daouser.findById(userId).orElseThrow(() -> new RestApiNotFoundException(userId));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RestApiNotFoundException(roleId));


        daoUser.getAssignedRoles().add(role);
        role.getUserSet().add(daoUser);

        daouser.save(daoUser);
        roleRepository.save(role);
    }




    @Autowired
    private DaoUser daouser;


    @Autowired
    private RoleRepository roleRepository;








    public  DAOUser assignRoleToUser(Long id, Long roleId ) {
        Set<Role> roleSet = null;
        DAOUser user = daouser.findById(id).get();
        Role role = roleRepository.findById(roleId).get();
        roleSet =  user.getAssignedRoles();
        roleSet.add(role);
        user.setAssignedRoles(roleSet);
        return daouser.save(user);
    }



}



 */