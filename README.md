# Simple JSON Parser

A simple Java JSON Parser written by Nicholas Wilson in the style of org.json.

Free and open-source. Feel free to modify and use for whatever!

The main method parser method is `JSONParser.parse(String json)` that returns a `JSONObect`. It's pretty simple!

Use the get methods from the `JSONObject` to return values.

## Changelog

### v1.0.3 

Added the ability to handle escape characters.
Added toString methods to turn a JSONObject back into JSON.
The add method in JSONArray now only accepts JSONObjects, as it should have been always.

### v1.0.2

Fixed a bug where string fields still had quotes around them.

### v1.0.1

Added more to the README