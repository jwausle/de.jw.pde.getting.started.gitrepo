package de.jw.pde.getting.started.extensionpoint.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import com.google.common.collect.Lists;

import de.jw.pde.getting.started.extensionpoint.api.IGettingStarted;
import de.jw.pde.getting.started.extensionpoint.constant.GettingStartedExtensionPointConst;

public class ExtentensionPointCommandProvider implements CommandProvider {

	public void _show(CommandInterpreter interpreter) throws CoreException, FileNotFoundException, IOException {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

		IConfigurationElement[] extensionElements = extensionRegistry
				.getConfigurationElementsFor(ExtensionPointActivator.EXTENSION_POINT_ID);

		List<IGettingStarted> allGettingStarteds = Lists.transform(Arrays.asList(extensionElements),
				new ExtentensionPointCommandProvider_.InitGettingStartedFun(GettingStartedExtensionPointConst.EXTENSTION_POINT_ATTRIBUTE_CLASS,
						GettingStartedExtensionPointConst.EXTENSION_POINT_OPTIONAL_ATTRIBUTE));
		
		System.out.println(allGettingStarteds);
		
	}

	@Override
	public String getHelp() {
		return "---" + ExtentensionPointCommandProvider.class.getName()
				+ " help: no help available. Ask Jan Winter.---\n";
	}

}
