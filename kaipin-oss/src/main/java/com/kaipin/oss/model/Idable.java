package com.kaipin.oss.model;

import java.io.Serializable;

 
public interface Idable<T extends Serializable> {
	public T getId();

	public void setId(T id);
}
