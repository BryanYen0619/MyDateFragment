# MyDateFragment
----

**# 目標**    
	
1. 建立日期選擇功能。
2. 簡易UI。
3. 實作日期限制範圍。


**# 遇到的坑**

1. 原本只想用DatePicker結果Thread Call Back顯示不出來。
2. 用DateFragment還要實作Dialog部分功能
3. Date MinTime遇到
	
	```java
	// 注意：如果不提前一秒的話會報錯
	"java.lang.IllegalArgumentException: fromDate: XXX does not precede toDate: XXX"
    ```
4. setMaxDate遇到

    ```java
    //注意：如果使用了setMaxDate必須關掉CalenderView,否則會報錯
    "FATAL EXCEPTION: main java.lang.NullPointerException"
	``` 
5. setMaxDate又遇到，Android版本BUG
	
	```java
	if (Build.VERSION.SDK_INT == 22) {
		// Android 5.1無法選擇迄止日期，所以多加一天。
        nowCalendar.add(Calendar.DAY_OF_YEAR, 1); 
    }
    ```	
