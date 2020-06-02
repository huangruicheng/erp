package cn.java.service.impl;

import cn.java.dao.ConsoleLogDao;
import cn.java.model.ConsoleLog;
import cn.java.query.ConsoleLogQuery;
import cn.java.service.ConsoleLogService;

public class ConsoleLogServiceImpl extends BaseServiceImpl<ConsoleLog, ConsoleLogQuery> implements ConsoleLogService {

	
	private ConsoleLogDao consoleLogDao;
	
	public void setConsoleLogDao(ConsoleLogDao consoleLogDao) {
		this.consoleLogDao = consoleLogDao;
		this.baseDao = consoleLogDao;
	}
	
	

}
