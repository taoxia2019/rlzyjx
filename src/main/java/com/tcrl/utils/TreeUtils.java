package com.tcrl.utils;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tcrl.entity.Permission;

import java.util.List;

public class TreeUtils {
    public static  void setPermissionsTree(Integer parentId, List<Permission> permissionAll,JSONArray array){

        for(Permission per:permissionAll){
            if(per.getParentid().equals(parentId)){
                String string= JSONObject.toJSONString(per);
                JSONObject parent=(JSONObject)JSONObject.parse(string);
                array.add(parent);
                if(permissionAll.stream().filter(p->p.getParentid().equals(per.getId())).findAny()!=null){
                    JSONArray child=new JSONArray();
                    parent.put("child",child);
                    setPermissionsTree(per.getId(),permissionAll,child);
                }
            }
        }

    }
}
