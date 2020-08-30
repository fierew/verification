package com.weigh.verification.service.impl.rbac;

import com.weigh.verification.service.rbac.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 机构管理
 *
 * @author xuyang
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {
}
