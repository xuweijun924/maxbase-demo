package com.maxnerva.maxbase.demo.common.util;

import cn.hutool.core.lang.Validator;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * IP工具类
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public class IpUtil {

    private static final String UNKNOWN = "unknown";
    private static final List<String> LAN_IP_LIST = Arrays.asList("127.0.0.1", "0:0:0:0:0:0:0:1");

    private IpUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 取得本机IP地址
     *
     * @return
     * @deprecated 建议使用 Hutool 中的相关工具方法
     */
    @Deprecated
    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("未知IP", e);
        }
    }

    /**
     * 取得服务器网络地址
     *
     * @param www
     * @return
     * @deprecated 建议使用 Hutool 中的相关工具方法
     */
    @Deprecated
    public static String getServerIp(String www) {
        Validator.validateNotEmpty(www, "www不能为空");

        try {
            return InetAddress.getByName(www.trim()).getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("未知IP", e);
        }
    }

    /**
     * 获得访问者IP
     *
     * @param request
     * @return
     */
    public static String getVisitorIp(HttpServletRequest request) {
        Validator.validateNotNull(request, "request不能为空");

        String ip = "";
        try {
            // 根据请求头获取IP信息
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                // 如果为局域网，就根据网卡取对应机器配置的IP
                if (LAN_IP_LIST.contains(ip)) {
                    ip = InetAddress.getLocalHost().getHostAddress();
                }
            }

            // 检查是否有被反向代理，因为反向代理后会有多个IP值（多个IP按照','分割），第一个IP才是真实IP
            if (ip != null) {
                int index = ip.indexOf(",");
                if (index != -1) {
                    ip = ip.substring(0, index);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

}