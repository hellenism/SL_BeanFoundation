/*
 *  Copyright 2014 Stephen Lee
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.sl.beans.core.requestbase;

import java.lang.reflect.Field;
import java.util.HashMap;

public class RequestBeanBase {

	protected int mNumTypeIgnoreTag;

	/**
	 */
	protected RequestBeanBase() {
		setNumTypeIgnore();
	}

	/**
	 */
	protected void setNumTypeIgnore() {
		mNumTypeIgnoreTag = (int) System.currentTimeMillis();
		setAllNumTypeIgnore();
	}

	/**
	 */
	protected void setAllNumTypeIgnore() {
		Field[] fields = this.getClass().getFields();
		if (null != fields) {

			for (Field field : fields) {
				field.setAccessible(true); 

				Class<?> fieldType = field.getType();

				if (fieldType == Integer.TYPE || fieldType == Long.TYPE || fieldType == Double.TYPE) {

					try {
						field.set(this, mNumTypeIgnoreTag);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public String getQueryString()
	{
		String andSign = "&";
		String equalSign = "=";
		StringBuilder sb = new StringBuilder();
		Field[] fields = this.getClass().getFields();
		if(null != fields)
		{
			
			for (Field field : fields) {
				field.setAccessible(true); 

				String fieldName = field.getName();
				Object fieldValue = null;

				try {
					fieldValue = field.get(this);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}

				if (fieldValue!=null && fieldValue.equals(mNumTypeIgnoreTag)) {
					continue;
				}

				if (fieldValue != null) {
					sb.append(fieldName).append(equalSign).append(fieldValue.toString()).append(andSign);
				}
			}
		}
		
		return sb.toString();
	}

	/**
	 */
	public HashMap<String, String> getHashMapParams() {
		Field[] fields = this.getClass().getFields();
		HashMap<String, String> paramsHashMap = new HashMap<String, String>(fields.length);
		if (null != fields) {

			for (Field field : fields) {
				field.setAccessible(true); 

				String fieldName = field.getName();
				Object fieldValue = null;

				try {
					fieldValue = field.get(this);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}

				if (fieldValue!=null && fieldValue.equals(mNumTypeIgnoreTag)) {
					continue;
				}

				if (fieldValue != null) {
					paramsHashMap.put(fieldName, fieldValue.toString());
				}
			}
		}

		return paramsHashMap;
	}
}
