package com.niranjan.queueSender;

public class MyFile {
private String filename;
private String MIMEType;
private byte[] bytes;
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getMIMEType() {
	return MIMEType;
}
public void setMIMEType(String mIMEType) {
	MIMEType = mIMEType;
}
public byte[] getBytes() {
	return bytes;
}
public void setBytes(byte[] bytes) {
	this.bytes = bytes;
}
}
