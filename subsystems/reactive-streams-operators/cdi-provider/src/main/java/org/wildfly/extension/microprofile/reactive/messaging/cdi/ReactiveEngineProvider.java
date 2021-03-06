/*
 *  Copyright 2020 Red Hat, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.wildfly.extension.microprofile.reactive.messaging.cdi;

import java.util.Iterator;
import java.util.ServiceLoader;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.eclipse.microprofile.reactive.streams.operators.spi.ReactiveStreamsEngine;

/**
 * @author <a href="mailto:kabir.khan@jboss.com">Kabir Khan</a>
 */
public class ReactiveEngineProvider {

    /**
     * @return the reactive stream engine. It uses {@link ServiceLoader#load(Class)} to find an implementation from the
     *         Classpath.
     * @throws IllegalStateException if no implementations are found.
     */
    @Produces
    @ApplicationScoped
    public ReactiveStreamsEngine getEngine() {
        Iterator<ReactiveStreamsEngine> iterator = ServiceLoader.load(ReactiveStreamsEngine.class).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        throw new IllegalStateException("No implementation of the "
                + ReactiveStreamsEngine.class.getName() + " found in the Classpath");
    }

}
