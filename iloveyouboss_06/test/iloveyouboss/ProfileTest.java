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

   private Profile profile;
   private BooleanQuestion question;
   private Criteria criteria;

   @Before
   public void create() {
      profile = new Profile("Bull Hockey, Inc.");
      question = new BooleanQuestion(1, "Got bonuses?");
      criteria = new Criteria();
   }

   @Test
   public void matchAnswersFalseWhenMatchCriteriaNotMet() {
      /* 준비 */
      // 질문을 작성하고 답변을 작성하여 프로필에 추가한다
      profile.add(new Answer(question, Bool.FALSE));

      // 답변에 대한 기준(Criterion)을 작성하고 Criteria 컬렉션에 추가한다
      Criterion criterion = new Criterion(
              new Answer(question, Bool.TRUE),
              Weight.MustMatch);
      criteria.add(criterion);

      /* 실행 */
      boolean matches = profile.matches(criteria);

      /* 단언 */
      assertFalse(matches);
   }

   @Test
   public void matchAnswersTrueForAnyDontCareCriteria() {
      /* 준비 */
      profile.add(new Answer(question, Bool.FALSE));

      Criterion criterion = new Criterion(
              new Answer(question, Bool.TRUE),
              Weight.DontCare);
      criteria.add(criterion);

      /* 실행 */
      boolean matches = profile.matches(criteria);

      /* 단언 */
      assertTrue(matches);
   }
}
