package org.eclipse.ui.tests.zoom;
/**********************************************************************
Copyright (c) 2000, 2002 IBM Corp. and others.
All rights reserved.   This program and the accompanying materials
are made available under the terms of the Common Public License v0.5
which accompanies this distribution, and is available at
http://www.eclipse.org/legal/cpl-v05.html
**********************************************************************/
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * A test suite to test the zooming behavior of Eclipse.
 */
public class ZoomTestSuite extends TestSuite {
	/**
	 * Returns the suite.  This is required to
	 * use the JUnit Launcher.
	 */
	public static Test suite() {
		return new ZoomTestSuite();
	}
	
	/**
	 * Construct the test suite.
	 */
	public ZoomTestSuite() {
		addTest(new TestSuite(ActivateTest.class));
		addTest(new TestSuite(CloseEditorTest.class));
		addTest(new TestSuite(HideViewTest.class));
		addTest(new TestSuite(OpenEditorTest.class));
		addTest(new TestSuite(ShowViewTest.class));
	}
}
