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

package com.sl.beans.requestbase;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @ClassName RequestBase.java
 * @Description ӰԺģ��������ࡣÿһ��request�����Ӧһ���ӿڡ� Ŀ���Ƕ�ÿһ��������г���ʹ��������������߼��ϵ�
 *              ����ϣ�ͬʱ����ά��
 * @author Stephen Lee
 * @date 2014��06��17��
 */
public class RequestBeanBase {

	/** ��ֵ���ͺ��Ա�ǩ */
	protected int mNumTypeIgnoreTag;

	/**
	 * Ĭ�Ϲ���
	 */
	protected RequestBeanBase() {
		setNumTypeIgnore();
	}

	/**
	 * ����������ֵ���͵ĳ�Ա��ֵΪ��ǰʱ�����
	 */
	protected void setNumTypeIgnore() {
		mNumTypeIgnoreTag = (int) System.currentTimeMillis();
		setAllNumTypeIgnore();
	}

	/**
	 * Ϊ[��ֵ]���͵ĳ�Ա��ֵ��ǰʱ�������
	 */
	protected void setAllNumTypeIgnore() {
		Field[] fields = this.getClass().getFields();
		// �ǿ�
		if (null != fields) {

			// ������ǰ������������
			for (Field field : fields) {
				field.setAccessible(true); // �������private����

				Class<?> fieldType = field.getType();

				// ͨ��[��ֵ]����ֻ�ᱻ����Ϊint , double , long
				// byte��Ҫ���⴦��
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

	/**
	 * @Description ��ȡrequest��������Լ���,����netAction���������
	 * @return HashMap<String,String> ��ǰ��������Լ�ֵ����
	 */
	public HashMap<String, String> getNetActionParams() {
		Field[] fields = this.getClass().getFields();
		HashMap<String, String> paramsHashMap = new HashMap<String, String>(fields.length);

		// �ǿ�
		if (null != fields) {

			// ������ǰ������������
			for (Field field : fields) {
				field.setAccessible(true); // �������private����

				String fieldName = field.getName();
				Object fieldValue = null;

				try {
					fieldValue = field.get(this);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}

				// �������ֵ�����͵ĳ�ԱֵΪ��ǰʱ����룬�����
				if (fieldValue!=null && fieldValue.equals(mNumTypeIgnoreTag)) {
					continue;
				}

				// ֵ�ǿ�
				if (fieldValue != null) {
					paramsHashMap.put(fieldName, fieldValue.toString());
				}
			}
		}

		return paramsHashMap;
	}
}
