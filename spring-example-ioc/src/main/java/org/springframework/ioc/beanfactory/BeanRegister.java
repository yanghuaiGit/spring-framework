package org.springframework.ioc.beanfactory;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.ioc.annotation.BeanScan;
import org.springframework.ioc.annotation.CanScan;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class BeanRegister implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

	private AtomicInteger number = new AtomicInteger(0);
	private ResourceLoader resourceLoader;

	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
		ClassPathScanningCandidateComponentProvider scanner = getScanner();
		scanner.setResourceLoader(this.resourceLoader);

		Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(BeanScan.class.getName());
		String packages = (String) annotationAttributes.get("packages");

		AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(
				CanScan.class);
		scanner.addIncludeFilter(annotationTypeFilter);

		Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(packages);

		for (BeanDefinition candidateComponent : candidateComponents) {
			if (candidateComponent instanceof AnnotatedBeanDefinition) {
				// verify annotated class is an interface
				AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
				AnnotationMetadata metadata = beanDefinition.getMetadata();
				registerFeignClient(registry, metadata);
			}
		}


	}

	private void registerFeignClient(BeanDefinitionRegistry registry,
									 AnnotationMetadata annotationMetadata) {
		String className = annotationMetadata.getClassName();
		BeanDefinitionBuilder definition = BeanDefinitionBuilder
				.genericBeanDefinition(BeanScanFactory.class);

		definition.addPropertyValue("type", className);
		definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);

		AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();

		beanDefinition.setPrimary(true);

		BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
	}

	protected ClassPathScanningCandidateComponentProvider getScanner() {
		return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
			@Override
			protected boolean isCandidateComponent(
					AnnotatedBeanDefinition beanDefinition) {
				boolean isCandidate = false;
				if (beanDefinition.getMetadata().isIndependent()) {
					if (!beanDefinition.getMetadata().isAnnotation()) {
						isCandidate = true;
					}
				}
				return isCandidate;
			}
		};
	}
}











