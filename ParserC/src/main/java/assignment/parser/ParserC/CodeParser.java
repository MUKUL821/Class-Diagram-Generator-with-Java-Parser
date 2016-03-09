package assignment.parser.ParserC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CodeParser {
	HashMap<String, String> finallist = new HashMap<String, String>();
	HashMap<String, String> relation = new HashMap<String, String>();
	ArrayList<String> resultRelation = new ArrayList();
	ArrayList<String> resultsMethods = new ArrayList();
	String classdetails = "";
	String interfacedetails = "";

	public CodeParser(String parsedString) throws ParseException {
		// for method parsing
		// ExperimentFinder objf =new ExperimentFinder();
		String[] arr = parsedString.split(",class-end");

		String varString = "";
		System.out.println("MY ALL DATA.." + parsedString);
		for (int i = 0; i < arr.length; i++) {
			// CLASS LIST PARSING
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(arr[i]);

			String name = (String) json.get("name");
			System.out.println("name" + name);
			if (json.get("isInterface").toString() == "false")
				classdetails += name;
			else
				interfacedetails += " " + name;
			// classRelation(json);
		}
		System.out.println("---------------" + classdetails);
		System.out.println("---------------" + interfacedetails);
		for (int i1 = 0; i1 < arr.length; i1++) {

			// System.out.println("output is"+arr[i]);
			JSONParser parser1 = new JSONParser();
			/// JSONObject jsonn = (JSONObject)(arr[i].toString());
			JSONObject json1 = (JSONObject) parser1.parse(arr[i1]);
			// System.out.println("output is" + json1);
			varString = ClassVarParser(json1, i1, arr);

			// classRelation(json);
		}
		System.out.println("complete list" + finallist.size());
		TreeMap<String, String> sortedMap = new TreeMap<String, String>(finallist);

		Iterator<String> iterator = sortedMap.keySet().iterator();
		while (iterator.hasNext()) {
			String mentry = iterator.next();
			// System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
			// String strval=mentry.getValue().toString().substring(beginIndex)
			System.out.println(sortedMap.get(mentry));
			if (!(mentry).contains("pattern") && !(mentry).contains("methods")) {
				String[] original = sortedMap.get(mentry).toString()
						.substring(1, sortedMap.get(mentry).toString().length() - 1).split(",");
				String[] str = sortedMap.get(mentry).toString().replaceAll("\\P{L}+", ",").split(",");
				// System.out.println("mentry.getValue().toString()" +
				// sortedMap.get(mentry));

				for (int j = 1; j < str.length; j++) {
					System.out.println(mentry + "STR ----" + str[j] + "" + original[j - 1]);
					relation.put(mentry + "-" + str[j], original[j - 1]);
					// relation.put(mentry.getKey().toString()+str[j],mentry.getKey()+str[j]);
				}

			} else {
				// System.out.println("jjjjjjjjjj"+(String) mentry.getKey());
				if (sortedMap.get(mentry) != "EMPTY") {
					String parsedstr = sortedMap.get(mentry);
					System.out.println("inn" + sortedMap.get(mentry));
					String interf = sortedMap.get(mentry).toString().substring(1, sortedMap.get(mentry).indexOf("|"));
					System.out.println("changing name of interface" + interf + "iiii" + interfacedetails);
					if (interfacedetails.indexOf(interf) > 0) {
						System.out.println("changing name of interface");
						parsedstr = sortedMap.get(mentry).toString().replace(interf, "[<<interface>>;" + interf+"]");

					}
					resultsMethods.add(parsedstr);
				}
			}

		}

		// System.out.println("finalllllllllllllllllllllllllllllllll" +
		// relation);
		// its time to filter final
		TreeMap<String, String> sortedHashMap = new TreeMap<String, String>(relation);

		Iterator<String> keySetIterator = sortedHashMap.keySet().iterator();
		while (keySetIterator.hasNext()) {
			String key = keySetIterator.next();
			System.out.println("key: " + key + " value: " + sortedHashMap.get(key));
			String[] str = key.split("-");
			String var = "";
			if (sortedHashMap.get(str[1] + "-" + str[0]) != null)
				var = sortedHashMap.get(str[1] + "-" + str[0]).toString();
			else
				var = "[" + str[0].toString() + "]";

			System.out.println("key made: " + str[1] + "-" + str[0] + " value: " + var);

			String add = var + "-" + sortedHashMap.get(key);
			resultRelation.add(add);
			sortedHashMap.remove(str[1] + "-" + str[0]);
			sortedHashMap.remove(str[0] + "-" + str[1]);
			keySetIterator = sortedHashMap.keySet().iterator();

		}
		// result relation keep the record between classes
		String resultRelationString = packageConversion(interfacedetails,resultRelation);
		System.out.println("final resultttttt" + resultRelationString.replace(",", "").replace("[[", "["));
		System.out.println("final resultttttt" + resultsMethods.toString().replace(",", "").replace("[[", "["));
	}

	public String ClassVarParser(JSONObject eachclass, int i, String[] arr) throws ParseException {
		List<String> classNames = new ArrayList<String>();

		String parsepattern = "";
		System.out.println("CLASSSSSS" + eachclass.get("name") + "---------------------------" + eachclass);
		if (eachclass.get("isInterface") != "true") {// not working..utlit it
														// changes to.toString()

			System.out.println("is not interface" + eachclass.get("variables").toString());
			// JSONObject varr= (JSONObject) eachclass.get("variables");
			// System.out.println("--"+varr.get("variable-data-type"));
			JSONArray jArray = new JSONArray();
			// FOR VARIABLE...............................
			jArray = (JSONArray) eachclass.get("variables");
			String parsemethods = ClassMethodParser(eachclass, i);

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = jArray.iterator();
			while (iterator.hasNext()) {
				String sign = "", dt = "";
				JSONObject iter = new JSONObject();
				iter = iterator.next();
				// System.out.println("working"+iterator.next().get("variable-modifier"));

				switch (iter.get("variable-modifier").toString()) {
				case "1":
					sign = "+";
					break;
				case "2":
					sign = "-";
					break;
				default:
					sign = "";
				}
				// String reserveDatatypes= "String, int,
				// float,double,char,array";
				String str = iter.get("variable-data-type").toString();
				if (str.indexOf("[") > 0)
					dt = str.substring(0, str.indexOf("[")) + "(*)";
				// change it to regular expression
				else if (str.indexOf("<") > 0 || (!str.contains("int") && !str.contains("float")
						&& !str.contains("String") && !str.contains("double"))) {
					classNames.add(classRelation(str));
					// NEED TO MAKE FUNCTION for class-class relation...question
					// 1
					dt = "";
				} else
					dt = str;

				if (dt != "" && sign != "") {
					if (sign.contains("-")) {
						// System.out.println("yipi"+parsemethods+"-------"+"set"+iter.get("variable-name").toString());
						String search = iter.get("variable-name").toString().substring(0, 1).toUpperCase()
								+ iter.get("variable-name").toString().substring(1) + "()";
						// System.out.println("search" + search);
						if (parsemethods.indexOf("get" + search) > -1 && parsemethods.indexOf("set" + search) > -1) {
							// System.out.println("yipiessssssssss");
							sign = "+";
							String[] str1 = parsemethods.split(";");
							parsemethods = "";
							for (int k = 0; k < str1.length; k++) {
								if (str1[k].contains(search))
									str1[k] = "";

								parsemethods += str1[k];

							}
						}

					}
					parsepattern += sign + iter.get("variable-name") + ":" + dt + ";";

				}
				//////////////////////// variable
				//

			}
			if (parsepattern.length() > 0)
				parsepattern = parsepattern.substring(0, parsepattern.length() - 1);
			System.out.println("PASER pattern .." + parsepattern);
		
			if (parsemethods.length() > 0)
				parsepattern += "|" + parsemethods;

			String ext = eachclass.get("extends").toString();
			if (ext.length() > 0)
				resultRelation.add("\n"+eachclass.get("extends").toString() );
				//parsepattern += "]" + "\n" + eachclass.get("extends").toString();
			String imp = eachclass.get("implements").toString();
			if (imp.length() > 0)
				resultRelation.add("\n"+eachclass.get("implements").toString() );
			
			//	parsepattern += "]" + eachclass.get("implements").toString();
			if (parsepattern != "") {

				parsepattern = eachclass.get("name") + "|" + parsepattern;
				parsepattern = "[" + parsepattern + "]" + "\n";
			} else
				parsepattern = "EMPTY";

		} else
			System.out.println("is interface");
		String parsemethods = ClassMethodParser(eachclass, i);

		System.out.println("list of linked objs are" + classNames);

		finallist.put((String) eachclass.get("name"), classNames.toString());
		if (parsepattern != "EMPTY")
			finallist.put("pattern " + (String) eachclass.get("name"), parsepattern);
		// uses lists finallist.put("methods "+(String) eachclass.get("name"),
		// parsemethods);

		return parsepattern;
	}

	private String ClassMethodParser(JSONObject eachclass, int i) {
		String parsemethods = "";
		// System.out.println("yipeiii gotcha"+eachclass);
		if (eachclass.get("isInterface") != "true") {

			// System.out.println("is not
			// interface"+eachclass.get("variables").toString());
			// JSONObject varr= (JSONObject) eachclass.get("methods");
			// System.out.println("--"+varr.get("method-return-type"));
			JSONArray jArray = new JSONArray();
			// FOR methods..............................
			jArray = (JSONArray) eachclass.get("methods");

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = jArray.iterator();
			while (iterator.hasNext()) {
				String sign = "";
				JSONObject iter = new JSONObject();
				iter = iterator.next();
				System.out.println(
						"working" + iter.get("method-modifier").toString() + iter.get("method-name").toString());

				// sign=(iter.get("method-modifier").toString()=="1")?"+":"";
				switch (iter.get("method-modifier").toString()) {
				case "1":
					sign = "+";
					break;
				default:
					sign = "";
				}
				// System.out.println("sign"+sign);
				String str = iter.get("method-return-type").toString();
				if (sign != "")
					parsemethods += iter.get("method-name") + "():" + iter.get("method-return-type") + ";";//////////////////////// variable

				if (iter.get("method-usedd").toString().indexOf(">>") > 0) {

					String strr = iter.get("method-usedd").toString().substring(
							iter.get("method-usedd").toString().indexOf(";") + 1,
							iter.get("method-usedd").toString().length() - 1);
					System.out.println("strr" + strr);
					if (classdetails.indexOf(strr) > 0)
					///	parsemethods += iter.get("method-usedd").toString() + ";";//////////////////// RECHECK
						resultRelation.add(iter.get("method-usedd").toString());															//////////////////// FOR
																					//////////////////// CASE
																					//////////////////// 1,2,3

					else {
						resultRelation.add(iter.get("method-usedd").toString());															//////////////////// FOR
						
					//	parsemethods += iter.get("method-usedd").toString() + ";";
						System.out.println("ADDED");
					}

				}

			}
			System.out.println("PARSE METHODS...." + parsemethods);
		}
		return parsemethods;

	}

	public String classRelation(String str) {
		String clasobj = "";
		if (str.indexOf("<") > 0)
			clasobj = "*[" + str.substring(str.indexOf("<") + 1, str.length() - 1) + "]";
		else
			clasobj = "1[" + str + "]";
		return clasobj;

	}
	private String packageConversion(String interfaceList,ArrayList<String> resultRelation2){
	//function convers all interface nume in the final prepared list
		System.out.println("came in");
		String []stringsplit=interfaceList.split(" ");
		for(int i=0;i<stringsplit.length;i++)
		{
			if(resultRelation2.contains(("["+stringsplit[i]+"]")))
			{	resultRelation2.toString().replaceAll(("["+stringsplit[i]+"]"), ("[<<interface>>;"+stringsplit[i]+"]"));
			System.out.println("replaced......."+"["+stringsplit[i]+"]");}	
			if(resultRelation2.contains(("["+stringsplit[i]+"]uses")))
			{	resultRelation2.toString().replaceAll(("["+stringsplit[i]+"]uses"), ("[<<interface>>;"+stringsplit[i]+"]uses"));
			//parsinglist.replace((" ["+stringsplit[i]+"]uses"), (" [<<interface>>;"+stringsplit[i]+"]uses"));
			System.out.println("replaced......."+"["+stringsplit[i]+"]uses");}	
		//if(resultRelation2.indexOf("["+stringsplit[i]+"]"+"uses -.->[<<interface>>;")>0)
		//}
		
		System.out.println("list replaced"+resultRelation2);
		
	}
		return resultRelation2.toString();}
}
