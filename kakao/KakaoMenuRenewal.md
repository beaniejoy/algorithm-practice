# 메뉴 리뉴얼

[Link](https://programmers.co.kr/learn/courses/30/lessons/72411)

```java
import java.util.*;

public class MenuRenewal {
    private String[] orders;
    private List<Menu> menuList;
    private List<String> resultList;
    private int maxCount;
    private Set<String> tmpSaved;

    private static class Menu implements Comparable<Menu>{
        char name;
        int freq;

        public Menu(char name, int freq) {
            this.name = name;
            this.freq = freq;
        }

        @Override
        public int compareTo(Menu o) {
            return this.freq > o.freq ? -1 : 1;
        }
    }

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        this.orders = orders;
        this.resultList = new ArrayList<>();
        this.menuList = new ArrayList<>();
        this.tmpSaved = new HashSet<>();

        Set<Character> allMenus = new HashSet<>();

        for (String order : orders) {
            char[] menuArr = order.toCharArray();

            for (char menu : menuArr)
                allMenus.add(menu);
        }

        for (char menu : allMenus) {
            Menu target = new Menu(menu, 0);
            for (int i = 0; i < orders.length; i++) {
                if (orders[i].contains(String.valueOf(menu))) {
                    target.freq++;
                }
            }
            menuList.add(target);
        }

        Collections.sort(menuList);

        for (int number : course) {
            maxCount = 0;
            bruteForce(0, 0, number, "");
            resultList.addAll(tmpSaved);
            tmpSaved.clear();
        }

        answer = new String[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            char[] charArr = resultList.get(i).toCharArray();
            Arrays.sort(charArr);
            answer[i] = String.valueOf(charArr);
        }

        Arrays.sort(answer);

        return answer;
    }

    private void bruteForce(int depth, int index, int k, String comp) {
        if (depth == k) {
            checkAllOrders(comp);
            return;
        }

        for (int i = index; i < menuList.size(); i++) {
            String added = comp + String.valueOf(menuList.get(i).name);
            bruteForce(depth + 1, i + 1, k, added);
        }
    }

    private void checkAllOrders(String comp) {
        int count = 0;
        for (String order : orders) {
            boolean isSatisfied = true;
            for (int i = 0; i < comp.length(); i++) {
                if (!order.contains(String.valueOf(comp.charAt(i)))) {
                    isSatisfied = false;
                    break;
                }
            }
            if(isSatisfied)
                count++;
        }

        if(count < 2 || maxCount > count)
            return;

        if (maxCount < count) {
            tmpSaved.clear();
            maxCount = count;
        }
        tmpSaved.add(comp);
    }
}
```

- orders에 있는 모든 메뉴들을 뽑아내서 Set에 저장(중복 허용 X 이용)
- 뽑은 메뉴 Set을 가지고 Menu Obejct 생성
  - Menu Object 안에는 freq(몇 명이 주문을 했는지), name(메뉴 알파벳)
- brute-force 방법을 이용해 모든 경우를 체크