/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package wvlet.airframe

/**
  *
  */
class SessionBuilderTest extends AirframeSpec {
  val d = newSilentDesign

  "SessionBuilder" should {
    "create a named session" in {
      val session = d.newSessionBuilder
        .withName("MySession")
        .create
      session.name shouldBe "MySession"

      session.start

      session.shutdown
    }

    "create a session with custom event handler" in {
      var counter = 0
      val session = d.newSessionBuilder
        .withEventHandler(new LifeCycleEventHandler {
          override def beforeStart(lifeCycleManager: LifeCycleManager): Unit = {
            counter += 1
          }
        })
        .create

      session.start {
        counter shouldBe 1
      }
    }
  }
}
