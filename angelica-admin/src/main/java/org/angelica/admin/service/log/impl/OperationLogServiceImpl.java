package org.angelica.admin.service.log.impl;

import org.angelica.admin.dao.log.OperationLogDao;
import org.angelica.admin.entity.log.OperationLog;
import org.angelica.admin.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationLogServiceImpl implements OperationLogService{

	@Autowired
	private OperationLogDao operationLogDao;
	
	@Override
	public OperationLog getOperationLog(Long id) {
		return operationLogDao.selectById(id);
	}

	@Override
	public void saveOperationLog(OperationLog operationLog) {
		operationLogDao.insert(operationLog);
	}

}
