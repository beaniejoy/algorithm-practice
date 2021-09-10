## 문자열 압축

[Link](https://programmers.co.kr/learn/courses/30/lessons/60057)

```java
class Solution {
    public int solution(String s) {
        
        int minLength = 1000;
        
        int totalSize = s.length();
        int size = 1;
        String priorStr = "";
        StringBuilder sb = new StringBuilder();
        
        while(size < totalSize / 2 + 1) {
            priorStr = s.substring(0, size);
            
            int index = size;
            int count = 1;
            while(index <= totalSize) {
                String nextStr = "";
                if(index + size > totalSize)
                    nextStr = s.substring(index);
                else
                    nextStr = s.substring(index, index + size);
                
                if(priorStr.equals(nextStr))
                    count++;
                else {
                    if(count > 1)
                        sb.append(String.valueOf(count) + priorStr);
                    else
                        sb.append(priorStr);
                    
                    count = 1;
                }
                
                priorStr = nextStr;
                index += size;
            }
            
            sb.append(priorStr);
            
            if(minLength > sb.length())
                minLength = sb.length();
            
            size++;
            sb.delete(0, sb.length());
        }
        
        // s 길이가 1인 경우 놓치지 말 것
        if(size == 1)
            minLength = 1;
        
        return minLength;
    }
}
```

- input s : `a`와 같은 놓치는 케이스 주의