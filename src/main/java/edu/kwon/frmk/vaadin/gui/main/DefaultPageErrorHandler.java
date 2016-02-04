package edu.kwon.frmk.vaadin.gui.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification.Type;

import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;

/**
 * Handling uncaught exceptions
 * @author eduseashell
 */
public class DefaultPageErrorHandler implements ErrorHandler {
	/** */
	private static final long serialVersionUID = 2356154168631007749L;
	
	private Logger logger = LoggerFactory.getLogger(DefaultPageErrorHandler.class);

	@Override
	public void error(ErrorEvent event) {
		logger.debug(">> Handling uncaught exceptions");
		String msg = "The cause of the unhandle exception is: ";
        logger.error(msg, event.getThrowable());
        
        String caption = "System Error!";
        String desc = "An error occures, please contact system admin";
        VaadinFactory.getNotification(caption, desc, Type.ERROR_MESSAGE)
        	.show(Page.getCurrent());
        
        // Do the default error handling (optional)
//        doDefault(event);
	}

}
