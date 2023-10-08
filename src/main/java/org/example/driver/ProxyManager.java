package org.example.driver;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.proxy.CaptureType;

/**
 * Proxy manager is used for creating and using proxy in UI testing
 */
public class ProxyManager {
    /**
     * Private constructor
     */
    public ProxyManager() {
    }

    /**
     * ThreadLocal variable to deal with thread safety issues
     */
    private static final ThreadLocal<BrowserMobProxy> proxy = new ThreadLocal<>();

    /**
     * Getter for Proxy
     *
     * @return WebDriver instance
     */
    public static BrowserMobProxy getProxy() {
        return proxy.get();
    }

    /**
     * Setter for Proxy
     */
    public static void createProxy() {
        BrowserMobProxyServer proxyServer = new BrowserMobProxyServer();
        proxyServer.setTrustAllServers(true);
        proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_HEADERS);
        proxyServer.start(0);
        proxy.set(proxyServer);
    }

    /**
     * Unloads threadLocal variable
     */
    public static void unload() {
        proxy.remove();
    }
}
