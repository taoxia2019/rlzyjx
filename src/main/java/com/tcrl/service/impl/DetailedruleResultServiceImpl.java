package com.tcrl.service.impl;

import com.tcrl.entity.DetailedruleResult;
import com.tcrl.dao.DetailedruleResultMapper;
import com.tcrl.service.DetailedruleResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taoxia
 * @since 2019-10-23
 */
@Service
@Transactional
public class DetailedruleResultServiceImpl extends ServiceImpl<DetailedruleResultMapper, DetailedruleResult> implements DetailedruleResultService {

}
