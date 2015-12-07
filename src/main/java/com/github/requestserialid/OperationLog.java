package com.github.requestserialid;

import java.util.ArrayList;
import java.util.List;

public class OperationLog implements java.io.Serializable, Cloneable,Log {

    private static final long serialVersionUID = 1L;

    private Long id; // Pk

    private String serialNo; // 流水号，一般填写内部流水号

    private String custId; //用户ID前台传递过来的custId(inner项目模块包含)

    private Integer phoneNum; //手机号

    private Long sourceId; // 订单系统中为订单id、申请单id、代退单id，商品系统中为 商品ID

    private String sourceSystem; // 来源系统，inner，outer 都由外部传递过来，我们发送请求到outer是需要传递 sourcesystem到outer中

    private Integer sourceType; //来源类型，订单系统中为订单类型：订单：1，商品：2，申请单：3，代退单，4，商品系统中为商品类型：产品，商品

    private String operationType; //操作类型，自己定义；可以填入本次操作执行业务的名称

    private String operationResult; //操作结果

    private java.sql.Timestamp operationDate; //操作时间

    private java.sql.Timestamp creationDate; //创建时间 ，数据库端加入，不用关心

    private String remark; // 描述

    private List<OperationDetailLog> operationDetailLogList = new ArrayList<OperationDetailLog>();

    public void putOperationDetailLog(OperationDetailLog operationDetailLog) {
        operationDetailLogList.add(operationDetailLog);
    }

    public List<OperationDetailLog> getOperationDetailLogList() {
        return operationDetailLogList;
    }

    public void setOperationDetailLogList(
            List<OperationDetailLog> operationDetailLogList) {
        this.operationDetailLogList = operationDetailLogList;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getCustId() {
        return this.custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }


    public Long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public Integer getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationResult() {
        return this.operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public java.sql.Timestamp getOperationDate() {
        return this.operationDate;
    }

    public void setOperationDate(java.sql.Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    public java.sql.Timestamp getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(java.sql.Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "OperationLog [ serialNo=" + serialNo + ", custId=" + custId
                + ", phoneNum=" + getPhoneNum() + ", sourceId=" + sourceId
                + ", sourceSystem=" + sourceSystem + ", sourceType="
                + sourceType + ", operationType=" + operationType
                + ", operationResult=" + operationResult + ", operationDate="
                + operationDate + ", remark=" + remark
                + ", operationDetailLogList=" + operationDetailLogList + "]";
    }

    public Integer getPhoneNum() {
      return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
      this.phoneNum = phoneNum;
    }
}