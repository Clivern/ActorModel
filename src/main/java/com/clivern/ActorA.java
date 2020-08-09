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
package com.clivern;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import com.clivern.child.*;
import com.clivern.service.*;
import com.google.inject.Injector;

public class ActorA extends AbstractBehavior<ActorA.Payload> {

    private Injector injector;

    private final ActorRef<ChildActorE.Payload> childActorE;

    public static class Payload {
        public final String message;

        public Payload(String message) {
            this.message = message;
        }
    }

    public static Behavior<Payload> create(Injector injector) {
        return Behaviors.setup(context -> new ActorA(context, injector));
    }

    private ActorA(ActorContext<Payload> context, Injector injector) {
        super(context);
        this.injector = injector;

        //#create-sub-actors
        childActorE = context.spawn(ChildActorE.create(injector), "childActorE");
        //#create-sub-actors
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

        childActorE.tell(new ChildActorE.Payload("From ActorA to ChildActorE"));

        return this;
    }
}
