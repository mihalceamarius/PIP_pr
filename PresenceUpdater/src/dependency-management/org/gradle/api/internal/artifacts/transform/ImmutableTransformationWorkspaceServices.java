/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.internal.artifacts.transform;

import com.google.common.collect.ImmutableList;
import org.gradle.cache.Cache;
import org.gradle.cache.CacheBuilder;
import org.gradle.internal.Try;
import org.gradle.internal.execution.UnitOfWork;
import org.gradle.internal.execution.history.ExecutionHistoryStore;
import org.gradle.internal.execution.workspace.impl.DefaultImmutableWorkspaceProvider;
import org.gradle.internal.file.FileAccessTimeJournal;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Closeable;
import java.io.File;

@NotThreadSafe
public class ImmutableTransformationWorkspaceServices implements TransformationWorkspaceServices, Closeable {
    private final Cache<UnitOfWork.Identity, Try<ImmutableList<File>>> identityCache;
    private final DefaultImmutableWorkspaceProvider workspaceProvider;

    public ImmutableTransformationWorkspaceServices(
        CacheBuilder cacheBuilder,
        FileAccessTimeJournal fileAccessTimeJournal,
        ExecutionHistoryStore executionHistoryStore,
        Cache<UnitOfWork.Identity, Try<ImmutableList<File>>> identityCache
    ) {
        this.workspaceProvider = DefaultImmutableWorkspaceProvider.withExternalHistory(cacheBuilder, fileAccessTimeJournal, executionHistoryStore);
        this.identityCache = identityCache;
    }

    @Override
    public DefaultImmutableWorkspaceProvider getWorkspaceProvider() {
        return workspaceProvider;
    }

    @Override
    public Cache<UnitOfWork.Identity, Try<ImmutableList<File>>> getIdentityCache() {
        return identityCache;
    }

    @Override
    public void close() {
        workspaceProvider.close();
    }
}
