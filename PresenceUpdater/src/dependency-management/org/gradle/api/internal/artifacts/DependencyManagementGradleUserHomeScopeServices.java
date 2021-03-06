/*
 * Copyright 2016 the original author or authors.
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

package org.gradle.api.internal.artifacts;

import com.google.common.collect.ImmutableList;
import org.gradle.BuildAdapter;
import org.gradle.BuildResult;
import org.gradle.api.internal.DocumentationRegistry;
import org.gradle.api.internal.GradleInternal;
import org.gradle.api.internal.artifacts.ivyservice.ArtifactCachesProvider;
import org.gradle.api.internal.artifacts.ivyservice.DefaultArtifactCaches;
import org.gradle.api.internal.artifacts.transform.ImmutableTransformationWorkspaceServices;
import org.gradle.api.internal.cache.StringInterner;
import org.gradle.api.internal.changedetection.state.DefaultExecutionHistoryCacheAccess;
import org.gradle.cache.CacheBuilder;
import org.gradle.cache.CacheRepository;
import org.gradle.cache.ManualEvictionInMemoryCache;
import org.gradle.cache.internal.CacheScopeMapping;
import org.gradle.cache.internal.InMemoryCacheDecoratorFactory;
import org.gradle.cache.internal.UsedGradleVersions;
import org.gradle.initialization.RootBuildLifecycleListener;
import org.gradle.internal.Try;
import org.gradle.internal.event.ListenerManager;
import org.gradle.internal.execution.UnitOfWork;
import org.gradle.internal.execution.history.ExecutionHistoryCacheAccess;
import org.gradle.internal.execution.history.ExecutionHistoryStore;
import org.gradle.internal.execution.history.impl.DefaultExecutionHistoryStore;
import org.gradle.internal.file.FileAccessTimeJournal;
import org.gradle.internal.service.ServiceRegistry;

import java.io.File;

public class DependencyManagementGradleUserHomeScopeServices {

    DefaultArtifactCaches.WritableArtifactCacheLockingParameters createWritableArtifactCacheLockingParameters(FileAccessTimeJournal fileAccessTimeJournal, UsedGradleVersions usedGradleVersions) {
        return new DefaultArtifactCaches.WritableArtifactCacheLockingParameters() {
            @Override
            public FileAccessTimeJournal getFileAccessTimeJournal() {
                return fileAccessTimeJournal;
            }

            @Override
            public UsedGradleVersions getUsedGradleVersions() {
                return usedGradleVersions;
            }
        };
    }

    ArtifactCachesProvider createArtifactCaches(CacheScopeMapping cacheScopeMapping,
                                                CacheRepository cacheRepository,
                                                ServiceRegistry registry,
                                                ListenerManager listenerManager,
                                                DocumentationRegistry documentationRegistry) {
        DefaultArtifactCaches artifactCachesProvider = new DefaultArtifactCaches(cacheScopeMapping, cacheRepository, () -> registry.get(DefaultArtifactCaches.WritableArtifactCacheLockingParameters.class), documentationRegistry);
        listenerManager.addListener(new BuildAdapter() {
            @Override
            public void buildFinished(BuildResult result) {
                artifactCachesProvider.getWritableCacheLockingManager().useCache(() -> {
                    // forces cleanup even if cache wasn't used
                });
            }
        });
        return artifactCachesProvider;
    }

    ExecutionHistoryCacheAccess createExecutionHistoryCacheAccess(CacheRepository cacheRepository) {
        return new DefaultExecutionHistoryCacheAccess(null, cacheRepository);
    }

    ExecutionHistoryStore createExecutionHistoryStore(
        ExecutionHistoryCacheAccess executionHistoryCacheAccess,
        InMemoryCacheDecoratorFactory inMemoryCacheDecoratorFactory,
        StringInterner stringInterner
    ) {
        return new DefaultExecutionHistoryStore(
            executionHistoryCacheAccess,
            inMemoryCacheDecoratorFactory,
            stringInterner
        );
    }

    ImmutableTransformationWorkspaceServices createTransformerWorkspaceServices(
        ArtifactCachesProvider artifactCaches,
        CacheRepository cacheRepository,
        FileAccessTimeJournal fileAccessTimeJournal,
        ExecutionHistoryStore executionHistoryStore,
        ListenerManager listenerManager
    ) {
        ManualEvictionInMemoryCache<UnitOfWork.Identity, Try<ImmutableList<File>>> identityCache = new ManualEvictionInMemoryCache<>();
        listenerManager.addListener(new RootBuildLifecycleListener() {
            @Override
            public void afterStart(GradleInternal gradle) {
            }

            @Override
            public void beforeComplete(GradleInternal gradle) {
                identityCache.clear();
            }
        });
        return new ImmutableTransformationWorkspaceServices(
            cacheRepository
                .cache(artifactCaches.getWritableCacheMetadata().getTransformsStoreDirectory())
                .withCrossVersionCache(CacheBuilder.LockTarget.DefaultTarget)
                .withDisplayName("Artifact transforms cache"),
            fileAccessTimeJournal,
            executionHistoryStore,
            identityCache
        );
    }
}
