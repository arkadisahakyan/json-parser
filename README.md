# JSON Parser
This program does not fully support the JSON specification.

## Specification & Features
- Key-value pair names must include only the characters **a-z**, **A-Z**
- Key-value pair values ​​can include strings (e.g. "Hello World"), numbers (e.g. -123.045), booleans (e.g. false), JSON objects {} or arrays []
- Arrays [] can only contain JSON objects {}
- JSON objects {} can only contain key-value pairs
- If arrays [] or JSON objects {} contain multiple elements, they must be separated by commas; the last element should not be separated by a comma
