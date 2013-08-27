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

	private Proxy proxy;
	private List<ProxyPlugin> plugins = new ArrayList<ProxyPlugin>();

	public void configureRunProxy() {
		// Provide the JS file to be inserted
		URL code2inject = WebScarabProxy.class.getClassLoader().getResource(ConstantVars.INJECT_ELEMENT_ACCESS_JS);

		this.addPlugin(new FileInjectorProxyAddon(code2inject, TargetNode.HEAD, FileInjectionLocation.FIRST_ITEM, FileInjectionType.JAVASCRIPT));

		// add the CSS style
		URL cssFile = WebScarabProxy.class.getClassLoader().getResource(ConstantVars.INJECT_ELEMENT_ACCESS_CSS);
		this.addPlugin(new FileInjectorProxyAddon(cssFile, TargetNode.HEAD, FileInjectionLocation.LAST_ITEM, FileInjectionType.CSS));

		// Configure the proxy to use the port 8084 (you can change this of
		// course)
		ProxyConfiguration proxyConfiguration = ProxyConfiguration.manualProxyOn("127.0.0.1", 8888);

		this.startProxy(proxyConfiguration);
	}

	public void startProxy(ProxyConfiguration config) {
		Framework framework = new Framework();

		/* set listening port before creating the object to avoid warnings */
		Preferences.setPreference("Proxy.listeners", config.getHostname() + ":" + config.getPort());

		this.proxy = new Proxy(framework);

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
		this.proxy.run();

	}

	public void addPlugin(ProxyPlugin plugin) {
		plugins.add(plugin);
	}
}
