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

package uk.gov.hmrc.gform.sharedmodel.formtemplate.generators
import org.scalacheck.Gen
import uk.gov.hmrc.gform.sharedmodel.formtemplate._

trait FormComponentGen {
  def formComponentIdGen: Gen[FormComponentId] = PrimitiveGen.nonEmptyAlphaNumStrGen.map(FormComponentId(_))
  def labelGen: Gen[String] = PrimitiveGen.nonEmptyAlphaNumStrGen
  def helpTextGen: Gen[String] = PrimitiveGen.nonEmptyAlphaNumStrGen
  def shortNameGen: Gen[String] = PrimitiveGen.nonEmptyAlphaNumStrGen
  def errorMessageGen: Gen[String] = PrimitiveGen.nonEmptyAlphaNumStrGen

  def formComponentGen(maxDepth: Int = 3): Gen[FormComponent] =
    for {
      id                <- formComponentIdGen
      tpe               <- ComponentTypeGen.componentTypeGen(maxDepth)
      label             <- labelGen
      helpText          <- Gen.option(helpTextGen)
      shortName         <- Gen.option(shortNameGen)
      validIf           <- Gen.option(ValidIfGen.validIfGen)
      mandatory         <- PrimitiveGen.booleanGen
      editable          <- PrimitiveGen.booleanGen
      submissable       <- PrimitiveGen.booleanGen
      derived           <- PrimitiveGen.booleanGen
      onlyShowOnSummary <- PrimitiveGen.booleanGen
      errorMessage      <- Gen.option(errorMessageGen)
      presentationHint  <- Gen.option(PrimitiveGen.zeroOrMoreGen(PresentationHintGen.presentationHintGen))
    } yield
      FormComponent(
        id,
        tpe,
        label,
        helpText,
        shortName,
        validIf,
        mandatory,
        editable,
        submissable,
        derived,
        onlyShowOnSummary,
        errorMessage,
        presentationHint)
}

object FormComponentGen extends FormComponentGen