package salt.domcoverage.core.dom.proxy;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import salt.domcoverage.core.utils.ConstantVars;

public class BrowserProfile {

	public static FirefoxProfile getConfigureProxyandgetProfile() {
		FirefoxProfile profile = new FirefoxProfile();
		// String lang = configuration.getBrowserConfig().getLangOrNull();
		// if (!Strings.isNullOrEmpty(lang)) {
		// profile.setPreference("intl.accept_languages", lang);
		// }
		salt.domcoverage.core.dom.proxy.WebScarabProxy.configureRunProxy();

		ConstantVars.JS_REWRITE_EXECUTED = false;
		profile.setPreference("network.proxy.http", "127.0.0.1");
		profile.setPreference("network.proxy.http_port", ConstantVars.PROXY_PORT);
		profile.setPreference("network.proxy.type", 1);
		/* use proxy for everything, including localhost */
		profile.setPreference("network.proxy.no_proxies_on", "");

		return profile;
	}

	public static FirefoxProfile getProfile() {
		FirefoxProfile profile = new FirefoxProfile();

		profile.setPreference("network.proxy.http", "localhost");
		profile.setPreference("network.proxy.http_port", 3128);
		profile.setPreference("network.proxy.type", 1);
		/* use proxy for everything, including localhost */
		profile.setPreference("network.proxy.no_proxies_on", "");

		return profile;
	}

	public static DesiredCapabilities getNodeCoverProfile() {
		Proxy proxy = new Proxy().setHttpProxy("localhost:3128");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);

		return cap;
	}

	public static DesiredCapabilities getCap() {
		Proxy proxy = new Proxy().setHttpProxy("localhost:3128");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PROXY, proxy);
		return cap;
	}
}
