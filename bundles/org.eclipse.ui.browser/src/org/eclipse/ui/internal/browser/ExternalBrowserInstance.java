/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.internal.browser;

import java.net.URL;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.AbstractWebBrowser;
/**
 * An instance of a running Web browser.
 */
public class ExternalBrowserInstance extends AbstractWebBrowser {
	protected IBrowserDescriptor browser;
	protected Process process;

	public ExternalBrowserInstance(String id, IBrowserDescriptor browser) {
		super(id);
		this.browser = browser;
	}

	public void openURL(URL url) throws PartInitException {
		String urlText = null;
		
		if (url != null)
			urlText = url.toExternalForm();

		// change spaces to "%20"
		if (urlText != null && !WebBrowserUtil.isWindows()) {
			int index = urlText.indexOf(" ");
			while (index >= 0) {
				urlText = urlText.substring(0, index) + "%20" + urlText.substring(index + 1);
				index = urlText.indexOf(" ");
			}
		}

		String location = browser.getLocation();
		String parameters = browser.getParameters();
		Trace.trace(Trace.FINEST, "Launching external Web browser: " + location + " - " + parameters + " - " + urlText);
		
		String params = parameters;
		if (params == null)
			params = "";
		
		if (urlText != null) {
			int urlIndex = params.indexOf(IBrowserDescriptor.URL_PARAMETER);
			if (urlIndex >= 0)
				params = params.substring(0, urlIndex) + " " + urlText + " " + params.substring(urlIndex + IBrowserDescriptor.URL_PARAMETER.length());
			else {
				if (!params.endsWith(" "))
					params += " ";
				params += urlText;
			}
		}
		
		try {
			Trace.trace(Trace.FINEST, "Launching " + location + " " + params);
			if (params == null || params.length() == 0)
				process = Runtime.getRuntime().exec(location);
			else
				process = Runtime.getRuntime().exec(location + " " + params);
		} catch (Exception e) {
			Trace.trace(Trace.SEVERE, "Could not launch external browser", e);
			WebBrowserUtil.openError(WebBrowserUIPlugin.getResource("%errorCouldNotLaunchWebBrowser", urlText));
		}
		Thread thread = new Thread() {
			public void run() {
				try {
					process.waitFor();
					DefaultBrowserSupport.getInstance().removeBrowser(ExternalBrowserInstance.this.getId());
				} catch (Exception e) {
					// ignore
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}
	
	public boolean close() {
		try {
			process.destroy();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}