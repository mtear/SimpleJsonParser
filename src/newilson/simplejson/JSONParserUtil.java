/**
 * Simple JSON Parser
 * 
 * File: newilson.simplejson.JSONParserUtil.java
 * 
 * Author: Nicholas Wilson
 * 
 * Free and open source to use. No license. Feel free to use and change!
 * 
 */

package newilson.simplejson;

/**
 * A class with static JSON parsing helper methods
 * 
 * @author Nicholas Wilson
 *
 */
public class JSONParserUtil {
	
//*********************************************************____________________
//************************STATIC METHODS*******************____________________
//*********************************************************____________________
	
	/**
	 * Find the next comma in the JSON string outside of curly brackets,
	 * square brackets, and quotes.
	 * 
	 * Assumes the string has been checked for balance already.
	 * 
	 * @param json The JSON string
	 * @return The int index of the next comma outside of brackets and quotes,
	 * or -1 if there is no such comma
	 */
	public static int findEndOfObjectComma(String json){
		int curlyBalance = 0, squareBalance = 0;
		boolean inQuote = false;
		
		//Iterate through the string
		for(int i = 0; i < json.length(); i++){
			char c = json.charAt(i);
			
			//Skip checking characters if in a string
			if(inQuote && c != '"') continue;
			
			//Process the character
			if(c == ',' && curlyBalance == 0 && !inQuote && squareBalance == 0){
				//Return the index if we found a comma while balanced
				return i;
			}else if(c == '{'){
				curlyBalance++;
			}else if(c == '}'){
				curlyBalance--;
			}else if(c == '['){
				squareBalance++;
			}else if(c == ']'){
				squareBalance--;
			}else if(c == '"'){
				inQuote = !inQuote;
			}
		}
		
		//Could not find comma
		return -1;
	}
	
	/**
	 * Checks that a string is balanced in its curly brackets, square brackets,
	 * and quotes.
	 * 
	 * @param json The JSON string to check
	 * @return True if the string is balanced, false otherwise.
	 */
	public static boolean isBalanced(String json){
		int curlyBalance = 0, squareBalance = 0;
		boolean inQuote = false;
		
		//Iterate through the string
		for(int i = 0; i < json.length(); i++){
			char c = json.charAt(i);
			
			//Skip checking characters if in a string
			if(inQuote && c != '"') continue;
			
			//Process the character
			if(c == '{'){
				curlyBalance++;
			}else if(c == '}'){
				curlyBalance--;
				if(curlyBalance < 0) return false; //Negative balance invalid
			}else if(c == '['){
				squareBalance++;
			}else if(c == ']'){
				squareBalance--;
				if(squareBalance < 0) return false; //Negative balance invalid
			}else if(c == '"'){
				inQuote = !inQuote;
			}
		}
		if(inQuote) return false; //Unclosed quote invalid
		
		//Valid balanced string
		return true;
	}
	
	/**
	 * Check if a string is numeric
	 * 
	 * @param s The string to analyze
	 * @return True is the string is a number, false otherwise
	 */
	public static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	/**
	 * Check if a string is one quoted string.<br>
	 * This means the string is surrounded with quotes and does not have
	 * any other quoted sections inside of it.<br>
	 * Note: This does not check for escape characters
	 * 
	 * @param s The string to analyze
	 * @return True if the string is one quoted string,
	 * false otherwise.
	 */
	public static boolean isOneQuotedString(String s){
		//Check the string is surrounded by quotes
		if(!(s.startsWith("\"") && s.endsWith("\""))) return false;
		
		//Check inside the quotes for any other quotes
		for(int i = 1; i < s.length()-1; i++){
			if(s.charAt(i) == '"') return false;
		}
		return true;
	} 
	
	/**
	 * Check if there is only one set of {} at the root level in a JSON
	 * string.
	 * 
	 * @param json The JSON to analyze.
	 * @return True if there is only one set of {} at the root level,
	 * false otherwise.
	 */
	public static boolean isSingleJSONObject(String json){
		//Sanity check, check if starts and ends with {}
		if(!json.startsWith("{") && json.endsWith("}")) return false;
		
		int balance = 0;
		//Iterate through the string
		for(int i = 0; i < json.length(); i++){
			char c = json.charAt(i);
			if(c == '{') balance++;
			if(c == '}') balance--;
			
			//Check if we have a premature 0 balance
			if(balance == 0 && i < json.length()-1) return false;
		}
		
		return true;
	}
	
	/**
	 * Remove all unquoted whitespace characters from a string.<br>
	 * All whitespace characters not in a set of quotes will be removed.
	 * 
	 * @param json The string to filter
	 * @return The filtered string with no unquoted whitespace
	 */
	public static String removeUnquotedWhitespace(String json){
		String result = "";
		boolean inQuote = false;
		
		//Iterate through the string
		for(int i = 0; i < json.length(); i++){
			char c = json.charAt(i);
			
			//Toggle whether we're in a quote
			if(c == '"'){
				inQuote = !inQuote;
			}
			//Put the character in no matter what if in a quote
			if(inQuote){
				result += c;
			}else{
				//Otherwise only put non-whitespace
				if(!Character.isWhitespace(c)){
					result += c;
				}
			}
		}
		
		return result;
	}

}
