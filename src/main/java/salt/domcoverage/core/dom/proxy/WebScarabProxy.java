package salt.domcoverage.core.dom.proxy;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.owasp.webscarab.model.Preferences;
import org.owasp.webscarab.model.StoreException;
import org.owasp.webscarab.plugin.Framework;
import org.owasp.webscarab.plugin.proxy.Proxy;
import org.owasp.webscarab.plugin.proxy.ProxyPlugin;

import salt.domcoverage.core.dom.proxy.FileInjectorProxyAddon.FileInjectionLocation;
import salt.domcoverage.core.dom.proxy.FileInjectorProxyAddon.FileInjectionType;
import salt.domcoverage.core.dom.proxy.FileInjectorProxyAddon.TargetNode;
import salt.domcoverage.core.utils.ConstantVars;

import com.crawljax.core.configuration.ProxyConfiguration;

public class WebScarabProxy {

	private static Proxy proxy;
	private static List<ProxyPlugin> plugins = new ArrayList<ProxyPlugin>();

	public static void configureRunProxy() {

		plugins = new ArrayList<ProxyPlugin>();
		jsAndcssCodeInject(ConstantVars.INJECT_ELEMENT_ACCESS_JS, ConstantVars.INJECT_ELEMENT_ACCESS_CSS);
		// try {
		// this.addPlugin(new ExternalJavaScriptFileInjectorProxyAddon(new URI("http://mutation-summary.googlecode.com/git/src/mutation-summary.js")));
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (URISyntaxException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// jsAndcssCodeInject(ConstantVars.CLICKABLE_DETECTOR_JS, ConstantVars.CLICKABLE_DETECTOR_CSS);

		// jsAndcssCodeInject(ConstantVars.VISIBLE_ELEMENT_DETECTOR_JS, ConstantVars.VISIBLE_ELEMENT_DETECTOR_CSS);
		// (ALL) jsAndcssCodeInject(ConstantVars.JS_CODE_INJECT, ConstantVars.CSS_CODE_INJECT);

		// Configure the proxy to use the port 8084 (you can change this of
		// course)
		ProxyConfiguration proxyConfiguration = ProxyConfiguration.manualProxyOn(ConstantVars.PROXY_IP, ConstantVars.PROXY_PORT);

		startProxy(proxyConfiguration);
	}

	private static void jsAndcssCodeInject(String js, String css) {
		// Provide the JS file to be inserted
		URL code2inject = WebScarabProxy.class.getClassLoader().getResource(js);

		addPlugin(new FileInjectorProxyAddon(code2inject, TargetNode.HEAD, FileInjectionLocation.FIRST_ITEM, FileInjectionType.JAVASCRIPT));

		// add the CSS style
		URL cssFile = WebScarabProxy.class.getClassLoader().getResource(css);
		addPlugin(new FileInjectorProxyAddon(cssFile, TargetNode.HEAD, FileInjectionLocation.LAST_ITEM, FileInjectionType.CSS));

	}

	public static void startProxy(ProxyConfiguration config) {
		Framework framework = new Framework();

		/* set listening port before creating the object to avoid warnings */
		Preferences.setPreference("Proxy.listeners", config.getHostname() + ":" + config.getPort());

		proxy = new Proxy(framework);

		/* add the plugins to the proxy */
		for (ProxyPlugin p : plugins) {
			proxy.addPlugin(p);
		}

		// framework.setSession("BlackHole", null, "");
		try {
			framework.setSession("FileSystem", new File("conversationRecords"), "");
		} catch (StoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* start the proxy */
		proxy.run();

	}

	public static void addPlugin(ProxyPlugin plugin) {
		plugins.add(plugin);
	}
}
