package utils.xcommon.util.base.classfile;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.sun.tools.javac.util.List;

public enum EnumClass {
	// PERSONAL("个人"),
	// ENTERPRISE("企业");
	//
	PERSONAL("GEREN") {
		@Override
		public String getDesc() {
			return "This is:" + this.name();
		}
	},
	ENTERPRISE("企业") {
		@Override
		public String getDesc() {
			ArrayList<String> strList = Lists.newArrayList();
			strList.add("dddddd");
			strList.add("333333");
			strList.add("444444");
			strList.forEach((x) -> {
				System.out.println(x + "我的家");
			});
			return "That is:" + this.name();
		}
	};
	private String desc;

	EnumClass(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return StringUtils.EMPTY;
	}

	public static void main(String[] args) {
		System.out.println(EnumClass.ENTERPRISE.getDesc());
	}
}
