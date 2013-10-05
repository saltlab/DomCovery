package salt.domcoverage.core.dom.proxy;

import org.openqa.selenium.firefox.FirefoxProfile;

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
		profile.setPreference("network.proxy.http_port", 8884);
		profile.setPreference("network.proxy.type", 1);
		/* use proxy for everything, including localhost */
		profile.setPreference("network.proxy.no_proxies_on", "");

		return profile;
	}

	public static FirefoxProfile getProfile() {
		FirefoxProfile profile = new FirefoxProfile();

		profile.setPreference("network.proxy.http", "127.0.0.1");
		profile.setPreference("network.proxy.http_port", 3128);
		profile.setPreference("network.proxy.type", 1);
		/* use proxy for everything, including localhost */
		profile.setPreference("network.proxy.no_proxies_on", "");

		return profile;
	}

}
