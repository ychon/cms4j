package cn.im47.cms.common.entity.category;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 文章显示类型枚举
 * <p/>
 * User: baitao.jibt@gmail.com
 * Date: 12-3-23
 * Time: 下午00:12
 */
public enum ShowTypeEnum {

    NONE("NONE", "无"), LINK("LINK", "外链"),

    LIST("LIST", "列表"), DIGEST("DIGEST", "摘要"), FULL("FULL", "全屏"),

    CONTENT("CONTENT", "内容"), GALLERY("GALLERY", "相册"),

    COURSE("COURSE", "课程");

    private static Map<String, ShowTypeEnum> valueMap = Maps.newHashMap();

    private String value;
    private String displayName;

    static {
        for (ShowTypeEnum showType : ShowTypeEnum.values()) {
            valueMap.put(showType.value, showType);
        }
    }

    ShowTypeEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public static ShowTypeEnum parse(String value) {
        return valueMap.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }
}