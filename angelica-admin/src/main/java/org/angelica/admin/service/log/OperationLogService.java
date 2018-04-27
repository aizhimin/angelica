package org.angelica.admin.service.log;

import org.angelica.admin.entity.log.OperationLog;

public interface OperationLogService {
	
	public OperationLog getOperationLog(Long id);
	
	public void saveOperationLog(OperationLog operationLog) ;
	
}
