/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.calendar.service

import com.liferay.ant.arquillian.WebArchiveBuilder

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.arquillian.spock.ArquillianSputnik

import org.jboss.shrinkwrap.api.spec.WebArchive
import org.jboss.shrinkwrap.resolver.api.Resolvers
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem

import org.junit.runner.RunWith

import spock.lang.Specification;

/**
 * @author Miguel Pastor
 * @author Cristina González
 */
@RunWith(ArquillianSputnik.class)
public class CalendarLocalServiceTest extends Specification {

	@Deployment
	public static WebArchive getDeployment() {
		File[] groovyLib = (File[])(Resolvers.use(MavenResolverSystem.class).
			resolve("org.codehaus.groovy:groovy-all:2.0.1")).
			withoutTransitivity().as(File.class);

		WebArchive webArchive = WebArchiveBuilder.build();

		return webArchive.addAsLibrary(groovyLib[0]);
	}

	def "When there has not been added any calendar , the number of calendars should be 0"() {
		expect:
		CalendarLocalServiceUtil.getCalendarsCount() == 0;
	}

}