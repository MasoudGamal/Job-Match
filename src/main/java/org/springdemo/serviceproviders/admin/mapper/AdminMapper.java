package org.springdemo.serviceproviders.admin.mapper;

import org.mapstruct.Mapper;
import org.springdemo.serviceproviders.admin.dto.AdminRequest;
import org.springdemo.serviceproviders.admin.dto.AdminResponse;
import org.springdemo.serviceproviders.admin.entity.Admin;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    Admin requestToAdmin(AdminRequest adminRequest);

    AdminResponse adminToResponse(Admin admin);

    List<AdminResponse> listAdminToListResponse(List<Admin> admins);
}
