package org.springframework.springfaces.mvc.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.HandlerExecutionChain;

/**
 * Strategy interface used by {@link FacesPostbackHandler} to obtain the handler that would have processed the request
 * if it were not a postback.
 * 
 * @see DefaultFacesConfig
 * @author Phillip Webb
 */
public interface FacesPostbackOrginalHandlerLocator {

	/**
	 * Return the handler that would have processed the request if it were not a postback.
	 * @param request the request that should be used to obtain the handler.
	 * @return the handler execution chain
	 * @throws Exception if the handler cannot be obtained
	 */
	HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}
