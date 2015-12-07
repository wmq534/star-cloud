package com.github.requestserialid;
public class MonitorReceiverLog implements Log{

	
		private Long id; //Pk  
	
		private String innerSerialId; //  内部流水号
	
	
		private String interfaceName; //  接口名称
	
		private Long cell; //  手机号
	
		private String sourceSystem; //  来源系统
	
		private String errorCode; //  错误码
	
		private String errorMsg; //  错误消息
	
		private Integer timeCost; //  调用耗时
	
		private String fullUrl; //  
	
	
		private String module; //  
	
		private String remoteIp; //  
	
		private String localIp; //  
	
		private String clientIp; //  
	
		private String serviceNo; //  部署机IP
	
		private java.sql.Timestamp invokeDate; //  调用时间
	
		private java.sql.Timestamp returnDate; //  返回时间
	
		private java.sql.Timestamp creationDate; //  记录创建时间
	
	
		public Long getId(){
			return this.id;
		}
		public void setId(Long id){
			this.id = id;
		}
	
		public String getInnerSerialId(){
			return this.innerSerialId;
		}
		public void setInnerSerialId(String innerSerialId){
			this.innerSerialId = innerSerialId;
		}
	
		public String getInterfaceName(){
			return this.interfaceName;
		}
		public void setInterfaceName(String interfaceName){
			this.interfaceName = interfaceName;
		}
	
		public String getSourceSystem(){
			return this.sourceSystem;
		}
		public void setSourceSystem(String sourceSystem){
			this.sourceSystem = sourceSystem;
		}
	
		public String getErrorCode(){
			return this.errorCode;
		}
		public void setErrorCode(String errorCode){
			this.errorCode = errorCode;
		}
	
		public String getErrorMsg(){
			return this.errorMsg;
		}
		public void setErrorMsg(String errorMsg){
			this.errorMsg = errorMsg;
		}
	
		public Integer getTimeCost(){
			return this.timeCost;
		}
		public void setTimeCost(Integer timeCost){
			this.timeCost = timeCost;
		}
	
		public String getFullUrl(){
			return this.fullUrl;
		}
		public void setFullUrl(String fullUrl){
			this.fullUrl = fullUrl;
		}
	
		public String getModule(){
			return this.module;
		}
		public void setModule(String module){
			this.module = module;
		}
	
		public String getRemoteIp(){
			return this.remoteIp;
		}
		public void setRemoteIp(String remoteIp){
			this.remoteIp = remoteIp;
		}
	
		public String getLocalIp(){
			return this.localIp;
		}
		public void setLocalIp(String localIp){
			this.localIp = localIp;
		}
	
		public String getClientIp(){
			return this.clientIp;
		}
		public void setClientIp(String clientIp){
			this.clientIp = clientIp;
		}
	
		public String getServiceNo(){
			return this.serviceNo;
		}
		public void setServiceNo(String serviceNo){
			this.serviceNo = serviceNo;
		}
	
		public java.sql.Timestamp getInvokeDate(){
			return this.invokeDate;
		}
		public void setInvokeDate(java.sql.Timestamp invokeDate){
			this.invokeDate = invokeDate;
		}
	
		public java.sql.Timestamp getReturnDate(){
			return this.returnDate;
		}
		public void setReturnDate(java.sql.Timestamp returnDate){
			this.returnDate = returnDate;
		}
	
		public java.sql.Timestamp getCreationDate(){
			return this.creationDate;
		}
		public void setCreationDate(java.sql.Timestamp creationDate){
			this.creationDate = creationDate;
		}
	
    	public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("MonitorReceiverLog [");
            sb.append("innerSerialId=" + innerSerialId);
            sb.append(", module=" + module);
            sb.append(", serviceNo=" + serviceNo);
            sb.append(", interfaceName=" + interfaceName);
            sb.append(", fullUrl=" + fullUrl + ", localIp=" + localIp);
            sb.append(", remoteIp=" + remoteIp + ", clientIp=" + clientIp);
            sb.append(", sourceSystem=" + sourceSystem);
            sb.append(", timeCost=" + timeCost + "ms");
            sb.append(", errorCode=" + errorCode + ", errorMsg=" + errorMsg);
            sb.append(", invokeDate=" + invokeDate + ", returnDate=" + returnDate + " ]");
            return sb.toString();
        }
      public Long getCell() {
        return cell;
      }
      public void setCell(Long cell) {
        this.cell = cell;
      }
}