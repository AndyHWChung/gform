/*
 * Copyright 2019 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.gform.mongo

import play.modules.reactivemongo.ReactiveMongoComponentImpl
import reactivemongo.api.DefaultDB
import uk.gov.hmrc.gform.playcomponents.PlayComponents

class MongoModule(playComponents: PlayComponents) {

  val reactiveMongoComponent: ReactiveMongoComponentImpl =
    new ReactiveMongoComponentImpl(
      playComponents.context.initialConfiguration,
      playComponents.context.environment,
      playComponents.context.lifecycle)
  val mongo: () => DefaultDB = reactiveMongoComponent.mongoConnector.db

}
