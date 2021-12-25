# Getting Started

To run these tests you don't need any additional moves except 
running ```mvn clean test``` in your terminal

By default, the headless mode is on, if you need to see the browser, 
then open ```TestsConfiguration``` class 
and change ```Configuration.headless=true;``` to ```false```

Possible improvements:
* Attach allure reports for better understanding what's going on
* Add timers interceptors for tracking performance
* Add parameterization to reduce the count of the similar tests
* Add parallelization to increase the speed of the whole test set
* ...

Possible issues:
* Settings tests don't contain checks for alphabet characters in inputs
since all inputs now are default inputs with type ```number```
* Win condition has been checked only on 1*2 field with 1 bomb which is pretty simple.
To check the game correctly, we need to get an ArrayBuffer with bombs from the app somehow.
* First turn should be successful - checks on the board 100*100 with 9999 bombs. But there is a chance
that I'm very lucky, every time firing the empty cell with a chance of 1/10000 ;)

