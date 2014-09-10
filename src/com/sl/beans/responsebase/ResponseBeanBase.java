package com.sl.beans.responsebase;

import com.google.gson.Gson;

public class ResponseBeanBase extends ResponseAdatper {
	
	@Override
	public void adapter() {
		super.adapter();
	}
	
	
	public static class ResponseBeanUtils {
		/**
		 * @Description java object to JSON string
		 * @param object
		 *            the java object
		 * @return
		 */
		public static String toJson(Object object) {
			Gson gson = new Gson();
			return gson.toJson(object);
		}

		/**
		 * @Description JSON string to java object
		 * @param jsonString
		 *            ths JSON string
		 * @param classOfT
		 *            target java object class type
		 * @return object target java object
		 */
		public static <T> T fromJson(String jsonString, Class<T> classOfT) {
			T result = null;
			Gson gson = new Gson();

			// �����JSON�ĵ���ʽ�����׳��쳣
			try {
				result = gson.fromJson(jsonString, classOfT);
			} catch (Exception exception)   // �����쳣��ֱ�ӷ���Null ,
											// �ⲿ����HTTP�ɹ������Ǵ˷�������null,����ж���JSON����ʧ��
			{
				return result;
			}

			return result;
		}
	}
}
