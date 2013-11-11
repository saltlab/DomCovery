package salt.domcoverage.core.dom.proxy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.owasp.webscarab.httpclient.HTTPClient;
import org.owasp.webscarab.model.Request;
import org.owasp.webscarab.model.Response;
import org.owasp.webscarab.plugin.proxy.ProxyPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import salt.domcoverage.core.utils.Utils;

import com.crawljax.util.DomUtils;

/**
 * This plugin intercepts the HTML code from the server and injects the contents of a file targetNode the document. It can inject the file at different locations in the HTML document.
 * 
 */
public class FileInjectorProxyAddon extends ProxyPlugin {

	public enum FileInjectionLocation {
		FIRST_ITEM, LAST_ITEM
	}

	public enum TargetNode {
		HEAD, BODY
	}

	public enum FileInjectionType {
		JAVASCRIPT, CSS
	}

	private TargetNode targetNode;
	private FileInjectionType fileType;
	private FileInjectionLocation fileLocation;
	private URL fileUrl;

	/**
	 * Constructor with config parameter.
	 * 
	 * @param filename
	 *            The file to inject.
	 * @throws FileNotFoundException
	 */
	public FileInjectorProxyAddon(URL fileUrl, TargetNode into, FileInjectionLocation fileLocation, FileInjectionType fileType) {

		this.targetNode = into;
		this.fileLocation = fileLocation;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
	}

	@Override
	public String getPluginName() {
		return "FileInjectorPlugin";
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
				System.out.println("REQUESTTTTTTTTTTTTTTTTTTTT: " + request.getURL());
				if (request.getURL().toString().contains("?thisisafunctiontracingcall")) {
					String rawResponse = new String(request.getContent());
					System.out.println("rawResponse: " + Utils.printSubstring(rawResponse, 20));
					IndirectelementAccessData.addElements(rawResponse);
					return response;
				}

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
			String targetNodeName = targetNode.toString();
			NodeList nodes = dom.getElementsByTagName(targetNodeName.toLowerCase());
			if (nodes.getLength() == 0) {
				nodes = dom.getElementsByTagName(targetNodeName.toUpperCase());
			}

			// no target node found
			if (nodes.getLength() == 0) {
				System.out.println("HTML received but" + " it is either invalid or a snippet");
			}
			// target node section found: inject the file
			else if (nodes.getLength() == 1) {
				Node parentNode = nodes.item(0);
				if (parentNode != null) {

					Element newElement = null;
					switch (fileType) {
					case JAVASCRIPT:
						newElement = createJavaScriptNodeToInject(dom, fileUrl);
						break;

					case CSS:
						newElement = createCSSNodeToInject(dom, fileUrl);
						break;

					}

					if (newElement != null) {
						switch (fileLocation) {
						case FIRST_ITEM:
							Node refChild = parentNode.getFirstChild();
							if (refChild == null) {
								parentNode.appendChild(newElement);
							} else {
								parentNode.insertBefore(newElement, refChild);
							}
							break;

						case LAST_ITEM:
							parentNode.appendChild(newElement);
							break;
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

		private Element createCSSNodeToInject(Document dom, URL fileUrl) throws IOException {
			Element el = dom.createElement("style");
			el.setAttribute("xxx", "injected");

			String content = IOUtils.toString(fileUrl.openStream(), "UTF-8");
			el.appendChild(dom.createTextNode(content));
			return el;
		}

		private Element createJavaScriptNodeToInject(Document dom, URL fireUrl) throws IOException {
			// create the node
			Element el = dom.createElement("script");
			el.setAttribute("type", "text/javascript");
			el.setAttribute("xxx", "injected");

			String js = IOUtils.toString(fileUrl.openStream(), "UTF-8");
			// add the javascript to the node
			el.appendChild(dom.createTextNode(js));
			return el;
		}
	}

}
