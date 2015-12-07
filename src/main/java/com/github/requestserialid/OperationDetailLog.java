package com.github.requestserialid;

public class OperationDetailLog implements java.io.Serializable,Cloneable,Log{

    private static final long serialVersionUID = 1L;
    
    
        private Long id; //Pk  
    
        private String serialNo; //内部流水号
    
        private Long sourceId; // 订单系统中为 订单ID，商品系统中为 商品ID 
    
        private String operationDesc; //  单步操作描述
    
        private String operationData1; //  
    
        private String operationData2; //  
    
        private String operationData3; //  
    
        private String operationData4; //  
    
        private String operationData5; //  
    
        private String operationData6; //  
    
        private java.sql.Timestamp creationDate; //  创建时间
    
        private String remark; //  描述
    
    
        public Long getId(){
            return this.id;
        }
        public void setId(Long id){
            this.id = id;
        }
    
        public String getSerialNo(){
            return this.serialNo;
        }
        public void setSerialNo(String serialNo){
            this.serialNo = serialNo;
        }
    
        public Long getSourceId(){
            return this.sourceId;
        }
        public void setSourceId(Long sourceId){
            this.sourceId = sourceId;
        }
    
        public String getOperationDesc(){
            return this.operationDesc;
        }
        public void setOperationDesc(String operationDesc){
            this.operationDesc = operationDesc;
        }
    
        public String getOperationData1(){
            return this.operationData1;
        }
        public void setOperationData1(String operationData1){
            this.operationData1 = operationData1;
        }
    
        public String getOperationData2(){
            return this.operationData2;
        }
        public void setOperationData2(String operationData2){
            this.operationData2 = operationData2;
        }
    
        public String getOperationData3(){
            return this.operationData3;
        }
        public void setOperationData3(String operationData3){
            this.operationData3 = operationData3;
        }
    
        public String getOperationData4(){
            return this.operationData4;
        }
        public void setOperationData4(String operationData4){
            this.operationData4 = operationData4;
        }
    
        public String getOperationData5(){
            return this.operationData5;
        }
        public void setOperationData5(String operationData5){
            this.operationData5 = operationData5;
        }
    
        public String getOperationData6(){
            return this.operationData6;
        }
        public void setOperationData6(String operationData6){
            this.operationData6 = operationData6;
        }
    
        public java.sql.Timestamp getCreationDate(){
            return this.creationDate;
        }
        public void setCreationDate(java.sql.Timestamp creationDate){
            this.creationDate = creationDate;
        }
    
        public String getRemark(){
            return this.remark;
        }
        public void setRemark(String remark){
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
        return "OperationDetailLog [id=" + id + ", serialNo=" + serialNo
                + ", sourceId=" + sourceId + ", operationDesc=" + operationDesc
                + ", operationData1=" + operationData1 + ", operationData2="
                + operationData2 + ", operationData3=" + operationData3
                + ", operationData4=" + operationData4 + ", operationData5="
                + operationData5 + ", operationData6=" + operationData6
                + ", creationDate=" + creationDate + ", remark=" + remark + "]";
    }
}