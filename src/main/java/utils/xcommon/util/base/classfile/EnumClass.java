package utils.xcommon.util.base.classfile;

public enum EnumClass {
    PERSONAL("个人"),
    ENTERPRISE("企业");

    private String desc;

    EnumClass(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
