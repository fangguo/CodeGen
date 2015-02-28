/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen.lang.models.implementation;

import com.speedment.codegen.lang.models.ClassOrInterface;
import com.speedment.codegen.lang.models.File;
import com.speedment.codegen.lang.models.Import;
import com.speedment.codegen.lang.models.Javadoc;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Emil Forslund
 */
public class FileImpl implements File {
	
	private String name;
	private Optional<Javadoc> doc;
	private final List<Import> imports;
	private final List<ClassOrInterface<?>> classes;
	
	public FileImpl(String name) {
		this.name	 = name;
		this.doc	 = Optional.empty();
		this.imports = new ArrayList<>();
		this.classes = new ArrayList<>();
	}
	
	protected FileImpl(FileImpl prototype) {
		this.name	 = prototype.name;
		this.doc	 = Copier.copy(prototype.doc);
		this.imports = Copier.copy(prototype.imports);
		this.classes = Copier.copy(prototype.classes, c -> c.copy());
	}

	@Override
	public File setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public File setJavadoc(Javadoc doc) {
		this.doc = Optional.of(doc);
		return this;
	}

	@Override
	public Optional<Javadoc> getJavadoc() {
		return doc;
	}

	@Override
	public List<Import> getImports() {
		return imports;
	}

	@Override
	public List<ClassOrInterface<?>> getClasses() {
		return classes;
	}

	@Override
	public FileImpl copy() {
		return new FileImpl(this);
	}
}