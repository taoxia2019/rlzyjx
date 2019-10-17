package com.tcrl.dto;

import com.tcrl.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO extends Role {

    private List<Integer> permissionIds;
    private Boolean flag=false;
}
