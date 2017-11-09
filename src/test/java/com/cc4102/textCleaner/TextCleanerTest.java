/**
 * 
 */
package com.cc4102.textCleaner;

import static org.junit.Assert.*;

import com.cc4102.experiment.TextCleaner;
import org.junit.Test;

/**
 * @author Lucas Puebla Silva
 *
 */
public class TextCleanerTest {

  TextCleaner tc = new TextCleaner();

  @Test
  public void notNullTest() {
    assertNotNull(tc);
  }

  @Test
  public void turnLowercaseTest() {
    String elem = "HOLA";

    String actual = tc.lowercase(elem);

    assertEquals("When applying lowercase() to a String, it should return"
        + "a lowercase version of the same String", "hola", actual);
  }

  @Test
  public void turnLowercaseWithAccentsTest() {
    String elem = "ÁRMÏJÊÒ";

    String actual = tc.lowercase(elem);

    assertEquals("When applying lowercase() to a String, it should return"
        + "a lowercase version of the same String even when it has accents", "ármïjêò", actual);
  }

  @Test
  public void turnLowercaseWithLowerAndUpperCaseTest() {
    String elem = "HoLA AmiGOs";

    String actual = tc.lowercase(elem);

    assertEquals(
        "When applying lowercase() to a String, it should return"
            + "a lowercase version, even if there are already lowercase characters",
        "hola amigos", actual);
  }

  @Test
  public void turnLowercaseWithSymbols() {
    String elem = "!@#$%^&*()";

    String actual = tc.lowercase(elem);

    assertEquals("When applying lowercase() to a symbol, it should return" + " the same symol",
        "!@#$%^&*()", actual);
  }

  @Test
  public void replaceAccentedCharactersForNonAccentedCharactersTest() {
    String elem = "áàäâéèëêíìïîóòöôúùüû";

    String actual = tc.replaceAccents(elem);

    assertEquals("Accented characters should be replaced by their non-accented" + " equivalents",
        "aaaaeeeeiiiioooouuuu", actual);
  }

  @Test
  public void removePunctuationTest() {
    String elem = "hola amigos! que tal? yo estoy bien.";

    String actual = tc.removePunctuation(elem);

    assertEquals("Every punctuation should be removed", "hola amigos que tal yo estoy bien",
        actual);
  }
  
  @Test
  public void removeDuplicatedSpacesTest() {
    fail("not yet implemented");
  }

  @Test
  public void stemmerTest() {
    String elem = "amoroso amor amorosa";

    String actual = tc.stem(elem);

    assertEquals("Similar words should be stemmed to their prefix", "amor amor amor", actual);
  }

  @Test
  public void cleanTextTest() {
    String elem = "!Hola QUE tAL?? CÖmÒ Estás? %&))=\n||]{]]";
    String[] expected = {"hola", "que", "tal", "como", "estas"};

    String[] actual = tc.clean(elem).split(" ");

    // puede que el stemmer cambie el expected de esto!
    // seria algo del estilo:
    // "hol que tal com est"

    // es incluso posible que hacer un stemmer sea demasiado procesamiento
    for (int i = 0; i < expected.length; i++) {
      assertEquals("The text should be cleaned!", expected[i], actual[i]);
    }
  }

}
