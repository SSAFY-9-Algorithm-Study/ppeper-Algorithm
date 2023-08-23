package week21;

import java.util.Stack;

class 짝지어제거하기 {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch: s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(ch);
            } else {
                // 마지막에 들어온 값과 현재 들어올 값이 같음
                if (stack.peek() == ch) {
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}