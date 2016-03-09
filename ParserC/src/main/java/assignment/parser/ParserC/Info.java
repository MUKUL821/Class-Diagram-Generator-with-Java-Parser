package assignment.parser.ParserC;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Info {
		private JSONArray methods=null;
		private JSONArray variables=null;
		
		
		public JSONArray getMethods() {
			
			return methods;
		}
		public void setMethods(JSONArray met) {
		      methods.add(met);
		}
		public JSONArray getVariables() {
			return variables;
		}
		public void setVariables(JSONObject item) {
			System.out.println("vvvv"+item);
			this.variables.add(item);
		}
		
	


}
                                                                                                                                                                                                                                                                                                                                