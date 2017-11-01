/**
 * 
 */
package com.cc4102.textCleaner;

/**
 * @author Lucas Puebla Silva
 *
 */
public class TextCleaner {

  /**
   * @param text
   * @return
   */
  public String lowercase(String text) {
    return text.toLowerCase();
  }

  /**
   * @param text
   * @return
   */
  public String removePunctuation(String text) {
    String tmp = "";
    
    for(char c : text.toCharArray()) {
      // estoy dejando solamente las letras minusculas y el espacio!
      if(c == 32 || c >= 97 && c <= 122) tmp += c;
    }
    
    return tmp;
  }

  /**
   * @param elem
   * @return
   */
  public String clean(String text) {
    StringBuilder sb = new StringBuilder();
    String tmp;
    
    for(String word : text.split(" ")) {
      tmp = this.lowercase(word);
      tmp = this.replaceAccents(tmp);
      tmp = this.stem(tmp);
      sb.append(this.removePunctuation(tmp));
    }
    
    return sb.toString();
  }

  /**
   * @param elem
   * @return
   */
  public String replaceAccents(String elem) {
    return elem;
  }

  /**
   * @param elem
   * @return
   */
  public String stem(String elem) {
    return elem;
  }
  
  

}
