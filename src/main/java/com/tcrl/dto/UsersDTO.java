package com.tcrl.dto;

import com.tcrl.entity.Users;
import lombok.Data;

import java.util.List;

@Data
public class UsersDTO extends Users {
    private List<Integer> roleIds;

}
