/*
Jordan Wilson
wilsonjordan61@gmail.com
Enigma Machine
Enigma.java
 */


package sample;

import java.util.Random;

public class Enigma implements EnigmaInterface {

    private String message, codedMessage;
    private int key;
    Random rand;

    public Enigma(){
        key = 1;
        rand = new Random();
    }

    private void encode(){
        StringBuilder sb = new StringBuilder(message.length());
        //begin with message
        //start a for loop
        for(int i =0; i<message.length(); ++i){
            char c = message.charAt(i);
            //convert char to ascii
            int ascii = c;
            //add the key
            int code = ascii + key;
            //Check to see if value >126
            //if it is, wrap
            if(code>126){
                code = (key - (126 - ascii)) + 32;
            }
            //convert the number to char again.
            char x = (char) code;
            sb.append(x);
        }
        //Build a new codedMessage, by concatenating each char
        //Can use StringBuilder
        //end with codedMessage
        codedMessage = sb.toString();
    }

    private void decode(){
        StringBuilder sb = new StringBuilder(codedMessage.length());
        //start with codedMessage
        //start a for loop
        for(int i = 0; i < codedMessage.length(); ++i){
            //convert char to ascii
            char c = codedMessage.charAt(i);
            int ascii = c;
            //subtract the key
            int decode = ascii - key;
            //check to see if value <32
            //if it is, wrap around
            if(decode<32){
                decode = 126 - (key - (ascii - 32));
            }
            //convert back to a char
            char x = (char) decode;
            sb.append(x);
        }
        //build a new message
        message = sb.toString();
    }



    @Override
    public void setMessage(String message) {
        this.message = message;
        //key = random key between 1 and 50
        key = rand.nextInt(49)+1;
        encode();
    }

    @Override
    public void setMessage(String message, int key) {
        this.message = message;
        //check if key is in the range of 1-50. if it is,
        if(key>0&&key<51){
            //assign to this.key
            this.key = key;
        }else {
            this.key = 1;
        }
        encode();
    }

    @Override
    public void setCodedMessage(String codedMessage, int key) {
        this.codedMessage = codedMessage;
        this.key = key;
        decode();
    }

    @Override
    public String getCodedMessage() {
        return codedMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getKey() {
        return key;
    }
}
