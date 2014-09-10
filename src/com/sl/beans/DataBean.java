package com.sl.beans;

import java.util.ArrayList;
import java.util.List;

import com.sl.beans.responsebase.ResponseBeanBase;

public class DataBean extends ResponseBeanBase {
	public List<String> words = new ArrayList<String>();
	public String code = "";
}
