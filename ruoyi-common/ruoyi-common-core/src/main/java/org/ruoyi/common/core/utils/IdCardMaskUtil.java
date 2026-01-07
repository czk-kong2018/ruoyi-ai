package org.ruoyi.common.core.utils;

public class IdCardMaskUtil {

    private IdCardMaskUtil() {
    }

    /**
     * 统一身份证 / 证件号脱敏入口
     */
    public static String mask(String id) {
        if (id == null || id.trim().isEmpty()) {
            return id;
        }

        id = id.trim();

        // 大陆身份证
        if (isMainland18(id)) {
            return maskFixed(id, 6, 4);
        }
        if (isMainland15(id)) {
            return maskFixed(id, 6, 3);
        }

        // 港澳台
        if (isHongKong(id)) {
            return maskKeepStructure(id, 1, 2);
        }
        if (isMacau(id)) {
            return maskKeepStructure(id, 0, 2);
        }
        if (isTaiwan(id)) {
            return maskKeepStructure(id, 1, 2);
        }

        // 兜底：未知证件，按通用规则
        return maskGeneric(id);
    }

    /* ===================== 大陆 ===================== */

    private static boolean isMainland18(String id) {
        return id.matches("\\d{17}[0-9Xx]");
    }

    private static boolean isMainland15(String id) {
        return id.matches("\\d{15}");
    }

    /* ===================== 港澳台 ===================== */

    // 香港：A123456(7)
    private static boolean isHongKong(String id) {
        return id.matches("[A-Z]\\d{6}\\(\\d\\)");
    }

    // 澳门：1234567(8)
    private static boolean isMacau(String id) {
        return id.matches("\\d{7}\\(\\d\\)");
    }

    // 台湾：A123456789
    private static boolean isTaiwan(String id) {
        return id.matches("[A-Z]\\d{9}");
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
     * 保留结构（字母 / 括号），只脱敏中间数字
     */
    private static String maskKeepStructure(String value, int keepPrefix, int keepSuffix) {
        char[] chars = value.toCharArray();
        int count = 0;

        // 统计可脱敏字符数
        for (char c : chars) {
            if (Character.isLetterOrDigit(c)) {
                count++;
            }
        }

        int masked = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isLetterOrDigit(c)) {
                if (masked >= keepPrefix && masked < count - keepSuffix) {
                    chars[i] = '*';
                }
                masked++;
            }
        }
        return new String(chars);
    }

    /**
     * 兜底脱敏（未知证件）
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
