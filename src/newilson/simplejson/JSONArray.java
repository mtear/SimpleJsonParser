/**
 * Simple JSON Parser
 * 
 * File: newilson.simplejson.JSONArray.java
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
 * An object that holds an Array of JSONObjects.
 * 
 * @author Nicholas Wilson
 *
 */
public class JSONArray{
	
//*********************************************************____________________
//*************************CLASS MEMBERS*******************____________________
//*********************************************************____________________

	List<Object> objects = new ArrayList<Object>();
	
//*********************************************************____________________
//*************************CLASS METHODS*******************____________________
//*********************************************************____________________

	/**
	 * Add a JSONObject to the JSONArray
	 * 
	 * @param object The object to add to the JSONArray if it is a JSONObject
	 */
	public void add(Object object){
		if(object instanceof JSONObject)
			objects.add(object);
	}
	
	/**
	 * Get a generic Object from the JSONArray at the selected index.
	 * 
	 * @param i The index to return from
	 * @return The object in the JSONArray at the selected index.
	 */
	public Object get(int i){
		return objects.get(i);
	}
	
	/**
	 * Get a JSONObject from the JSONARrray at the selected index.
	 * @param i The index to return from
	 * @return The JSONObject in the JSONArray at the selected index.
	 */
	public JSONObject getJSONObject(int i){
		return (JSONObject)objects.get(i);
	}
	
	/**
	 * Gets the number of elements in the JSONArray.
	 * 
	 * @return The number of elements in the JSONArray.
	 */
	public int size(){
		return objects.size();
	}
	
	@Override
	public String toString(){
		StringBuilder result = new StringBuilder();
		result.append("[\n");
		
		for(int i = 0; i < size(); i++){
			Object o = get(i);
			String value = (o == null) ? "null" : o.toString();
			result.append(value);
			//Add comma if appropriate
			if(i < size()-1) result.append(",\n");
		}
		
		return result.append("\n]").toString();
	}
	
}
