package com.just.soso.common;

import java.io.Serializable;

public class AjaxResult implements Serializable {

	private static final long serialVersionUID = -4536092594179835353L;
	
	public static final Integer AJAX_STATUS_CODE_SUCCESS = 0;
	public static final Integer AJAX_STATUS_CODE_WARN = 1;
	public static final Integer AJAX_STATUS_CODE_ERROR = 2;

	private Integer statusCode;
	private String message;

	public AjaxResult() {
		super();
	}

	public AjaxResult(Integer statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	// 操作成功
	public static AjaxResult success() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatusCode(AjaxResult.AJAX_STATUS_CODE_SUCCESS);
		ajaxResult.setMessage("操作成功");
		return ajaxResult;
	}

	// 操作失败
	public static AjaxResult error() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatusCode(AjaxResult.AJAX_STATUS_CODE_ERROR);
		return ajaxResult;
	}

	// 警告
	public static AjaxResult warn() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setStatusCode(AjaxResult.AJAX_STATUS_CODE_WARN);
		return ajaxResult;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
