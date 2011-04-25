package org.springframework.springfaces.mvc.internal;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.util.Assert;

public class DefaultDestinationRegistry implements DestinationRegistry {

	// FIXME possibly make these package scope and remove the interfaces

	private static final String CONTAINER_ATTRIBUTE = DefaultDestinationRegistry.class.getName() + ".CONTAINER";
	private static final String KEY_PREFIX = DefaultDestinationRegistry.class.getName() + ":";

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		Assert.state(context != null, "Unable to obtain the FacesContext");
		return context;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getContainer(boolean create) {
		FacesContext context = getContext();
		Map<String, Object> container = (Map<String, Object>) context.getAttributes().get(CONTAINER_ATTRIBUTE);
		if (container == null && create) {
			container = new HashMap<String, Object>();
			context.getAttributes().put(CONTAINER_ATTRIBUTE, container);
		}
		return container;
	}

	public String put(Object destination) {
		Assert.notNull(destination, "Destination must not be null");
		synchronized (this) {
			Map<String, Object> container = getContainer(true);
			String key = KEY_PREFIX + String.valueOf(container.size() + 1);
			container.put(key, destination);
			return key;
		}
	}

	public Object get(String key) {
		if (key.startsWith(KEY_PREFIX)) {
			synchronized (this) {
				Map<String, Object> container = getContainer(false);
				if (container != null) {
					return container.get(key);
				}
			}
		}
		return null;
	}
}