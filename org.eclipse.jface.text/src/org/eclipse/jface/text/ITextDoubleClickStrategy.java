/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jface.text;



/**
 * A text double click strategy defines the reaction of a text viewer
 * to mouse double click events. For that the strategy must be installed
 * on the text viewer.<p>
 * Clients may implements this interface or use the standard implementation
 * <code>DefaultTextDoubleClickStrategy</code>.
 *
 * @see ITextViewer
 * @see org.eclipse.swt.events.MouseListener#mouseDoubleClick(MouseEvent)
 */
public interface ITextDoubleClickStrategy {

	/**
	 * The mouse has been double clicked on the given text viewer.
	 *
	 * @param viewer the viewer into which has been double clicked
	 */
	void doubleClicked(ITextViewer viewer);
}
