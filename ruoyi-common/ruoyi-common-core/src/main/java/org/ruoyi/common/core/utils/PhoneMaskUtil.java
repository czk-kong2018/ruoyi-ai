package org.ruoyi.common.core.utils;

public class PhoneMaskUtil {

    private PhoneMaskUtil() {
    }

    /**
     * 手机号脱敏统一入口
     */
    public static String mask(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return phone;
        }

        phone = phone.trim();

        // 带国家码（如 +86-13812345678）
        if (phone.startsWith("+")) {
            return maskWithCountryCode(phone);
        }

        // 大陆手机号
        if (isMainland(phone)) {
            return maskFixed(phone, 3, 4);
        }

        // 香港
        if (isHongKong(phone)) {
            return maskFixed(phone, 1, 4);
        }

        // 澳门
        if (isMacau(phone)) {
            return maskFixed(phone, 1, 4);
        }

        // 台湾
        if (isTaiwan(phone)) {
            return maskFixed(phone, 2, 4);
        }

        // 兜底
        return maskGeneric(phone);
    }

    /* ===================== 识别规则 ===================== */

    // 大陆：11 位，1 开头
    private static boolean isMainland(String phone) {
        return phone.matches("1\\d{10}");
    }

    // 香港：8 位
    private static boolean isHongKong(String phone) {
        return phone.matches("\\d{8}");
    }

    // 澳门：8 位（6 开头常见）
    private static boolean isMacau(String phone) {
        return phone.matches("6\\d{7}");
    }

    // 台湾：09 开头 10 位
    private static boolean isTaiwan(String phone) {
        return phone.matches("09\\d{8}");
    }

    /* ===================== 脱敏实现 ===================== */

    /**
     * 固定前后保留
     */
    private static String maskFixed(String value, int prefix, int suffix) {
        int len = value.length();
        if (prefix + suffix >= len) {
            return value;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(value, 0, prefix);
        for (int i = 0; i < len - prefix - suffix; i++) {
            sb.append('*');
        }
        sb.append(value.substring(len - suffix));
        return sb.toString();
    }

    /**
     * 处理带国家码的号码
     * 示例：+86-13812345678 → +86-138****5678
     */
    private static String maskWithCountryCode(String phone) {
        // 找到最后一段数字
        int lastDash = phone.lastIndexOf('-');
        if (lastDash > 0) {
            String prefix = phone.substring(0, lastDash + 1);
            String number = phone.substring(lastDash + 1);
            return prefix + mask(number);
        }

        // 没有分隔符，直接兜底
        return maskGeneric(phone);
    }

    /**
     * 兜底脱敏
     */
    private static String maskGeneric(String value) {
        int len = value.length();
        if (len <= 4) {
            return "***";
        }
        return value.charAt(0)
                + "***"
                + value.charAt(len - 1);
    }
}
