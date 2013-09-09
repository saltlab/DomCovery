package salt.domcoverage.core.dom.proxy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.owasp.webscarab.httpclient.HTTPClient;
import org.owasp.webscarab.model.Request;
import org.owasp.webscarab.model.Response;
import org.owasp.webscarab.plugin.proxy.ProxyPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.crawljax.util.DomUtils;

/**
 * This plugin intercepts the HTML code from the server and injects the contents of a file targetNode the document. It can inject the file at different locations in the HTML document.
 * 
 */
public class ExternalJavaScriptFileInjectorProxyAddon extends ProxyPlugin {

	private URI externalJSURI;

	/**
	 * 
	 * @param externalJSURI
	 *            The external file to inject.
	 * @throws FileNotFoundException
	 */
	public ExternalJavaScriptFileInjectorProxyAddon(URI externalJSURI) throws FileNotFoundException {
		this.externalJSURI = externalJSURI;
	}

	@Override
	public String getPluginName() {
		return this.getClass().getName();
	}

	@Override
	public HTTPClient getProxyPlugin(HTTPClient in) {
		return new Plugin(in);
	}

	/**
	 * The actual WebScarab plugin.
	 */
	private class Plugin implements HTTPClient {

		private HTTPClient client;

		/**
		 * Constructor.
		 * 
		 * @param in
		 *            HTTPClient
		 */
		public Plugin(HTTPClient in) {
			this.client = in;
		}

		/**
		 * This plugin only handles the response: If the response contains HTML and is a complete document, it adds the content to it.
		 * 
		 * @param request
		 *            The incoming request.
		 * @throws IOException
		 *             On read write error.
		 * @return The new response.
		 */
		public Response fetchResponse(Request request) throws IOException {

			Response response = this.client.fetchResponse(request);

			if (response == null) {
				return null;
			}

			// parse the response body
			try {
				String contentType = response.getHeader("Content-Type");
				if (contentType == null) {
					return response;
				}

				if (contentType.contains("html")) {
					// parse the content
					String domStr = new String(response.getContent());
					Document dom = DomUtils.asDocument(domStr);
					injectIntoHeader(response, dom);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return response;
		}

		private void injectIntoHeader(Response response, Document dom) throws IOException {
			// try to get the target node tag

			NodeList nodes = dom.getElementsByTagName("head");
			if (nodes.getLength() == 0) {
				nodes = dom.getElementsByTagName("HEAD");
			}

			// no target node found
			if (nodes.getLength() == 0) {
				System.out.println("HTML received but" + " it is either invalid or a snippet");
			}
			// target node section found: inject the file
			else if (nodes.getLength() == 1) {
				Node parentNode = nodes.item(0);
				if (parentNode != null) {

					Element newElement = createJavaScriptNodeToInject(dom);

					if (newElement != null) {
						Node refChild = parentNode.getFirstChild();
						if (refChild == null) {
							parentNode.appendChild(newElement);
						} else {
							parentNode.insertBefore(newElement, refChild);
						}

					}

					response.setContent(DomUtils.getDocumentToByteArray(dom));

				}
			}
			// should not happen
			else {
				System.out.println("More than one head element in HTML");
			}
		}

		private Element createJavaScriptNodeToInject(Document dom) throws IOException {
			// create the node
			Element el = dom.createElement("script");
			el.setAttribute("type", "text/javascript");
			el.setAttribute("yyy", "injected");
			el.setAttribute("src", externalJSURI.toString());

			return el;
		}
	}

}
