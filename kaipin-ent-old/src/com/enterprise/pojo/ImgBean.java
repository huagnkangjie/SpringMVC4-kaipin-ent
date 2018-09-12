package com.enterprise.pojo;


/**
 * 图片裁剪 前端获取裁剪数值
 * @author Mr-H
 *
 */
public class ImgBean {

	private String x; // x
	private String y; // y
	private String width; // 宽
	private String height; // 高
	private String rotate; // 缩放
	
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getRotate() {
		return rotate;
	}
	public void setRotate(String rotate) {
		this.rotate = rotate;
	}
}
