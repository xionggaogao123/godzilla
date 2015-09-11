package cn.godzilla.model;

import java.io.Serializable;

public class RpcResult implements Serializable{
	
	public String rpcCode;
	public String rpcMsg;
	
	private RpcResult() {
		this("0", "success");
	}
	public RpcResult(String rpcCode, String rpcMsg) {
		this.rpcCode = rpcCode;
		this.rpcMsg = rpcMsg;
	}
	public String getRpcCode() {
		return rpcCode;
	}
	public void setRpcCode(String rpcCode) {
		this.rpcCode = rpcCode;
	}
	public String getRpcMsg() {
		return rpcMsg;
	}
	public void setRpcMsg(String rpcMsg) {
		this.rpcMsg = rpcMsg;
	}
	
	public static RpcResult create(String sort) {
		RpcResult rpcResult = null;
		switch(sort){
		case "SUCCESS":
			rpcResult = new RpcResult(SUCCESS,"success");
			break;
		case "FAILURE":
			rpcResult = new RpcResult(FAILURE,"failure");
			break;
		case "BUILDFAILURE":
			rpcResult = new RpcResult(BUILDFAILURE,"BUILDFAILURE");
		default:
			rpcResult = new RpcResult(CREATEFAIL, "RpcResult create failed");
		}
		return rpcResult;
	}
	
	private final static String SUCCESS = "0";
	private final static String FAILURE = "1";
	private final static String CREATEFAIL = "2";
	private final static String BUILDFAILURE = "3";
}
