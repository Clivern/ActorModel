/*
 * Copyright (C) 2020 Clivern <https://clivern.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.clivern.child;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import com.clivern.service.*;
import com.google.inject.Injector;

public class ChildActorE extends AbstractBehavior<ChildActorE.Payload> {

    private Injector injector;

    public static class Payload {
        public final String message;

        public Payload(String message) {
            this.message = message;
        }
    }

    public static Behavior<Payload> create(Injector injector) {
        return Behaviors.setup(context -> new ChildActorE(context, injector));
    }

    private ChildActorE(ActorContext<Payload> context, Injector injector) {
        super(context);
        this.injector = injector;

        //#create-actors

        //#create-actors
    }

    @Override
    public Receive<Payload> createReceive() {
        return newReceiveBuilder().onMessage(Payload.class, this::onMessage).build();
    }

    private Behavior<Payload> onMessage(Payload payload) {
        getContext().getLog().info("Received Message -> {}", payload.message);
        getContext()
                .getLog()
                .info("Service DI -> {}", this.injector.getInstance(ServiceA.class).getSomething());
        return this;
    }
}
