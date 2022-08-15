package com.wh.file.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 14:53 2022/2/16
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    private static final String NULLSTR = "";
    private static final char SEPARATOR = '_';
    private static final String START = "*";

    public StringUtils() {
    }

    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || objects.length == 0;
    }

    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    public static String substring(String str, int start) {
        if (str == null) {
            return "";
        } else {
            if (start < 0) {
                start += str.length();
            }

            if (start < 0) {
                start = 0;
            }

            return start > str.length() ? "" : str.substring(start);
        }
    }

    public static String substring(String str, int start, int end) {
        if (str == null) {
            return "";
        } else {
            if (end < 0) {
                end += str.length();
            }

            if (start < 0) {
                start += str.length();
            }

            if (end > str.length()) {
                end = str.length();
            }

            if (start > end) {
                return "";
            } else {
                if (start < 0) {
                    start = 0;
                }

                if (end < 0) {
                    end = 0;
                }

                return str.substring(start, end);
            }
        }
    }

    public static String format(String template, Object... params) {
        return !isEmpty(params) && !isEmpty(template) ? StrFormatter.format(template, params) : template;
    }

    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            boolean preCharIsUpperCase = true;
            boolean curreCharIsUpperCase = true;
            boolean nexteCharIsUpperCase = true;

            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if (i > 0) {
                    preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
                } else {
                    preCharIsUpperCase = false;
                }

                curreCharIsUpperCase = Character.isUpperCase(c);
                if (i < str.length() - 1) {
                    nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
                }

                if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                    sb.append('_');
                } else if (i != 0 && !preCharIsUpperCase && curreCharIsUpperCase) {
                    sb.append('_');
                }

                sb.append(Character.toLowerCase(c));
            }

            return sb.toString();
        }
    }

    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            String[] var2 = strs;
            int var3 = strs.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String s = var2[var4];
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && !name.isEmpty()) {
            if (!name.contains("_")) {
                return name.substring(0, 1).toUpperCase() + name.substring(1);
            } else {
                String[] camels = name.split("_");
                String[] var3 = camels;
                int var4 = camels.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String camel = var3[var5];
                    if (!camel.isEmpty()) {
                        result.append(camel.substring(0, 1).toUpperCase());
                        result.append(camel.substring(1).toLowerCase());
                    }
                }

                return result.toString();
            }
        } else {
            return "";
        }
    }

    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        } else {
            s = s.toLowerCase();
            StringBuilder sb = new StringBuilder(s.length());
            boolean upperCase = false;

            for(int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (c == '_') {
                    upperCase = true;
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

    public static boolean matches(String str, List<String> strs) {
        if (!isEmpty(str) && !isEmpty((Collection)strs)) {
            Iterator var2 = strs.iterator();

            String testStr;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                testStr = (String)var2.next();
            } while(!matches(str, testStr));

            return true;
        } else {
            return false;
        }
    }

    public static boolean matches(String str, String... strs) {
        if (!isEmpty(str) && !isEmpty((Object[])strs)) {
            String[] var2 = strs;
            int var3 = strs.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String testStr = var2[var4];
                if (matches(str, testStr)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }
//
//    public static boolean matches(String str, String pattern) {
//        if (!isEmpty(pattern) && !isEmpty(str)) {
//            pattern = pattern.replaceAll("\\s*", "");
//            int beginOffset = 0;
//            int formerStarOffset = true;
//            int latterStarOffset = true;
//            String remainingURI = str;
//            String prefixPattern = "";
//            String suffixPattern = "";
//            boolean result = false;
//
//            do {
//                int formerStarOffset = indexOf(pattern, "*", beginOffset);
//                prefixPattern = substring(pattern, beginOffset, formerStarOffset > -1 ? formerStarOffset : pattern.length());
//                result = remainingURI.contains(prefixPattern);
//                if (formerStarOffset == -1) {
//                    return result;
//                }
//
//                if (!result) {
//                    return false;
//                }
//
//                if (!isEmpty(prefixPattern)) {
//                    remainingURI = substringAfter(str, prefixPattern);
//                }
//
//                int latterStarOffset = indexOf(pattern, "*", formerStarOffset + 1);
//                suffixPattern = substring(pattern, formerStarOffset + 1, latterStarOffset > -1 ? latterStarOffset : pattern.length());
//                result = remainingURI.contains(suffixPattern);
//                if (!result) {
//                    return false;
//                }
//
//                if (!isEmpty(suffixPattern)) {
//                    remainingURI = substringAfter(str, suffixPattern);
//                }
//
//                beginOffset = latterStarOffset + 1;
//            } while(!isEmpty(suffixPattern) && !isEmpty(remainingURI));
//
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static <T> T cast(Object obj) {
//        return obj;
//    }
}
