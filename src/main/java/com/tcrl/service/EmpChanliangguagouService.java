package com.tcrl.service;

import com.tcrl.entity.EmpChanliangguagou;
import com.tcrl.base.result.Results;

import java.util.Map;

public interface EmpChanliangguagouService {

   Results<EmpChanliangguagou>  getByPage(Integer offset, Integer limit);

   Results  save(EmpChanliangguagou empChanliangguagou);

   Results  getById(Integer id);

   Results update(EmpChanliangguagou empChanliangguagou);

   Results  delete(EmpChanliangguagou empChanliangguagou);

   EmpChanliangguagou getEmpChanliangguagouById(Integer id);

}

