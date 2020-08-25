/*
Jordan Wilson
wilsonjordan61@gmail.com
Enigma Machine
EnigmaInterface.java
 */


package sample;

public interface EnigmaInterface {
    //six methods total
    public void setMessage(String message);
    public void setMessage(String message, int key);
    public void setCodedMessage(String codedMessage, int key);
    public String getCodedMessage();
    public String getMessage();
    public int getKey();
}
