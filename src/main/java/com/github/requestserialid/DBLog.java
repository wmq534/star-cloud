package com.github.requestserialid;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 对外暴露的接口日志，四个方法用于记录不同功能日志
 * 
 * @author hugangjs2
 * 
 */
@Component("dblog")
public class DBLog {

	private Logger logger = LoggerFactory.getLogger(DBLog.class);

//	@Resource
//	private LogQueueContainer logQueueContainer;
	
	private static String localHostAddress;
	
	static {
		try {
			localHostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 记录业务操作日志
	 * 
	 * @param operationLog
	 */
	public void operationLog(OperationLog operationLog) {
	    if(logger.isDebugEnabled()){
	        logger.debug("开始记录系统操作日志->流水号:{},内容:{}", operationLog.getSerialNo(),
	                operationLog.toString());
		}
		logger.info(operationLog.toString());
		//log(operationLog);
 
	}
	
	/**
     * 接收请求监控日志 调用时间操作一个指定的值
     * @param monitorRemoteLog
     */
    public void monitorReceiverLog(MonitorReceiverLog monitorReceiverLog){
        if(logger.isDebugEnabled()){
            logger.debug("开始记录接收请求检控日志->流水号:{},内容:{}",
                    monitorReceiverLog.getInnerSerialId(), monitorReceiverLog.toString());
        }
       /* //响应时间超过配置时间则记录日志
        if(monitorReceiverLog.getTimeCost() != null && monitorConfig.getMonitorReceiverTime() * 1000 <= monitorReceiverLog.getTimeCost()){
            monitorReceiverLog.setServiceNo(localHostAddress);
            log(monitorReceiverLog);
        }*/
    }


	/*
	private void log(Log log){
		if(logQueueContainer.getLogQueueImpl().capacity().intValue() > logQueueContainer.getLogQueueImpl().count().intValue()){
			logQueueContainer.getLogQueueImpl().putLog(log);
		}else{
			logger.info("日志队列已满，不记得数据库日志！日志内容如下：");
			logger.info(log.toString());
		}
	}

	
	private void remoteApiLog2MonitorRemoteLog(RemoteApiLog remoteApiLog,MonitorRemoteLog monitorRemoteLog){
		if(monitorRemoteLog == null){
			monitorRemoteLog = new MonitorRemoteLog();
		}
		monitorRemoteLog.setCreateDate(remoteApiLog.getCreationDate());
		monitorRemoteLog.setId(remoteApiLog.getId());
		monitorRemoteLog.setInnerSerialId(remoteApiLog.getInnerSerialId());
		monitorRemoteLog.setInterfaceId(remoteApiLog.getInterfaceId());
		monitorRemoteLog.setInterfaceName(remoteApiLog.getInterfaceName());
		monitorRemoteLog.setInvokeDate(remoteApiLog.getInvokeDate());
		monitorRemoteLog.setParams(remoteApiLog.getParams());
		monitorRemoteLog.setReturnCode(remoteApiLog.getReturnCode());
		monitorRemoteLog.setReturnDate(remoteApiLog.getReturnDate());
		monitorRemoteLog.setReturnMsg(remoteApiLog.getReturnMsg());
		monitorRemoteLog.setServiceNo(localHostAddress);
		monitorRemoteLog.setTimeCost(remoteApiLog.getTimeCost() == null ? 0:remoteApiLog.getTimeCost().intValue());
	}*/
}
