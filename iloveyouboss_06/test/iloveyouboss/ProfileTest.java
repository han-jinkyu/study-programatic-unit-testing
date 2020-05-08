/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package iloveyouboss;

import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

   @Test
   public void matchAnswersFalseWhenMatchCriteriaNotMet() {
      /* 준비 */
      // 프로필을 작성한다
      Profile profile = new Profile("Bull Hockey, Inc.");

      // 질문을 작성하고 답변을 작성하여 프로필에 추가한다
      Question question = new BooleanQuestion(1, "Got bonuses?");
      Answer profileAnswer = new Answer(question, Bool.FALSE);
      profile.add(profileAnswer);

      // 답변에 대한 기준(Criterion)을 작성하고 Criteria 컬렉션에 추가한다
      Criteria criteria = new Criteria();
      Answer criteriaAnswer = new Answer(question, Bool.TRUE);
      Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
      criteria.add(criterion);

      /* 실행 */
      boolean matches = profile.matches(criteria);

      /* 단언 */
      assertFalse(matches);
   }

   @Test
   public void matchAnswersTrueForAnyDontCareCriteria() {
      /* 준비 */
      Profile profile = new Profile("Bull Hockey, Inc.");

      Question question = new BooleanQuestion(1, "Got milk?");
      Answer profileAnswer = new Answer(question, Bool.FALSE);
      profile.add(profileAnswer);

      Criteria criteria = new Criteria();
      Answer criteriaAnswer = new Answer(question, Bool.TRUE);
      Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
      criteria.add(criterion);

      /* 실행 */
      boolean matches = profile.matches(criteria);

      /* 단언 */
      assertTrue(matches);
   }
}
