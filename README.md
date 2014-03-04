Tuning Fork
========

java pipe programming framework

## Usage ##

write a pipe script `grab.properties`
```properties
	url=http://www.renrendai.com/lend/loanList!json.action
	pages=@url|Get|JsonToXml|XPath root/data/totalPage/text()
	listUrls=@(url)?pageIndex={1-2}|StringGenerator
	jsonBodys=@listUrls|Get|JsonToXml|XPath root/data/loans/title/text()
```

use `PipeInterpreter` to evaluate this file

```java
	String script = IOUtils.toString(PipeInterpreterTest.class.getResourceAsStream(TEST_FILE_PATH));
	PipeInterpreter interpreter = new PipeInterpreterFactory().getPipeInterpreter();
	interpreter.evaluate(script);

	System.out.println(interpreter.getVariable("pages")[0]);

	System.out
			.println(Arrays.toString(interpreter.getVariable("listUrls")));
	System.out
			.println(Arrays.toString(interpreter.getVariable("jsonBodys")));
```

a possible output：

	
