package de.jw.pde.getting.started.extensionpoint.internal;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ExtensionPointActivator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "de.jw.pde.getting.started.extensionpoint"; //$NON-NLS-1$
	public static final String EXTENSION_POINT_ID = PLUGIN_ID + ".sample";

	// The shared instance
	private static ExtensionPointActivator plugin;
	
	/**
	 * The constructor
	 */
	public ExtensionPointActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		context.registerService(CommandProvider.class.getName(), new ExtentensionPointCommandProvider(), null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ExtensionPointActivator getDefault() {
		return plugin;
	}

}
