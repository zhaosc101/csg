package com.zsc.csg.web.common.exception;

public class CsgAppException extends RuntimeException {
	private static final long serialVersionUID = -5260479364532006348L;

	private int code;
	private String message;
	private String[] msgs;

	public CsgAppException() {
		this.code = 9999;
		this.message = "未知系统异常";
	};

	public CsgAppException(int code, String message, String... msgs) {
		this.setCode(code);
		this.setMessage(message);
		this.setMsgs(msgs);
	}

	public CsgAppException(Exception e, int code, String message) {
		if (e instanceof CsgAppException) {
			CsgAppException e1 = (CsgAppException) e;
			this.setCode(code);
			this.setMessage(e1.getMessage());
		} else {
			this.setCode(code);
			this.setMessage(message);
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message + getMsgs();
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMsgs() {

		String result = "#";
		if (msgs != null) {
			for (String s : msgs) {
				result += s + "#";
			}
		}

		result = result.substring(0, result.length() - 1);

		return result;
	}

	public void setMsgs(String[] msgs) {
		this.msgs = msgs;
	}
}
