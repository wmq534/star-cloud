package com.github.requestserialid;

import org.slf4j.Logger;

public  class BaseRequest {

    public String innerSerialId; // 内部流水号
    public String operatorIp; // 操作ip

	public String getInnerSerialId() {
        return innerSerialId;
    }

    public void setInnerSerialId(String innerSerialId) {
        this.innerSerialId = innerSerialId;
    }

    public String getOperatorIp() {
		return operatorIp;
	}

	public void setOperatorIp(String operatorIp) {
		this.operatorIp = operatorIp;
	}

	protected void log(Logger logger,String after) {
        String beginPre = MakeCommonBody.makeInnerserialBody(innerSerialId)+after;
        logger.info(beginPre + "innerSerialId:{}", innerSerialId);
    }
}