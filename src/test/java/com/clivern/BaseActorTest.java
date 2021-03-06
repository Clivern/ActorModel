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

import akka.actor.testkit.typed.javadsl.TestKitJunitResource;
import org.junit.ClassRule;
import org.junit.Test;

/** BaseActorTest Class */
public class BaseActorTest {

    @ClassRule public static final TestKitJunitResource testKit = new TestKitJunitResource();
    //#definition

    //#test
    @Test
    public void testCase() {
        // TestProbe<Greeter.Greeted> testProbe = testKit.createTestProbe();
        // ActorRef<Greeter.Greet> underTest = testKit.spawn(Greeter.create(), "greeter");
        // underTest.tell(new Greeter.Greet("Charles", testProbe.getRef()));
        // testProbe.expectMessage(new Greeter.Greeted("Charles", underTest));
    }
    //#test
}
