package de.jw.support.eclipse.extensionpoint.sample;

import static com.bosch.support.java.lang.Objects.castIntegerArgFun;
import static com.bosch.support.java.lang.Objects.castStringArgFun;
import static com.bosch.support.org.eclipse.core.runtime.IConfigurationElements.getAttribute;
import static com.bosch.support.org.eclipse.core.runtime.IConfigurationElements.getOptionalAttributeFun;
import static de.jw.support.eclipse.extensionpoint.sample.SampleObject.setIntegerValueFun;
import static de.jw.support.eclipse.extensionpoint.sample.SampleObject.setValueFun;

import org.eclipse.core.runtime.IConfigurationElement;

import com.bosch.support.com.google.common.base.Optional_;
import com.bosch.support.org.eclipse.core.runtime.IConfigurationInstanceBuilder;
import com.google.common.base.Function;
import com.google.common.base.Optional;


public class Sample {

	public static void main(String[] args) {
		IConfigurationInstanceBuilder<SampleObject> builder = new IConfigurationInstanceBuilder<SampleObject>();

		final Function<IConfigurationElement, Optional<String>> optionalAttribute2 = getOptionalAttributeFun("integer-attribute-id");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Function<IConfigurationElement, Optional<Object>> optionalAttribute = new Optional_.CastOptionalReturnValue(optionalAttribute2);

		builder
				//
				.newInstanceFun("class-attirbute-id")
				//
				.addSetter("string-attribute-id", castStringArgFun(setValueFun(builder)))
				.addSetter(getAttribute("integer-attribute-id"), castIntegerArgFun(setIntegerValueFun(builder)))
				.addOptionalSetter(optionalAttribute, castIntegerArgFun(setIntegerValueFun(builder)));
		;

		SampleObject build = builder.build(null);
		
		System.out.println(build);
	}
}
