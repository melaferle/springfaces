package org.springframework.springfaces.internal;

import javax.faces.render.ResponseStateManager;

import org.springframework.springfaces.render.RenderKitIdAware;
import org.springframework.springfaces.util.ResponseStateManagerWrapper;

public class SpringResponseStateManager extends ResponseStateManagerWrapper {

	private WrapperHandler<ResponseStateManager> wrapperHandler;

	public SpringResponseStateManager(final String renderKitId, ResponseStateManager delegate) {
		this.wrapperHandler = new WrapperHandler<ResponseStateManager>(ResponseStateManager.class, delegate) {
			protected void postProcessWrapper(ResponseStateManager wrapped) {
				if (wrapped instanceof RenderKitIdAware) {
					((RenderKitIdAware) wrapped).setRenderKitId(renderKitId);
				}
			};
		};
	}

	public ResponseStateManager getWrapped() {
		return wrapperHandler.getWrapped();
	}
}