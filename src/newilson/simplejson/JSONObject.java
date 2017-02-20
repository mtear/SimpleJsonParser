/**
 * Simple JSON Parser
 * 
 * File: newilson.simplejson.JSONObject.java
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

import java.util.HashMap;
import java.util.Set;

/**
 * A class representing a JSONObject.<br>
 * <br>
 * Has methods to put and get different values that can be found in a JSON
 * string.
 * 
 * @author Nicholas Wilson
 *
 */
public class JSONObject {

//*********************************************************____________________
//*************************CLASS MEMBERS*******************____________________
//*********************************************************____________________

	HashMap<String, Object> members = new HashMap<String, Object>();

//*********************************************************____________________
//*************************CLASS METHODS*******************____________________
//*********************************************************____________________
	
	/**
	 * Get a generic Object from the JSONObject.
	 * 
	 * @param name The name key of the object.
	 * @return The object or null if that object is not in the JSONObject
	 */
	public Object get(String name){
		return members.get(name);
	}
	
	/**
	 * Get a Boolean Object from the JSONObject.
	 * 
	 * @param name The name key of the object.
	 * @return The Boolean object
	 * or null if that object is not in the JSONObject
	 * or the object is not a Boolean
	 */
	public Boolean getBoolean(String name){
		try{
			return (Boolean)members.get(name);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Get a Double Object from the JSONObject.
	 * 
	 * @param name The name key of the object.
	 * @return The Double object
	 * or null if that object is not in the JSONObject
	 * or the object is not a Double
	 */
	public Double getDouble(String name){
		try{
			return (Double)members.get(name);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Get a Integer Object from the JSONObject.
	 * 
	 * @param name The name key of the object.
	 * @return The Integer object
	 * or null if that object is not in the JSONObject
	 * or the object is not a Integer
	 */
	public Integer getInteger(String name){
		try{
			return (Integer)members.get(name);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Get a JSONArray Object from the JSONObject.
	 * 
	 * @param name The name key of the object.
	 * @return The JSONArray object
	 * or null if that object is not in the JSONObject
	 * or the object is not a JSONArray
	 */
	public JSONArray getJSONArray(String name){
		try{
			return (JSONArray)members.get(name);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Get a JSONObject from the JSONObject.
	 * 
	 * @param name The name key of the object.
	 * @return The JSONObject
	 * or null if that object is not in the JSONObject
	 * or the object is not a JSONObject
	 */
	public JSONObject getJSONObject(String name){
		try{
			return (JSONObject)members.get(name);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Get a String from the JSONObject.
	 * 
	 * @param name The name key of the object.
	 * @return The String object
	 * or null if that object is not in the JSONObject
	 * or the object is not a String
	 */
	public String getString(String name){
		try{
			return (String)members.get(name);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * Whether or not a key exists in this JSONObject
	 * @param name The name key of the object
	 * @return True if there is an object by that name in the JSONObject,
	 * false otherwise.
	 */
	public boolean has(String name){
		return members.containsKey(name);
	}
	
	/**
	 * Whether or not an object is a null value in the JSONObject
	 * 
	 * @param name The name key of the object
	 * @return True if this JSONObject has the key and the value is null,
	 * false otherwise
	 */
	public boolean isNull(String name){
		return has(name) && members.get(name) == null;
	}
	
	/**
	 * Gets the set of keys this JSONObject has values for.
	 * 
	 * @return The key set of this JSONObject
	 */
	public Set<String> keySet(){
		return members.keySet();
	}
	
	/**
	 * Put an Object into this JSONObject by a key.
	 * 
	 * @param name The name key of the object
	 * @param value The Object to put into the JSONObject
	 */
	public void put(String name, Object value){
		members.put(name, value);
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("{\n");
		
		int index = 0;
		//Add all the objects and their keys
		for(String key : keySet()){
			index++;
			//Get the value of the Object as a String
			String value = get(key).toString();
			if(value.equals("null")) value = "null";
			
			//Add quotes for String literals
			if(get(key) instanceof String){
				value = "\"" + 
						JSONParserUtil.encodeEscapeCharacters(value) + "\"";
			}
			
			String nextLine = "";
			//Add comma if appropriate
			if(index < keySet().size()){
				nextLine = ",";
			}
			
			result.append("\"" + key + "\"").append(" : ").append(value)
			.append(nextLine).append("\n");
			
		}
		
		return result.append("}").toString();
	}
		
}

