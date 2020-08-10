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

import akka.actor.typed.ActorSystem;
import com.clivern.config.*;
import com.clivern.service.*;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.lang.InterruptedException;
import java.util.concurrent.TimeUnit;

/** BaseActor Class */
public class BaseActor {

    public static void main(String[] args) throws InterruptedException {

        Injector injector = Guice.createInjector(new Container());

        //#actor-systems
        final ActorSystem<ActorA.Payload> actorA =
                ActorSystem.create(ActorA.create(injector), "actorA");

        final ActorSystem<ActorB.Payload> actorB =
                ActorSystem.create(ActorB.create(injector), "actorB");

        final ActorSystem<ActorC.Payload> actorC =
                ActorSystem.create(ActorC.create(injector), "actorC");
        //#actor-systems

        for (int i = 0; i < 10; i++) {
            //#main-send-messages
            actorA.tell(new ActorA.Payload(String.format("%d. From Base to ActorA.", i)));
            TimeUnit.SECONDS.sleep(i);

            actorB.tell(new ActorB.Payload(String.format("%d. From Base to ActorB.", i)));
            TimeUnit.SECONDS.sleep(i);

            actorC.tell(new ActorC.Payload(String.format("%d. From Base to ActorC.", i)));
            TimeUnit.SECONDS.sleep(i);
            //#main-send-messages
        }
    }
}
