/**
 * Simple JSON Parser
 * 
 * File: newilson.simplejson.JSONParser.java
 * 
 * Author: Nicholas Wilson
 * 
 * Free and open source to use. No license. Feel free to use and change!
 * 
 */

package newilson.simplejson;

//*****************************************************************************
//*************************IMPORT LIBRARIES************************************
//*****************************************************************************

import java.util.ArrayList;
import java.util.List;

/**
 * A class with static methods for parsing a JSON string.
 * 
 * @author Nicholas Wilson
 *
 */
public class JSONParser {
	
//*********************************************************____________________
//************************STATIC METHODS*******************____________________
//*********************************************************____________________

	/**
	 * Parse a JSON string and return the object it represents.<br>
	 * This is the inner recursive method called by "parse".
	 * 
	 * @param json The JSON string to parse
	 * @return The parsed object from the JSON string
	 * @throws Exception There will be an Exception if the JSON is invalid
	 */
	private static Object innerParseMethod(String json) throws Exception{
		//CHECK JSON VALIDITY: BALANCED STRING
		if(!JSONParserUtil.isBalanced(json)) throw new Exception();
		
		//RECURSIVE BASE CASE:
		//LOOK FOR PRIMITIVE OBJECTS
		if(json.equals("null")) return null;
		if(json.equals("true") || json.equals("false")){
			return new Boolean(json);
		}
		if(JSONParserUtil.isOneQuotedString(json)){
			return json.substring(1, json.length()-1);
		}
		if(JSONParserUtil.isNumeric(json)){
			if(json.contains(".")){
				return new Double(json);
			}else{
				return new Integer(json);
			}
		}
		
		//MUST PERFORM RECURSION
		
		//If it is NOT a primitive it MUST be an object or array
		if(json.startsWith("{") && json.endsWith("}")){
			//This is a JSONObject
			json = json.substring(1, json.length()-1);
		}else if(json.startsWith("[") && json.endsWith("]")){
			//This is a JSON Array
			json = json.substring(1, json.length()-1);
			//Return the parsing of the Array
			//(will recurse back to this method)
			return parseJSONArray(json);
		}else{
			//JSON VALIDITY: IF NOT SURROUNDED BY { OR [ THE JSON IS INVALID
			throw new Exception();
		}
		
		//HERE WE KNOW WE ARE PARSING A JSONOBJECT, NOT A PRIMITIVE OR ARRAY
		
		JSONObject jo = new JSONObject();
		
		List<String> objectStrings = splitByOutsideObjectCommas(json);
		
		//Get the names and split the object
		for(String objectString : objectStrings){
			//Get the field name
			int colon = objectString.indexOf(':');
			String name = objectString.substring(0, colon);
			
			//Remove quotes
			name = name.substring(1, name.length()-1);
			jo.put(name, innerParseMethod(objectString.substring(colon+1)));
		}
		
		return jo;
	}
	
	/**
	 * Parse a JSON String into a JSONObject.
	 * 
	 * @param json The JSON string to parse
	 * @return
	 */
	public static JSONObject parse(String json){
		try{
			//Remove all the unquoted whitespace
			json = JSONParserUtil.removeUnquotedWhitespace(json);
			//Confirm the initial string is an object
			if(JSONParserUtil.isSingleJSONObject(json)
					&& JSONParserUtil.isBalanced(json)){
				//Start the recursion from the inner method
				return (JSONObject)innerParseMethod(json);
			}
		}catch(Exception e){}
		
		//Return null if error
		return null;
	}
	
	/**
	 * Parse a JSON string into an a JSONArray.
	 * 
	 * @param json The JSON to parse into the JSONArray
	 * @return a JSONArray with the fields as the indices
	 * @throws Exception An Exception is thrown if the JSON is invalid
	 */
	private static JSONArray parseJSONArray(String json) throws Exception{
		JSONArray jsonArray = new JSONArray();
		
		List<String> objectStrings = splitByOutsideObjectCommas(json);
		
		for(String objectString : objectStrings){
			jsonArray.add(innerParseMethod(objectString));
		}
		
		return jsonArray;
	}
	
	/**
	 * Split a list of JSON string fields
	 * 
	 * @param json The JSON to split
	 * @return A list of split JSON object strings
	 * @throws Exception There is an Exception if the JSON is invalid
	 */
	private static List<String> splitByOutsideObjectCommas(String json)
	throws Exception{
		List<String> objectStrings = new ArrayList<String>();
		
		//Take out all the children until no comma is found
		int index = -1;
		while( (index = JSONParserUtil.findEndOfObjectComma(json)) > -1){
			objectStrings.add(json.substring(0, index));
			json = json.substring(index+1);
			if(!JSONParserUtil.isBalanced(json)) throw new Exception();
		}
		//Add the leftovers
		objectStrings.add(json);
		
		return objectStrings;
	}
}
