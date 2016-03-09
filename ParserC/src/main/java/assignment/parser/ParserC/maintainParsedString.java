package assignment.parser.ParserC;

public class maintainParsedString {
		private String parsedString;

		public String getParsedString() {
			return parsedString;
		}

		public void setParsedString(String parsedString) {
			this.parsedString = getParsedString()+parsedString;
		}
	}
/**
 * 
 * 
 * case 3: expected parser : [ClassB|-hello:String]
[ClassA|+message:String;-bark:String;testMethod():void]
[ClassA]^-[ClassB]

*
*
*
*case 4
[[ConcreteObserver|;update:void; showState():void; ConcreteObserver(theSubject : ConcreteSubject)]
[ConcreteSubject|-subjectState:String|getState():String; setState(status:String):void;showState():void;] 
[Optimist|;update():void;Optimist(sub:ConcreteSubject)]
[Pessimist|;update():void;Pesimist(sub:ConcreteSubject)]
[<<Interface>>Subject|attach(obj :Observer):void; 
detach(obj : Observer):void;notifyObservers():void]
[TheEconomy|TheEconomy()]
[ConcreteSubject]^-[TheEconomy]]
[ConcreteObserver]^-[Pessimist]]
[ConcreteObserver]^-[Optimist]]
[ConcreteSubject]uses [<<interface>>;Observer];
[ConcreteSubject]uses -.->[<<interface>>;Observer];
[ConcreteSubject]...*<<interface>>Observer];
[ConcreteSubject]uses [ConcreteObserver];
[<<interface>>;Subject]^-.-[ConcreteSubject];
[<<interface>>;Observer]^-.-[ConcreteObserver]


*/
