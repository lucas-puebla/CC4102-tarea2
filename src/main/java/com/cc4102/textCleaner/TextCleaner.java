/**
 * 
 */
package com.cc4102.textCleaner;

/**
 * @author Lucas Puebla Silva
 *
 * TextCleaner enables to clean a given text by applying different methods.
 * 
 * <p> 
 * Supported methods are: lowercase(), removePunctuation(), 
 * replaceAccents() and stem() 
 * </p>
 */
public class TextCleaner {

  // Seria mucho mas facil solamente procesar texto en ingles, en particular
  // por el tema de los acentos ademas de tener un stemmer que funcione para
  // 1 solo idioma (sino hay que reconocer el idioma y tener una funcion distinta)

  /**
   * Converts text to lower case.
   * @param text 
   * @return
   */
  public String lowercase(String text) {
    return text.toLowerCase();
  }

  /**
   * Removes every type of punctuation from text.
   * <p>
   * Currently removes everything except for lower case characters and space
   * character.
   * </p>
   * @param text
   * @return
   */
  public String removePunctuation(String text) {
    String tmp = "";

    for (char c : text.toCharArray()) {
      // estoy dejando solamente las letras minusculas y el espacio!
      // quizas deberia mejorar algo
      if (c == 32 || c >= 97 && c <= 122)
        tmp += c;
    }
    return tmp;
  }

  /**
   * Main method of TextCleaner, uses every other method to preprocess the text.
   * <p>
   * This implementation uses the StringBuilder class.
   * </p>
   * @param elem
   * @return
   */
  public String clean(String text) {
    StringBuilder sb = new StringBuilder();
    String tmp;
    String[] test;

    for (String word : text.split(" ")) {
      tmp = this.lowercase(word);
      tmp = this.replaceAccents(tmp);
      tmp = this.stem(tmp);
      sb.append(this.removePunctuation(tmp)+ " ");
    }
    String res = sb.toString();
    res = res.replaceAll("( +)"," ").trim();
    return res;
  }

  /**
   * Removes accented characters and replaces them for the non-accented version
   * of themselves.
   * 
   * <p>
   * This might not work very well with heavy accented languages such as
   *  french or spanish.
   * </p>
   * @param elem
   * @return
   */
  public String replaceAccents(String elem) {
    // TODO
    return elem;
  }

  /**
   * Stems the text, or in other words reduces words to their prefix root,
   * this enables a reduction of words.
   * <p>
   * This might be useful but can also be harmful since it reduces words such as
   * "lovable" "lover" "loving" and "love" to "lov".
   * <p/>
   * @param elem
   * @return
   */
  public String stem(String elem) {
    // deberiamos usar algo de este estilo:
    // https://stackoverflow.com/questions/9756653/porter-stemmer-code
    // TODO
    return elem;
  }



}
