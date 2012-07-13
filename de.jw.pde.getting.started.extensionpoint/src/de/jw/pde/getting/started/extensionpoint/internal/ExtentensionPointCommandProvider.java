package de.jw.pde.getting.started.extensionpoint.internal;

import static com.bosch.support.java.lang.Objects.castStringArgFun;
import static de.jw.pde.getting.started.extensionpoint.constant.GettingStartedExtensionPointConst.EXTENSION_POINT_OPTIONAL_ATTRIBUTE;
import static de.jw.pde.getting.started.extensionpoint.constant.GettingStartedExtensionPointConst.EXTENSTION_POINT_ATTRIBUTE_CLASS;
import static de.jw.pde.getting.started.extensionpoint.internal.ExtensionPointActivator.EXTENSION_POINT_ID;
import static de.jw.pde.getting.started.extensionpoint.internal.IGettingStarteds.setUpGettingStartedByPropertiesFileFun;

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

import com.bosch.support.org.eclipse.core.runtime.IConfigurationInstanceBuilder;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import de.jw.pde.getting.started.extensionpoint.api.IGettingStarted;
import de.jw.pde.getting.started.extensionpoint.constant.GettingStartedExtensionPointConst;

public class ExtentensionPointCommandProvider implements CommandProvider {

	private final class UntypedArgumentDoNothingFun<A, T> implements Function<Object, T> {
		private final Function<A, T> doNothing;

		private UntypedArgumentDoNothingFun(Function<A, T> doNothing) {
			this.doNothing = doNothing;
		}

		@Override
		public T apply(Object object) {
			@SuppressWarnings("unchecked")
			T resultFromTypedFun = doNothing.apply((A) object);
			return resultFromTypedFun;
		}
	}

	private final class TypedDoNothingFun<A> implements Function<A, A> {
		@Override
		public A apply(A string) {
			return string;
		}
	}

	public void _show(CommandInterpreter interpreter) throws CoreException, FileNotFoundException, IOException {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();

		IConfigurationElement[] extensionElements = extensionRegistry.getConfigurationElementsFor(ExtensionPointActivator.EXTENSION_POINT_ID);

		List<IGettingStarted> allGettingStarteds = Lists.transform(Arrays.asList(extensionElements),
				new ExtentensionPointCommandProvider_.InitGettingStartedFun(GettingStartedExtensionPointConst.EXTENSTION_POINT_ATTRIBUTE_CLASS,
						GettingStartedExtensionPointConst.EXTENSION_POINT_OPTIONAL_ATTRIBUTE));

		System.out.println(allGettingStarteds);

	}

	public void _show2(CommandInterpreter interpreter) {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IConfigurationElement[] configurationElementsFor = extensionRegistry.getConfigurationElementsFor(EXTENSION_POINT_ID);

		final IConfigurationInstanceBuilder<IGettingStarted> builder = IConfigurationInstanceBuilder.<IGettingStarted> create();

		builder//
				.newInstanceFun(EXTENSTION_POINT_ATTRIBUTE_CLASS)//
				.addOptionalStringSetter(EXTENSION_POINT_OPTIONAL_ATTRIBUTE, cast(setUpGettingStartedByPropertiesFileFun(builder)))//
		;

		List<IGettingStarted> allGettingStarteds = Lists.transform(Arrays.asList(configurationElementsFor), builder.asFunction());
		interpreter.println(allGettingStarteds);
	}

	/**
	 * Helper to cast f(A) -> R to f(Object) -> R. Implicit downcast will do.
	 * 
	 * @param toCastFun
	 *          f(A) -> R
	 * @return f(Object) -> R
	 */
	private Function<Object, IGettingStarted> cast(Function<String, IGettingStarted> toCastFun) {
		Function<Object, IGettingStarted> castStringArgFun = castStringArgFun(toCastFun);
		return castStringArgFun;
	}

	public void _cast(CommandInterpreter interpreter) {
		final Function<String, String> doNothing = new TypedDoNothingFun<String>();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Function<Object, String> castedDoNothing = new UntypedArgumentDoNothingFun(doNothing);
		interpreter.println(castedDoNothing.apply("doNoting"));
	}

	@Override
	public String getHelp() {
		return "---" + ExtentensionPointCommandProvider.class.getName() + " help: no help available. Ask Jan Winter.---\n";
	}

}
