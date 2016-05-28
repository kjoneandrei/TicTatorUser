package com.jofa.dao;

import com.jofa.model.UserRoles;

public interface UserRolesDao extends GenericDao<UserRoles, Integer>
{

	public UserRoles findByUserId(Integer userId);

}
